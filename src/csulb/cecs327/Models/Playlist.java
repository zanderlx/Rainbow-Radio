package csulb.cecs327.Models;

import java.util.ArrayList;

/**
 * This class is responsible for handling the playlists and creating an arraylist of songs
 */
public class Playlist {
    private ArrayList<SongTableEntry> listOfSongs;
    private String nameOfPlaylist;
    
    public Playlist(String name) {
        this.listOfSongs = new ArrayList<>();
        this.nameOfPlaylist = name;
    }
    
    public ArrayList<SongTableEntry> getListOfSongs() {
        return listOfSongs;
    }
    
    public void setListOfSongs(ArrayList<SongTableEntry> listOfSongs) {
        this.listOfSongs = listOfSongs;
    }

    public String getNameOfPlaylist() {
        return nameOfPlaylist;
    }
    
    public void setNameOfPlaylist(String nameOfPlaylist) {
        this.nameOfPlaylist = nameOfPlaylist;
    }
    public void addSongTableEntry(SongTableEntry songTableEntry){listOfSongs.add(songTableEntry);}
    
   
    
    @Override
    public String toString() {
        return nameOfPlaylist;
    }
}
