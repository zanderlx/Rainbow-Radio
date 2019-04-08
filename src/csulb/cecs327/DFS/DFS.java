package csulb.cecs327.DFS;

import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.math.BigInteger;
import java.security.*;

import com.google.gson.Gson;

/* JSON Format

{"file":
  [
     {"name":"MyFile",
      "size":128000000,
      "pages":
      [
         {
            "guid":11,
            "size":64000000
         },
         {
            "guid":13,
            "size":64000000
         }
      ]
      }
   ]
} 
*/

public class DFS {

    /**
     * This class is for Meta Page
     */
    public class PagesJson {
        Long guid;
        Long size;
        int pageNumber;

        public PagesJson(long guid, long size) {
            this.guid = guid;
            this.size = size;
        }

        public long getPageNumber() {
            return size;
        }

        public long getGUID() {
            return guid;
        }
        // getters
        // setters
    }

    ;

    /**
     * This class is for Meta File
     */
    public class FileJson {
        String name;
        Long size;
        ArrayList<PagesJson> pages;

        public FileJson(String name) {
            this.name = name;
        }

        public void addPage(long guid) {
            pages.add(new PagesJson(pages.size() + 1, guid));
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getNumOfPages() {
            return pages.size();
        }

        public PagesJson getPage(int pageNumber) {
            return pages.get(pageNumber - 1);
        }

        public void printListOfPages() {
            System.out.printf("\n%-5s%-15s%-15s\n", "", "Page Number", "GUID");
            for (int i = 0; i < pages.size(); i++) {
                PagesJson temp = pages.get(i);

                System.out.printf("%-5s%-15s%-15d\n", "", temp.getPageNumber(), temp.getGUID());
            }
            System.out.println("");
        }
        // getters
        // setters
    }

    ;

    /**
     * This class is for Meta Data
     */
    public class FilesJson {
        //List<FileJson> file;
        ArrayList<FileJson> file = new ArrayList<FileJson>();

        public FilesJson() {

        }

        public void addFile(String fileName) {
            FileJson newFile = new FileJson(fileName);
            file.add(newFile);
        }

        public void printListOfFiles() {
            System.out.printf("\n%-15s%-15s\n", "Filename", "Number of Pages");
            for (int i = 0; i < file.size(); i++) {
                FileJson temp = file.get(i);

                System.out.printf("%-15s%-15d\n", temp.getName(), temp.getNumOfPages());

                if (temp.getNumOfPages() > 0)
                    temp.printListOfPages();
            }
            System.out.println("");
        }

        public FileJson getFile(String filename) {
            for (int i = 0; i < file.size(); i++) {
                FileJson temp = file.get(i);

                if (temp.getName().equals(filename)) {
                    return temp;
                }
            }
            return null;
        }

        public boolean fileExists(String filename) {
            for (int i = 0; i < file.size(); i++) {
                FileJson temp = file.get(i);

                if (temp.getName().equals(filename)) {
                    return true;
                }
            }
            return false;
        }

        public void deleteFile(String filename) {
            ListIterator<FileJson> listIterator = file.listIterator();

            while (listIterator.hasNext()) {
                FileJson temp = listIterator.next();

                if (temp.getName().equals(filename))
                    listIterator.remove();
            }
        }

        public void clear() {
            file.clear();
        }
        // getters
        // setters
    }

    ;

    int port;
    Chord chord;

    private long md5(String objectName) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(objectName.getBytes());
            BigInteger bigInt = new BigInteger(1, m.digest());
            return Math.abs(bigInt.longValue());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
        return 0;
    }

