package csulb.cecs327.Models;

import java.util.HashMap;

public class User {
    private String userName;
    private String password;
    private String email;
    private HashMap<Integer, Integer> songHistory;
    
    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        songHistory = new HashMap<>();
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
    
    public void addSongHistory(int songID){
        songHistory.merge(songID, 1, Integer::sum);
    }
    
    public HashMap<Integer, Integer> getSongHistory(){
        return songHistory;
    }
    
}
