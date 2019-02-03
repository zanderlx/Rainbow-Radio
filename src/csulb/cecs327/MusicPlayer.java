package csulb.cecs327;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class MusicPlayer {
    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;
    private FileInputStream songStream;
    private  Player songPlayer;
    private final Object playerLock = new Object();
    private int playerStatus = NOTSTARTED;

    public MusicPlayer(String song) {
        try {
            songStream = new FileInputStream(song);
            songPlayer = new Player(songStream);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void play() {
        synchronized (playerLock) {
            switch (playerStatus) {
                case NOTSTARTED:
                    start();
                    break;
                case PAUSED:
                    resume();
                    break;
                case FINISHED:
                    start();
                    break;
                default:
                    break;
            }
        }
    }

    public void start() {
        final Runnable r = new Runnable() {
            public void run() {
                playInternal();
            }
        };
        final Thread t = new Thread(r);
        t.setPriority(Thread.MAX_PRIORITY);
        playerStatus = PLAYING;
        t.start();
    }

    public boolean pause() {
        synchronized (playerLock) {
            if (playerStatus == PLAYING) {
                playerStatus = PAUSED;
            }
            return playerStatus == PAUSED;
        }
    }

    public boolean resume() {
        synchronized (playerLock) {
            if (playerStatus == PAUSED) {
                playerStatus = PLAYING;
                playerLock.notifyAll();
            }
            return playerStatus == PLAYING;
        }
    }

    public void stop() {
        synchronized (playerLock) {
            playerStatus = FINISHED;
            playerLock.notifyAll();
        }
    }

    private void playInternal() {
        while (playerStatus != FINISHED) {
            try {
                if (!songPlayer.play(1)) {
                    break;
                }
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
}
