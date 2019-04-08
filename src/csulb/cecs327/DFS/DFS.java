package csulb.cecs327.DFS;

import java.time.LocalDateTime;
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
        String CreateTimeStamp;
        String WriteTimeStamp;
        String ReadTimeStamp;
        int counter;
        //int pageNumber;
        public PagesJson(Long guid, Long size, String CreationTimeStamp, String ReadTimeStamp, String WriteTimeStamp,int counter) {
            this.guid = guid;
            this.size = size;
            this.CreateTimeStamp = CreationTimeStamp;
            this.ReadTimeStamp = ReadTimeStamp;
            this.WriteTimeStamp = WriteTimeStamp;
            this.counter = counter;
        }
        // getters
        public long getSize()
        {

            return size;
        }
        public long getGUID()
        {

            return this.guid;
        }
        public String getCreateTimeStamp(){
            return this.CreateTimeStamp;
        }
        public int getCounter(){
            return this.counter;
        }
        // setters
        public void setSize(Long size){
            this.size = size;
        }
        public void setGuid(Long guid)
        {
            this.guid = guid;
        }
        public void setCreateTimeStamp(String CreateTimeStamp)
        {
            this.CreateTimeStamp = CreateTimeStamp ;
        }

        public void setWriteTimeStamp(String WriteTimeStamp)
        {
            this.WriteTimeStamp = WriteTimeStamp;
        }

        public void setReadTimeStamp(String ReadTimeStamp)
        {
            this.ReadTimeStamp = ReadTimeStamp;
        }
        public void setCounter(int counter)
        {
            this.counter = counter;
        }
    };

    /**
     * This class is for Meta File
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
        public void addPage(Long guid, Long size, String CreateTimeStamp, String ReadTimeStamp, String WriteTimeStamp,int counter)
        {
            PagesJson newPage = new PagesJson(guid, size,CreateTimeStamp,ReadTimeStamp, WriteTimeStamp,counter);
            pages.add(newPage);
        }
        // Getters
        public String getName()
        {
            return this.name;
        }
        public Long getSize(){
            return this.size;
        }
        public int getMaxPageNumber(){
            return this.MaxPageNumber;
        }
        public int getPageNumber(){
            return this.PageNumber;
        }
        public ArrayList<PagesJson> getPages() {
            return pages;
        }
        public String getCreateTimeStamp() {
            return CreateTimeStamp;
        }

        // Setters
        public void setName(String name)
        {
            this.name = name;
        }
        public void setSize(Long size){
            this.size = size;
        }
        public void setMaxPageNumber(int MaxPageNumber){
            this.MaxPageNumber = MaxPageNumber;
        }
        public int getNumOfPages()
        {
            return pages.size();
        }
        public void addSize(Long size){
            this.size += size;
        }
        public void setCounter(int counter){
            this.counter = counter;
        }
        public void setPageNumber(int PageNumber){
            this.PageNumber = PageNumber;
        }
        public void addPageNumber( int PageNumber){
            this.PageNumber += PageNumber;
        }
        public void setWriteTimeStamp(String WriteTimeStamp){
            this.WriteTimeStamp = WriteTimeStamp;
        }
        public void setReadTimeStamp(String ReadTimeStamp){
            this.ReadTimeStamp = ReadTimeStamp;
        }


        /*public void printListOfPages()
        {
            System.out.printf("\n%-5s%-15s%-15s\n", "", "Page Number", "GUID");
            for (int i = 0; i < pages.size(); i++)
            {
                PagesJson temp = pages.get(i);

                System.out.printf("%-5s%-15s%-15d\n", "", temp.getPageNumber(), temp.getGUID());
            }
            System.out.println("");
        }*/
        // getters
        // setters
    };

    /**
     * This class is for Meta Data
     */
    public class FilesJson {
        List<FileJson> metaFile;
        //ArrayList<FileJson> file = new ArrayList<FileJson>();
        public FilesJson() {

            metaFile = new ArrayList<FileJson>();
        }
        // Getter
        public FileJson getFile(int index){
            return metaFile.get(index);
        }
        public int getSize(){
            return metaFile.size();
        }
        public void addFile(FileJson newFile) {
            metaFile.add(newFile);


        }


  
        public void clear()
        {
            metaFile.clear();
        }
        // getters
        // setters
    };

    int port;
    Chord chord;

    FilesJson MetaData;
    

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
     *
     */
    public void join(String Ip, int port) throws Exception {
        chord.joinRing(Ip, port);
        chord.print();
    }

    /**
     * leave the chord
     *
     */
    public void leave() throws Exception {
        chord.leave();
    }

    /**
     * print the status of the peer in the chord
     *
     */
    public void print() throws Exception {
        chord.print();
    }

    /**
     * readMetaData read the metadata from the chord
     *
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
     *
     */
    public void writeMetaData(FilesJson filesJson) throws Exception {
        long guid = md5("Metadata");
        ChordMessageInterface peer = chord.locateSuccessor(guid);

        Gson gson = new Gson();
        peer.put(guid, gson.toJson(filesJson));
    }

    /**
     * Change Name
     *
     */
    public void move(String oldName, String newName) throws Exception {

        // TODO: Change the name in Metadata
        // Write Metadata

        // Setting temp JsonObject
        //FilesJson md = readMetaData();
        //if (md.fileExists(oldName))
        //{
          //  FileJson metafile = md.getFile(oldName);
            //metafile.setName(newName);
            //writeMetaData(md);
        //}
        //else
          //  System.out.println("That file does not exist. Try again.");


    }

    /**
     * create an empty file
     */
    public void create(String fileName) throws Exception {
        FileJson MetaFile= new FileJson();
        MetaFile.setName(fileName);
        MetaData.addFile(MetaFile);
        writeMetaData(MetaData);
    }

    /**
     * list
     * @return
     * @throws Exception
     */
    public void lists() throws Exception {

    }


    /**
     * delete file
     */
    public void delete(String fileName) throws Exception {

    }

    public void tail(String filename) throws Exception
    {

    }
    public void head(String filename) throws Exception
    {

    }

    public RemoteInputFileStream read(String fileName, int pageNumber) throws Exception {
        pageNumber--;
        RemoteInputFileStream InputStream = null;
        FilesJson md = readMetaData();
        for(int i = 0; i < MetaData.getSize(); i++){
            if(MetaData.getFile(i).getName().equalsIgnoreCase(fileName)){
                ArrayList<PagesJson> pagesList = MetaData.getFile(i).getPages();
                for(int k = 0; k < pagesList.size(); k++){
                    if(k == pageNumber){
                        PagesJson pageToRead = pagesList.get(k);
                        String timeOfRead = LocalDateTime.now().toString();
                        pageToRead.setReadTimeStamp(timeOfRead);
                        MetaData.getFile(i).setReadTimeStamp(timeOfRead);
                        Long pageGUID = md5(fileName + pageToRead.getCreateTimeStamp());
                        ChordMessageInterface peer = chord.locateSuccessor(pageGUID);
                        InputStream = peer.get(pageGUID);
                    }
                }
                writeMetaData(MetaData);
            }
        }
        return InputStream;
    }

    public void append(String filename, RemoteInputFileStream data) throws Exception
    {

        for(int i = 0; i < MetaData.getSize();i++)
        {
            {

                //update information in the file we are going to append
                //data.connect();
                //This is used to get the size of the file
                Long sizeOfFile = new Long(data.available());
                String timeOfAppend = LocalDateTime.now().toString();
                MetaData.getFile(i).setWriteTimeStamp(timeOfAppend);
                MetaData.getFile(i).addPageNumber(1);
                MetaData.getFile(i).addSize(sizeOfFile);

                //create the page metadata information
                String objectName = filename + LocalDateTime.now();
                Long guid = md5(objectName);

                ChordMessageInterface peer = chord.locateSuccessor(guid);
                peer.put(guid, data);
                //chord locate successor , then put

                //filesJson.getFileJson(i).addPageInfo(guid, size, creationTS, readTS, writeTs, referenceCount);
                Long defaultZero = new Long(0);
                MetaData.getFile(i).addPage(guid,sizeOfFile,timeOfAppend,"0","0",0);

            }

        }
        writeMetaData(MetaData);


    }
}