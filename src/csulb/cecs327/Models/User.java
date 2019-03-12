package csulb.cecs327.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/**
 * This class is responsible for parsing in and passing user data
 */
public class User {
    private String userName;
    private String password;
    private String email;
    private ArrayList<Playlist> playLists;
    
    public User(String userName, String password, String email, ArrayList<Playlist> playLists) {
        this.userName = userName;
        this.password = password;
        this.email = email;
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
