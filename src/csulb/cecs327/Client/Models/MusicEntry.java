package csulb.cecs327.Client.Models;

/**
 * This class assigns song entities
 */
public class MusicEntry {
    private Release release;
    private Artist artist;
    private Song song;

    public void setSong(Song song) {
        this.song = song;
    }

    public Song getSong() {
        return song;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public Release getRelease() {
        return release;
    }

    public int getId() { return this.release.getId(); }

    @Override
    public String toString() {
        return
                "Response{" +
                        "release = '" + release + '\'' +
                        ",artist = '" + artist + '\'' +
                        ",song = '" + song + '\'' +
                        "}";
    }
}

