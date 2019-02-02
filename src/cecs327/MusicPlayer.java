package cecs327;

import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

public class MusicPlayer {

    private FileInputStream songStream;
    private  AdvancedPlayer songPlayer;
    private ArrayList<String> songList = new ArrayList<>();
    private int currentSong;

    public MusicPlayer() {
        try {
            initializeSongList();
            currentSong = new Random().nextInt(songList.size());
            songStream = new FileInputStream(songList.get(currentSong));
            songPlayer = new AdvancedPlayer(songStream);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void play() {
        try {
            songPlayer.play();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void pause() {
        // TODO: Implement this function
    }

    public void stop() {
        songPlayer.stop();
    }

    public void next() {
        currentSong = (currentSong == songList.size() - 1) ? 0 : currentSong++;
        play();
    }

    public void previous() {
        currentSong = (currentSong == 0) ? currentSong = songList.size() - 1 : currentSong--;
        play();
    }

    public void initializeSongList() {
        songList.add("music/Alan Walker - Faded (8D AUDIO).mp3");
        songList.add("music/Bazzi - Mine (8D AUDIO).mp3");
        songList.add("music/Noisestorm - Crab Rave (8D AUDIO).mp3");
        songList.add("music/Panic! At The Disco - High Hopes (8D AUDIO).mp3");
        songList.add("music/Twenty One Pilots - Stressed Out (8D AUDIO).mp3");
    }

}
