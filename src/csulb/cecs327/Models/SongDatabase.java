package csulb.cecs327.Models;

import java.util.ArrayList;

public class SongDatabase {
    private ArrayList<String> songList = new ArrayList<>();

    public SongDatabase() {
        initializeSongList();
    }

    public void initializeSongList() {
        songList.add("src/csulb/cecs327/Resources/music/Alan Walker - Faded (8D AUDIO).mp3");
        songList.add("src/csulb/cecs327/Resources/music/Bazzi - Mine (8D AUDIO).mp3");
        songList.add("src/csulb/cecs327/Resources/music/Noisestorm - Crab Rave (8D AUDIO).mp3");
        songList.add("src/csulb/cecs327/Resources/music/Panic! At The Disco - High Hopes (8D AUDIO).mp3");
        songList.add("src/csulb/cecs327/Resources/music/Twenty One Pilots - Stressed Out (8D AUDIO).mp3");
    }

    public ArrayList<String> getSongList() {
        return songList;
    }
}
