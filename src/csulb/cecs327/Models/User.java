package csulb.cecs327.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class User {
    private String userName;
    private String password;
    private String email;
    //private HashMap<Integer, Integer> songHistory;
    private ArrayList<Playlist> playLists;
    
    public User(String userName, String password, String email, ArrayList<Playlist> playLists) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        if(playLists == null)
            this.playLists = new ArrayList<>();
        else
            this.playLists = playLists;
    }
    
    public ArrayList<Playlist> getPlayLists() {
        return playLists;
    }
    
    public void setPlayLists(ArrayList<Playlist> playLists) {
        this.playLists = playLists;
    }
    
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", playLists=" + playLists +
                '}';
    }
}
