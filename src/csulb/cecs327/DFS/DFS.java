package csulb.cecs327.DFS;

import java.time.*;
import java.util.*;
import java.nio.file.*;
import java.math.*;
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
     * This class is for pagesJson, containing the setters and getters
     */
    public class PagesJson {
        Long guid;
        Long size;
        String CreateTimeStamp;
        String WriteTimeStamp;
        String ReadTimeStamp;
        int pageNum;

        /**
         * Constructor for the pages
         *
         * @param guid              - specifies the global unique identifier
         * @param size              - size of
         * @param CreationTimeStamp
         * @param ReadTimeStamp
         * @param WriteTimeStamp
         * @param counter
         */
        public PagesJson(Long guid, Long size, String CreationTimeStamp, String ReadTimeStamp, String WriteTimeStamp, int counter) {
            this.guid = guid;
            this.size = size;
            this.CreateTimeStamp = CreationTimeStamp;
            this.ReadTimeStamp = ReadTimeStamp;
            this.WriteTimeStamp = WriteTimeStamp;
            this.pageNum = counter;
        }

        // getters
        public long getSize() {

            return size;
        }

        public long getGUID() {

            return this.guid;
        }

        public String getCreateTimeStamp() {
            return this.CreateTimeStamp;
        }

        public int getPageNumber() {
            return this.pageNum;
        }

        // setters
        public void setSize(Long size) {
            this.size = size;
        }

        public void setGUID(Long guid) {
            this.guid = guid;
        }

        public void setCreateTimeStamp(String CreateTimeStamp) {
            this.CreateTimeStamp = CreateTimeStamp;
        }

        public void setWriteTimeStamp(String WriteTimeStamp) {
            this.WriteTimeStamp = WriteTimeStamp;
        }

        public void setReadTimeStamp(String ReadTimeStamp) {
            this.ReadTimeStamp = ReadTimeStamp;
        }

        public void setPageNumber(int pageNum) {
            this.pageNum = this.pageNum;
        }
    }

    ;

    /**
     * This class is for FileJson and its setters and getters
     */
    public class FileJson {
        String name;
        Long size;
        String CreateTimeStamp;
        String WriteTimeStamp;
        String ReadTimeStamp;
        int counter;
        int PageNumber;
        int MaxPageNumber;
        ArrayList<PagesJson> pages = new ArrayList<PagesJson>();

        public FileJson() {
            this.size = new Long(0);
            this.PageNumber = 0;
            this.counter = 0;
            //this.creationTS = new Long(date.getTime());
            this.CreateTimeStamp = LocalDateTime.now().toString();
            this.ReadTimeStamp = "0";
            this.WriteTimeStamp = "0";
            this.MaxPageNumber = 0;
            this.pages = new ArrayList<PagesJson>();
        }

        public void addPage(Long guid, Long size, String CreateTimeStamp, String ReadTimeStamp, String WriteTimeStamp, int counter) {
            PagesJson newPage = new PagesJson(guid, size, CreateTimeStamp, ReadTimeStamp, WriteTimeStamp, counter);
            pages.add(newPage);
        }

        // Getters
        public String getName() {
            return this.name;
        }

        public Long getSize() {
            return this.size;
        }

        public int getMaxPageNumber() {
            return this.MaxPageNumber;
        }

        public int getPageNumber() {
            return this.PageNumber;
        }

        public ArrayList<PagesJson> getPages() {
            return pages;
        }

        public String getCreateTimeStamp() {
            return CreateTimeStamp;
        }

        // Setters
        public void setName(String name) {
            this.name = name;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        public void setMaxPageNumber(int MaxPageNumber) {
            this.MaxPageNumber = MaxPageNumber;
        }

        public int getNumOfPages() {
            return pages.size();
        }

        public void addSize(Long size) {
            this.size += size;
        }

        public void setCounter(int counter) {
            this.counter = counter;
        }

        public void setPageNumber(int PageNumber) {
            this.PageNumber = PageNumber;
        }

        public void addPageNumber(int PageNumber) {
            this.PageNumber += PageNumber;
        }

        public void setWriteTimeStamp(String WriteTimeStamp) {
            this.WriteTimeStamp = WriteTimeStamp;
        }

        public void setReadTimeStamp(String ReadTimeStamp) {
            this.ReadTimeStamp = ReadTimeStamp;
        }


        public void printListOfPages() {
            System.out.printf("\n%-5s%-15s%-15s\n", "", "Page Number", "GUID");
            for (int i = 0; i < pages.size(); i++) {
                PagesJson temp = pages.get(i);

                System.out.printf("%-5s%-15s%-15d\n", "", temp.getPageNumber(), temp.getGUID());
            }
            System.out.println("");
        }
    }

    /**
     * This class is for FilesJSon and its setters and getters
     */
    public class FilesJson {
        List<FileJson> metaFile;

        //ArrayList<FileJson> file = new ArrayList<FileJson>();
        public FilesJson() {
            metaFile = new ArrayList<FileJson>();
        }

        // Getter
        public FileJson getFile(int index) {
            return metaFile.get(index);
        }

        public int getSize() {
            return metaFile.size();
        }

        public void addFile(FileJson newFile) {
            metaFile.add(newFile);
        }


        public boolean fileExists(String filename) {
            for (int i = 0; i < metaFile.size(); i++) {
                FileJson temp = metaFile.get(i);

                if (temp.getName().equals(filename)) {
                    return true;
                }
            }
            return false;
        }

        public void deleteFile(String filename) {
            ListIterator<FileJson> listIterator = metaFile.listIterator();

            while (listIterator.hasNext()) {
                FileJson temp = listIterator.next();

                if (temp.getName().equals(filename))
                    listIterator.remove();
            }
        }

        public void clear() {
            metaFile.clear();
        }

        public void printListOfFiles() {
            System.out.printf("\n%-15s%-15s\n", "Filename", "Number of Pages");
            for (int i = 0; i < metaFile.size(); i++) {
                FileJson temp = metaFile.get(i);

                System.out.printf("%-15s%-15d\n", temp.getName(), temp.getNumOfPages());

                if (temp.getNumOfPages() > 0)
                    temp.printListOfPages();
            }
            System.out.println("");
        }
    }

    int port;
    Chord chord;
    FilesJson MetaData;

    /**
     * This class is for making guid's and hashes
     *
     * @param objectName the name of the object that is being hashed
     * @return 0
     */
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

    /**
     * This method opens a new connection to the specified port
     *
     * @param port
     * @throws Exception
     */
    public DFS(int port) throws Exception {
        this.port = port;
        long guid = md5("" + port);
        this.MetaData = new FilesJson();
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
//            System.out.println(strMetaData);
            filesJson = gson.fromJson(strMetaData, FilesJson.class);
        } catch (NoSuchElementException ex) {
            filesJson = new FilesJson();
        }
        return filesJson;
    }

    /**
     * Writes meta data into the pages
     *
     * @param filesJson The parameter being passed in, it is then written to
     * @throws Exception
     */
    public void writeMetaData(FilesJson filesJson) throws Exception {
        long guid = md5("Metadata");
        ChordMessageInterface peer = chord.locateSuccessor(guid);

        Gson gson = new Gson();
        peer.put(guid, gson.toJson(filesJson));
    }

    /**
     * Changes the name of the file. Enter olf file name and new file name
     *
     * @throws Exception needed for writeMetaData and readMetaData
     */
    public void move(String oldName, String newName) throws Exception {
        FilesJson md = this.readMetaData();
        boolean fileExists = false;
        for (int i = 0; i < md.getSize(); i++) {
            if (md.getFile(i).getName().equalsIgnoreCase(oldName)) {
                md.getFile(i).setName(newName);
                String timeWriteStamp = LocalDateTime.now().toString();
                md.getFile(i).setWriteTimeStamp(timeWriteStamp);
                fileExists = true;
            }
        }
        if (fileExists)
            System.out.println(oldName + " has been renamed to " + newName + ".\n");
        else
            System.out.println(oldName + " was not found in the file system, please check name using the command ls.\n");
    }

    /**
     * Creates a new file according to the port that chord is connected too
     *
     * @param fileName The name of the file you want to create
     * @throws Exception
     */
    public void create(String fileName) throws Exception {
        FileJson MetaFile = new FileJson();
        FilesJson md = this.readMetaData();
        MetaFile.setName(fileName);
        md.addFile(MetaFile);
        writeMetaData(md);
    }

    /**
     * list lists all files contained within the current connected port
     *
     * @return
     * @throws Exception
     */
    public void lists() throws Exception {
        FilesJson md = this.readMetaData();
        String FileList = " ";
        for (int i = 0; i < md.getSize(); i++) {
            String FileName = md.getFile(i).getName();
            FileList += " " + FileName + "\n";
        }
        System.out.println(FileList);
    }

    /**
     * Deletes the file, must specify index, if only one file then index 0
     *
     * @throws Exception required for read and write metadata
     */
    public void delete() throws Exception {
        FilesJson md = readMetaData();
        Scanner scan = new Scanner(System.in);
        int indexOfFile = scan.nextInt();
        FileJson file1 = md.getFile(indexOfFile);
        if (file1.getNumOfPages() > 0) {
            for (int i = 0; i < file1.getNumOfPages(); i++) {
                PagesJson page = file1.pages.get(i);
                long guid = page.getGUID();
                ChordMessageInterface peer = chord.locateSuccessor(guid);
                peer.delete(guid);
            }
        }
        writeMetaData(md);
    }


    /**
     * tail - to read from the last page
     *
     * @param filename
     * @return
     * @throws Exception
     */
    public RemoteInputFileStream tail(String filename) throws Exception {
        RemoteInputFileStream tail = null;
        for (int i = 0; i < MetaData.getSize(); i++) {
            if (MetaData.getFile(i).getName().equalsIgnoreCase(filename)) {
                ArrayList<PagesJson> pagesList = MetaData.getFile(i).getPages();
                int last = pagesList.size() - 1;
                PagesJson pageToRead = pagesList.get(last);
                String timeOfRead = LocalDateTime.now().toString();
                pageToRead.setReadTimeStamp(timeOfRead);
                MetaData.getFile(i).setReadTimeStamp(timeOfRead);
                Long pageGUID = md5(filename + pageToRead.getCreateTimeStamp());
                ChordMessageInterface peer = chord.locateSuccessor(pageGUID);
                tail = peer.get(pageGUID);
                writeMetaData(MetaData);
            }
        }
        return tail;
    }

    /**
     * to read from the first page
     *
     * @param filename
     * @return
     * @throws Exception
     */
    public RemoteInputFileStream head(String filename) throws Exception {
        RemoteInputFileStream head = null;
        for (int i = 0; i < MetaData.getSize(); i++) {
            if (MetaData.getFile(i).getName().equalsIgnoreCase(filename)) {
                ArrayList<PagesJson> pagesList = MetaData.getFile(i).getPages();
                int first = 0;
                PagesJson pageToRead = pagesList.get(first);
                String timeOfRead = LocalDateTime.now().toString();
                pageToRead.setReadTimeStamp(timeOfRead);
                MetaData.getFile(i).setReadTimeStamp(timeOfRead);
                Long pageGUID = md5(filename + pageToRead.getCreateTimeStamp());
                ChordMessageInterface peer = chord.locateSuccessor(pageGUID);
                head = peer.get(pageGUID);
                writeMetaData(MetaData);
            }
        }
        return head;
    }

    /**
     * Reads from the actual pages in chord
     *
     * @param fileName   Name of file being read
     * @param pageNumber Index of page being read
     * @return the data contained in the page
     * @throws Exception
     */
    public RemoteInputFileStream read(String fileName, int pageNumber) throws Exception {
        pageNumber--;
        RemoteInputFileStream InputStream = null;
        FilesJson md = readMetaData();
        for (int i = 0; i < md.getSize(); i++) {
            if (md.getFile(i).getName().equalsIgnoreCase(fileName)) {
                ArrayList<PagesJson> pagesList = md.getFile(i).getPages();
                for (int k = 0; k < pagesList.size(); k++) {
                    if (k == pageNumber) {
                        PagesJson pageToRead = pagesList.get(k);
                        String timeOfRead = LocalDateTime.now().toString();
                        pageToRead.setReadTimeStamp(timeOfRead);
                        md.getFile(i).setReadTimeStamp(timeOfRead);
                        Long pageGUID = pageToRead.guid;
                        ChordMessageInterface peer = chord.locateSuccessor(pageGUID);
                        InputStream = peer.get(pageGUID);
                    }
                }
                writeMetaData(md);
            }
        }
        return InputStream;
    }

    /**
     * Adds the specified data to the end of a page/file
     *
     * @param filename name of file
     * @param data     data being passed in
     * @throws Exception needed for read and write metadata
     */
    public void append(String filename, RemoteInputFileStream data) throws Exception {
        FilesJson md = this.readMetaData();
        for (int i = 0; i < md.getSize(); i++) {
            if (md.getFile(i).getName().equalsIgnoreCase(filename)) {
                Long sizeOfFile = (long) data.available();
                String timeOfAppend = LocalDateTime.now().toString();
                md.getFile(i).setWriteTimeStamp(timeOfAppend);
                md.getFile(i).addPageNumber(1);
                md.getFile(i).addSize(sizeOfFile);
                String objectName = filename + LocalDateTime.now();
                Long guid = md5(objectName);
                ChordMessageInterface peer = chord.locateSuccessor(guid);
                peer.put(guid, data);

                Long defaultZero = 0L;
                md.getFile(i).addPage(guid, sizeOfFile, timeOfAppend, "0", "0", 0);

            }
        }
        writeMetaData(md);
    }

    public void writePage(String filename, RemoteInputFileStream data, int pageNumber) throws Exception {
        FilesJson md = this.readMetaData();
        for (int i = 0; i < md.getSize(); i++) {
            if (md.getFile(i).getName().equalsIgnoreCase(filename)) {
                //check if there is data
                Long sizeOfFile = (long) data.available();

                //Getting timestamp
                String timeOfWrite = LocalDateTime.now().toString();
                //Set the  write time stamp
                md.getFile(i).setWriteTimeStamp(timeOfWrite);
                //Add data to specified page number
                md.getFile(i).addPageNumber(pageNumber);
                //Get size of file and specify
                md.getFile(i).addSize(sizeOfFile);

                //Recall that you obtain the guid of the page by applying MD5 to a unique feature, such as  file " filename + timestamp"
                String objectName = filename + LocalDateTime.now();
                Long guid = md5(objectName);
                ChordMessageInterface peer = chord.locateSuccessor(guid);
                peer.put(guid, data);

                Long defaultZero = 0L;
                md.getFile(i).addPage(guid, sizeOfFile, timeOfWrite, "0", "0", 0);
            }
        }
        writeMetaData(md);
    }

    public byte[] get(String fileName, long offset, int len) throws Exception {
        FilesJson metadata = readMetaData();
        for (int i = 0; i < metadata.getSize(); i++) {
            if (metadata.getFile(i).getName().equalsIgnoreCase(fileName)) {
                long guid = metadata.getFile(i).pages.get(0).guid;
                ChordMessageInterface peer = chord.locateSuccessor(guid);
                return peer.get(guid, offset, len);
            }
        }
        return new byte[8192];


    }

    public int getFileLength(String fileName) throws Exception {
        FilesJson metadata = readMetaData();
        for (int i = 0; i < metadata.getSize(); i++) {
            if (metadata.getFile(i).getName().equalsIgnoreCase(fileName)) {
                return Math.toIntExact(metadata.getFile(i).size);
            }
        }
        return 0;
    }

    public void deleteUsers() throws Exception {
        FilesJson metadata = readMetaData();
        for (int i = 0; i < metadata.getSize(); i++) {
            if (metadata.getFile(i).getName().equalsIgnoreCase("Users.json")) {
                long guid = metadata.getFile(i).pages.get(0).guid;
                ChordMessageInterface peer = chord.locateSuccessor(guid);
                peer.delete(guid);

            }
        }
    }

    /**MAP REDUCE FUNCTIONS START HERE*/

    private void createFile(String fileOutput, int interval, int size) throws Exception{ // Helper function
        int lower = 0;
        create(fileOutput);
        for (int i = 0; i <= size - 1; i++) {
            long page = md5(fileOutput + i);
            double lowerBoundInterval = (Math.floor(lower / 38)) + (lower % 38);

            //TODO appendEmptyPage needs to be created
            appendEmptyPage(fileOutput, page, lowerBoundInterval);
            lower += interval;
        }
    }

    int fileCounter = 0;
    public void runMapReduce(String fileInput, String fileOutput) throws Exception{
        int size = 0; // If the remote methods are in Chord, then size is a
        // variable of Chord
        FilesJson md= readMetaData();

        //need to complete onChordSize so we can determine what guid parameter

        chord.successor.onChordSize(guid, 1); // Obtain the number of nodes
        //while loop for size of network
        while (md.getSize() > 0) {
            Thread.sleep(10);
            int interval = 1936 / size; // Assuming 38 characters A-Z, 0-9, _, +.
            //1936 = 38*38
            createFile(fileOutput + ".map", interval, size);
            // mapreducer is an instance of the class that implements MapReduceInterface for each page in fileInput
            for (int i = 0; i < md.getSize(); i++) {
                if (md.getFile(i).getName().equalsIgnoreCase(fileInput)) {
                    ArrayList<PagesJson> inputList = md.getFile(i).getPages();

                    //iterate through pages of fileinput
                    for (int j = 0; j < inputList.size(); j++) {

                        PagesJson page = inputList.get(j);

                        fileCounter++;
                        ChordMessageInterface peer = chord.locateSuccessor(page.guid);

                        //TODO mapcontext and mapreducer
                        peer.mapContext(page.guid, mapreducer, this, fileOutput + ".map");
                    }


                }
                // Obtained from onNetworkSize after a full cycle
            }


            //while page ==0 set timer/ sleep thread.sleep for 10 milliseconds
            //while counter ==0 then sleep
            while (fileCounter == 0) {
                Thread.sleep(10);


                bulkTree(fileOutput + ".map");
                createFile(fileOutput, interval, size);
                //    for each page in fileOutput + ".map"{
                for (int i = 0; i < md.getSize(); i++) {
                    if (md.getFile(i).getName().equalsIgnoreCase(fileOutput + ".map")) {
                        ArrayList<PagesJson> pages = md.getFile(i).getPages();
                        for (int b = 0; b < pages.size(); b++) {
                            PagesJson page = pages.get(b);
                            fileCounter++;
                            ChordMessageInterface peer = chord.locateSuccessor(page.guid);
                            //need to complete reduceContext function to figure out what mapreducer parameter is
                            peer.reduceContext(page.guid, mapreducer, this, fileOutput);
                        }
                    }
                }
            }
        }
        ;
        while (fileCounter == 0) {
            Thread.sleep(10);
            bulkTree(fileOutput);
        }
    }

    private void bulkTree(String fileOutput) throws Exception { // Helper function
        int size = 0;
        // only using output file and all the pages of that
        // read fileoutput and check how many pages in it

        FilesJson md = readMetaData();

        for (int i = 0; i < md.getSize(); i++) {
            {
                if (md.getFile(i).getName().equalsIgnoreCase(fileOutput)) {
                    ArrayList<PagesJson> pagesList = md.getFile(i).getPages();
                    // iterate through pages of fileOutput and bulk
                    for (int j = 0; j < pagesList.size(); j++) {
                        long pageGuid = pagesList.get(j).getGUID();
                        long page = md5(fileOutput + i);
                        ChordMessageInterface peer = chord.locateSuccessor(pageGuid);
                        //TODO implement bulk
                        peer.bulk(page);
                    }
                }
            }
        }
    }

}