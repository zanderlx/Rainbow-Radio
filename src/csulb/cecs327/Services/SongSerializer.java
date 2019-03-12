package csulb.cecs327.Services;

import com.google.gson.Gson;
import csulb.cecs327.Models.*;

import java.io.BufferedReader;
import java.io.FileReader;

public class SongSerializer {

    private Gson gson;
    private String path;
    private MusicEntry[] rootObjects;
    private BufferedReader br;

    /**
     * Parses the song
     */
    public SongSerializer() {
        try {
            gson = new Gson();
            path = "C:\\Users\\ZanderLx\\Desktop\\CECS-327\\Rainbow-Radio\\src\\csulb\\cecs327\\Services\\Networking\\music.json";
            br = new BufferedReader(new FileReader(path));
            rootObjects = gson.fromJson(br, MusicEntry[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MusicEntry[] getRootObjects() {
        return rootObjects;
    }
}
