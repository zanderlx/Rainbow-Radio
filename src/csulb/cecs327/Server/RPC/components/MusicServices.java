package csulb.cecs327.Server.RPC.components;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import csulb.cecs327.Client.Models.MusicEntry;
import csulb.cecs327.Client.Services.SongSerializer;
import csulb.cecs327.DFS.DFS;
import csulb.cecs327.DFS.RemoteInputFileStream;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * This class allows the player to search for songs from the DFS
 */
public class MusicServices {
    private SongSerializer songSerializer = new SongSerializer();
//    private  = null;
    private Gson gson = new Gson();
    private RemoteInputFileStream inputStream;
    private DFS dfs;

    public MusicServices(DFS dfs) {
        this.dfs = dfs;
    }

    /**
     * Gets songs from the server
     * @param test used for testing purposes
     * @return json as results
     * @throws Exception
     */
    public String getSongsFromServer(String test) throws Exception {
        ArrayList<MusicEntry> results = new ArrayList<>();
        inputStream = dfs.read("music.json", 1);
        inputStream.connect();
        String json = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        ArrayList<MusicEntry> songs = gson.fromJson(json, new TypeToken<ArrayList<MusicEntry>>(){}.getType());
        for (int i = 0; i < 13; i++) {
            results.add(songs.get(i));
        }
        return gson.toJson(results);
    }

    /**
     * Makes an object of class type searcher
     * @param searchQuery what is being searched for
     * @return json of search results
     * @throws Exception threads
     */
    public String searchSongsFromServer(String searchQuery) throws Exception {
//        Set<MusicEntry> results = new HashSet<>();
        ConcurrentLinkedQueue<MusicEntry> results = new ConcurrentLinkedQueue<>();
        DFS.FilesJson metadata = dfs.readMetaData();
//        int numberOfThreads = metadata.getFile(0).getNumOfPages();

        for (int i = 1; i <= 10; i++) {
            Thread searcher;
            inputStream = dfs.read("music.json", i);
            inputStream.connect();
            System.out.println("Searching Page " + i);
            String json = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
            ArrayList<MusicEntry> songs = gson.fromJson(json, new TypeToken<ArrayList<MusicEntry>>(){}.getType());
    
            Searcher search = new Searcher(searchQuery, songs, results);
            searcher = new Thread(search);
            searcher.start();
        }

        return gson.toJson(results);
    }

}

/**
 * This class allows for multiple threads to be ran so search efficiency is increased
 * Parallel searching
 */
class Searcher implements Runnable {
    private String searchQuery;
    private ArrayList<MusicEntry> songs;
    private ConcurrentLinkedQueue<MusicEntry> results;


    public Searcher(String searchQuery, ArrayList<MusicEntry> songs, ConcurrentLinkedQueue<MusicEntry> results){
        this.searchQuery = searchQuery;
        this.songs = songs;
        this.results = results;
    }

    /**
     * Runs a instance of the thread
     */
    public void run() {
        for (MusicEntry entry : songs) {
            if (results.size() > 13)
                break;
            // Check if song was found
            if (entry.getSong().getTitle().toLowerCase().contains(searchQuery.toLowerCase()))
                results.add(entry);
            // Check if artist was found
            if (entry.getArtist().getName().toLowerCase().contains(searchQuery.toLowerCase()))
                results.add(entry);
            // Check if album was found
            if (entry.getRelease().getName().toLowerCase().contains(searchQuery.toLowerCase()))
                results.add(entry);
            // Check if genre was found
            if (entry.getArtist().getTerms().toLowerCase().contains(searchQuery.toLowerCase()))
                results.add(entry);
        }
    }

}