    public DFS(int port) throws Exception {

        this.port = port;
        long guid = md5("" + port);
        chord = new Chord(port, guid);
        Files.createDirectories(Paths.get(guid + "/repository"));
        Files.createDirectories(Paths.get(guid + "/tmp"));
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                chord.leave();
            }
        });

    }

    /**
     * Join the chord
     */
    public void join(String Ip, int port) throws Exception {
        chord.joinRing(Ip, port);
        chord.print();
    }

    /**
     * leave the chord
     */
    public void leave() throws Exception {
        chord.leave();
    }

    /**
     * print the status of the peer in the chord
     */
    public void print() throws Exception {
        chord.print();
    }

    /**
     * readMetaData read the metadata from the chord
     */
    public FilesJson readMetaData() throws Exception {
        FilesJson filesJson = null;
        try {
            Gson gson = new Gson();
            long guid = md5("Metadata");
            ChordMessageInterface peer = chord.locateSuccessor(guid);
            RemoteInputFileStream metadataraw = peer.get(guid);
            metadataraw.connect();
            Scanner scan = new Scanner(metadataraw);
            scan.useDelimiter("\\A");
            String strMetaData = scan.next();
            System.out.println(strMetaData);
            filesJson = gson.fromJson(strMetaData, FilesJson.class);
        } catch (NoSuchElementException ex) {
            filesJson = new FilesJson();
        }
        return filesJson;
    }

    /**
     * writeMetaData write the metadata back to the chord
     */
    public void writeMetaData(FilesJson filesJson) throws Exception {
        long guid = md5("Metadata");
        ChordMessageInterface peer = chord.locateSuccessor(guid);

        Gson gson = new Gson();
        peer.put(guid, gson.toJson(filesJson));
    }

    /**
     * Change Name
     */
    public void move(String oldName, String newName) throws Exception {
        // TODO: Change the name in Metadata
        // Write Metadata

        // Setting temp JsonObject
        FilesJson md = readMetaData();
        if (md.fileExists(oldName)) {
            FileJson metafile = md.getFile(oldName);
            metafile.setName(newName);
            writeMetaData(md);
        } else
            System.out.println("That file does not exist. Try again.");

    }

    /**
     * create an empty file
     */
    public void create(String fileName) throws Exception {
        FilesJson metaData = new FilesJson();
        metaData.addFile(fileName);
        writeMetaData(metaData);
    }

    /**
     * list
     *
     * @return
     * @throws Exception
     */
    public void lists() throws Exception {
        FilesJson metaData = readMetaData();
        if (metaData.file.size() > 0 || metaData == null) {
            FilesJson metadata = readMetaData();
            metadata.printListOfFiles();
        } else
            System.out.println("No files found in metadata.");
    }


    /**
     * delete file
     */
    public void delete(String fileName) throws Exception {
        FilesJson md = readMetaData();
        if (md.fileExists(fileName)) {
            FileJson metafile = md.getFile(fileName);
            if (metafile.getNumOfPages() > 0) {
                for (int i = 0; i < metafile.getNumOfPages(); i++) {
                    PagesJson page = metafile.pages.get(i);
                    long guid = page.getGUID();
                    ChordMessageInterface peer = chord.locateSuccessor(guid);
                    peer.delete(guid);
                }
            }
            md.deleteFile(fileName);
            writeMetaData(md);
        } else
            System.out.println("That file does not exist. Try again.");
    }


    public RemoteInputFileStream read(String fileName, int pageNumber) throws Exception {
        FilesJson md = readMetaData();
        if (md.fileExists(fileName)) {
            FileJson metafile = md.getFile(fileName);
            PagesJson page = metafile.getPage(pageNumber);
            long guid = page.getGUID();
            ChordMessageInterface peer = chord.locateSuccessor(guid);
            InputStream metadataraw = peer.get(guid);

            int content;
            while ((content = metadataraw.read()) != 0) {
                System.out.print((char) content);
            }
            System.out.println("");
        } else
            System.out.println("That file could not be located...");
        return null;
    }


    public void append(String filename, RemoteInputFileStream data) throws Exception {
        FilesJson md = readMetaData();
        if (md.fileExists(filename)) {
            long guid = md5(filename);
            Gson gson = new Gson();
            FileJson ourFile = new FileJson(filename);

            ChordMessageInterface peer = chord.locateSuccessor(guid);
            peer.put(guid, gson.toJson(ourFile));

            FileJson metafile = md.getFile(filename);
            metafile.addPage(guid);
            writeMetaData(md);
        } else
            System.out.println("That file could not be located...");
    }

}