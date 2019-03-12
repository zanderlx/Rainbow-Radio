package csulb.cecs327.Services.Networking;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import csulb.cecs327.Models.SongTableEntry;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class MusicServices {

    public MusicServices() {

    }

    public ArrayList<SongTableEntry> getSongsFromServer() {
        Gson gson = new Gson();
        ArrayList<SongTableEntry> songs = null;
        try (Reader reader = new FileReader("music.json")) {
            songs = gson.fromJson(reader, new TypeToken<ArrayList<SongTableEntry>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return songs;
    }

}
