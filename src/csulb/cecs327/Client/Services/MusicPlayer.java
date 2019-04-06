package csulb.cecs327.Client.Services;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import java.io.InputStream;

/**
 * This class handles the playing of music
 */
public class MusicPlayer {
    // Player statuses
    private final static int NOT_STARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;

    // Player attributes
    private  Player songPlayer;
    private final Object playerLock = new Object();
    private int playerStatus = NOT_STARTED;
    private int songLength;

    public MusicPlayer(InputStream songStream) {
        try {
            songPlayer = new Player(songStream);
            songLength = songStream.available();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Starts playing the song
     */
    public void play() {
        synchronized (playerLock) {
            switch (playerStatus) {
                case NOT_STARTED: start(); break;
                case PAUSED: resume(); break;
                case FINISHED: start(); break;
                default: break;
            }
        }
    }

    /**
     * Starts playing the chosen music
     */
    public void start() {
        final Runnable r = this::playInternal;
        final Thread t = new Thread(r);
        t.setPriority(Thread.MAX_PRIORITY);
        playerStatus = PLAYING;
        t.start();
    }

    /**
     * Pauses the song
     * @return
     */
    public boolean pause() {
        synchronized (playerLock) {
            if (playerStatus == PLAYING) playerStatus = PAUSED;
            return playerStatus == PAUSED;
        }
    }

    /**
     * Plays a paused song
     * @return
     */
    public boolean resume() {
        synchronized (playerLock) {
            if (playerStatus == PAUSED) {
                playerStatus = PLAYING;
                playerLock.notifyAll();
            }
            return playerStatus == PLAYING;
        }
    }

    /**
     * Stops playing the song
     */
    public void stop() {
        synchronized (playerLock) {
            playerStatus = FINISHED;
            playerLock.notifyAll();
        }
    }

    /**
     * Calls the song method for internal player
     */
    private void playInternal() {
        while (playerStatus != FINISHED) {
            try {
                if (!songPlayer.play(1)) break;
            } catch (final JavaLayerException e) {
                break;
            }
            // check if paused or terminated
            synchronized (playerLock) {
                while (playerStatus == PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (final InterruptedException e) {
                        // terminate player
                        break;
                    }
                }
            }
        }
        close();
    }

    /**
     * Cleanup method
     */
    public void close() {
        synchronized (playerLock) {
            playerStatus = FINISHED;
        }
        try {
            songPlayer.close();
        } catch (final Exception e) {
            // ignore, we are terminating anyway
        }
    }

    public int getSongLength() {
        return songLength;
    }

}
