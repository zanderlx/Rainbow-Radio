package csulb.cecs327.Models;

import java.util.ArrayList;

public class Playlist {
    private Object[] listOfSongs;
    private String nameOfPlaylist;
    
    public Playlist(String name) {
        this.listOfSongs = new Object[]{};
        this.nameOfPlaylist = name;
    }
    
    public Object[] getListOfSongs() {
        return listOfSongs;
    }
    
    public void setListOfSongs(Object[] listOfSongs) {
        this.listOfSongs = listOfSongs;
    }

    public String getNameOfPlaylist() {
        return nameOfPlaylist;
    }
    
    public void setNameOfPlaylist(String nameOfPlaylist) {
        this.nameOfPlaylist = nameOfPlaylist;
    }
    
    @Override
    public String toString() {
        return nameOfPlaylist;
    }
}
