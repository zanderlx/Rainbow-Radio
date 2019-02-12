package csulb.cecs327.Models;

import java.util.ArrayList;

public class Playlist {
    private ArrayList<String> listOfSongs;
    private String nameOfPlaylist;
    
    public Playlist() {
        this.listOfSongs = new ArrayList<>();
    }
    
    public ArrayList<String> getListOfSongs() {
        return listOfSongs;
    }
    
    public void setListOfSongs(ArrayList<String> listOfSongs) {
        this.listOfSongs = listOfSongs;
    }
    public void addSong(String name){
        listOfSongs.add(name);
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
