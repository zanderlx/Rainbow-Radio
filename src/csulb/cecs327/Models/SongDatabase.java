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
        songList.add("src/csulb/cecs327/Resources/music/All falls down.mp3");
        songList.add("src/csulb/cecs327/Resources/music/490183.mp3");
        songList.add("src/csulb/cecs327/Resources/music/Down On Me.mp3");
        songList.add("src/csulb/cecs327/Resources/music/Drake - Headlines.mp3");
        songList.add("src/csulb/cecs327/Resources/music/Drake - Take Care (ft. Rihanna).mp3");
        songList.add("src/csulb/cecs327/Resources/music/Drive.mp3");
        songList.add("src/csulb/cecs327/Resources/music/Dynamite.mp3");
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
