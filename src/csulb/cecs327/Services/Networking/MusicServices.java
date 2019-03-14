package csulb.cecs327.Services.Networking;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import csulb.cecs327.Models.MusicEntry;
import csulb.cecs327.Models.Song;
import csulb.cecs327.Models.SongTableEntry;
import csulb.cecs327.Services.SongSerializer;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MusicServices {
    private SongSerializer songSerializer = new SongSerializer();
    ArrayList<MusicEntry> songs = null;
    Gson gson = new Gson();

    public MusicServices() {

        try (Reader reader = new FileReader("src/csulb/cecs327/Services/Networking/music.json")) {
            songs = gson.fromJson(reader, new TypeToken<ArrayList<MusicEntry>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSongsFromServer(String test) {
        ArrayList<MusicEntry> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(songs.get(i));
        }
        return gson.toJson(results);
    }

}
