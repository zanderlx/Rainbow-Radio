package csulb.cecs327;

import java.util.ArrayList;

public class SongDatabase {
    private ArrayList<String> songList = new ArrayList<>();

    public SongDatabase() {
        initializeSongList();
    }

    public void initializeSongList() {
        songList.add("music/Alan Walker - Faded (8D AUDIO).mp3");
        songList.add("music/Bazzi - Mine (8D AUDIO).mp3");
        songList.add("music/Noisestorm - Crab Rave (8D AUDIO).mp3");
        songList.add("music/Panic! At The Disco - High Hopes (8D AUDIO).mp3");
        songList.add("music/Twenty One Pilots - Stressed Out (8D AUDIO).mp3");
    }

    public ArrayList<String> getSongList() {
        return songList;
    }
}
