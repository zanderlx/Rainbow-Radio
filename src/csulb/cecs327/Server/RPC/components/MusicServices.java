package csulb.cecs327.Server.RPC.components;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import csulb.cecs327.Client.Models.MusicEntry;
import csulb.cecs327.Client.Services.SongSerializer;
import csulb.cecs327.DFS.DFS;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MusicServices {
    private SongSerializer songSerializer = new SongSerializer();
    ArrayList<MusicEntry> songs = null;
    Gson gson = new Gson();

    public MusicServices(DFS dfs) {
        try (Reader reader = new FileReader("src/csulb/cecs327/Server/Files/music.json")) {
            songs = gson.fromJson(reader, new TypeToken<ArrayList<MusicEntry>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSongsFromServer(String test) {
        ArrayList<MusicEntry> results = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            results.add(songs.get(i));
        }
        return gson.toJson(results);
    }

    public String searchSongsFromServer(String searchQuery) {
        Set<MusicEntry> results = new HashSet<>();

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



        return gson.toJson(results);
    }

}
