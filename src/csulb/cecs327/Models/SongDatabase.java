package csulb.cecs327.Models;

import java.util.ArrayList;

/**
 * This class pulls all songs and initializes them into an arraylist
 */
public class SongDatabase {
    private ArrayList<String> songList = new ArrayList<>();

    public SongDatabase() {
        initializeSongList();
    }

    public void initializeSongList() {
        songList.add("src/csulb/cecs327/Resources/music/300848");
        songList.add("src/csulb/cecs327/Resources/music/287650");
        songList.add("src/csulb/cecs327/Resources/music/Noisestorm - Crab Rave (8D AUDIO).mp3");
        songList.add("src/csulb/cecs327/Resources/music/Panic! At The Disco - High Hopes (8D AUDIO).mp3");
        songList.add("src/csulb/cecs327/Resources/music/Twenty One Pilots - Stressed Out (8D AUDIO).mp3");
        songList.add("src/csulb/cecs327/Resources/music/300822");
        songList.add("src/csulb/cecs327/Resources/music/490183.mp3");
        songList.add("src/csulb/cecs327/Resources/music/358182");
        songList.add("src/csulb/cecs327/Resources/music/815863");
        songList.add("src/csulb/cecs327/Resources/music/605134");
        songList.add("src/csulb/cecs327/Resources/music/463514");
        songList.add("src/csulb/cecs327/Resources/music/663761");
        songList.add("src/csulb/cecs327/Resources/music/Empire State Of Mind.mp3");
        songList.add("src/csulb/cecs327/Resources/music/Eye Of The Tiger.mp3");
        songList.add("src/csulb/cecs327/Resources/music/Fireflies.mp3");
        songList.add("src/csulb/cecs327/Resources/music/Kanye West - Stronger.mp3");
        songList.add("src/csulb/cecs327/Resources/music/My Way.mp3");
        songList.add("src/csulb/cecs327/Resources/music/Oh My God.mp3");
        songList.add("src/csulb/cecs327/Resources/music/Rack City.mp3");
        songList.add("src/csulb/cecs327/Resources/music/Right Round.mp3");
        songList.add("src/csulb/cecs327/Resources/music/The Next Episode (San Holo Remix).mp3");
        songList.add("src/csulb/cecs327/Resources/music/We Are Never Getting Back Together.mp3");
        songList.add("src/csulb/cecs327/Resources/music/We Are Young.mp3");
        songList.add("src/csulb/cecs327/Resources/music/Wide Awake.mp3");


    }

    public ArrayList<String> getSongList() {
        return songList;
    }
}
