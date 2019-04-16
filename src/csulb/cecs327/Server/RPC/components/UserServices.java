package csulb.cecs327.Server.RPC.components;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import csulb.cecs327.Client.Models.User;
import csulb.cecs327.DFS.DFS;
import csulb.cecs327.DFS.RemoteInputFileStream;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * These are the services to store and update User
 */
public class UserServices {
    DFS dfs;
    public UserServices(DFS dfs) {
        this.dfs = dfs;
    }
    
    /**
     * Returns a user for given username and password
     * @param user username
     * @param password password
     * @return The user in json
     */
    public String login(String user, String password){
        String userInfo = "";
        try {
            Gson gson = new Gson();
            InputStream inputStream = dfs.read("Users.json", 1);
            ((RemoteInputFileStream) inputStream).connect();
            String json = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
            ArrayList<User> users = gson.fromJson(json, new TypeToken<ArrayList<User>>(){}.getType());
            boolean found = false;
            for (int i = 0; i < users.size(); i++)
            {
                User j = users.get(i);
                if (j.getUserName().equals(user))
                {
                    found = true;
                    if (j.getPassword().equals(password)){
                        userInfo = gson.toJson(j);
                    }
                    else {
                        userInfo = "Incorrect Password";
                        break;
                    }
                
                }
            }
            if (!found)
                userInfo = "User not found";
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try(Reader reader = new FileReader("Users.json")) {
//            Gson gson = new Gson();
//            ArrayList<User> users = gson.fromJson(reader, new TypeToken<ArrayList<User>>(){}.getType());
//            boolean found = false;
//            for (int i = 0; i < users.size(); i++)
//            {
//                User j = users.get(i);
//                if (j.getUserName().equals(user))
//                {
//                    found = true;
//                    if (j.getPassword().equals(password)){
//                        userInfo = gson.toJson(j);
//                    }
//                    else {
//                        userInfo = "Incorrect Password";
//                        break;
//                    }
//
//                }
//            }
//            if (!found)
//                userInfo = "User not found";
//
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
    
        return userInfo;
    }
    
    /**
     * Adds given user to user.json
     * @param userJson The user as a json file
     * @return confirmation of success
     */
    public String register(String userJson){
        try {
            Gson gson = new Gson();
            User newUser = gson.fromJson(userJson, new TypeToken<User>(){}.getType());
            InputStream inputStream = dfs.read("Users.json", 0);
            ((RemoteInputFileStream) inputStream).connect();
            String json = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
            ArrayList<User> users = gson.fromJson(json, new TypeToken<ArrayList<User>>(){}.getType());
            boolean duplicateUserName= false;
            for (User j : users){
                if(j.getUserName().equals(newUser.getUserName())){
                    duplicateUserName = true;
                    break;
                }
            }
            if(duplicateUserName){
            
            }
            else{
                writeToUsersJson(newUser, gson, users);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Gson gson = new Gson();
//        User newUser = gson.fromJson(userJson, new TypeToken<User>(){}.getType());
//        try(Reader reader = new FileReader("Users.json")){
//            boolean duplicateUserName = false;
//
//            ArrayList<User> users = gson.fromJson(reader, new TypeToken<ArrayList<User>>(){}.getType());
//            for (User j : users){
//                if (j.getUserName().equals(newUser.getUserName())) {
//                    duplicateUserName = true;
//                    break;
//                }
//            }
//            if (duplicateUserName){
//                //todo: send back an error message
//            }
//            else{
//                writeToUsersJson(newUser, gson, users);
//            }
//        } catch (FileNotFoundException | NullPointerException e1) {
//            writeToUsersJson(newUser, gson, null);
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
        return "Success";
    }
    
    /**
     * Updates the user
     * @param user the changed user
     */
    public void updateUser(String user){
        Gson gson = new Gson();
        User updatedUser = gson.fromJson(user, new TypeToken<User>(){}.getType());
        
        try(Reader reader = new FileReader("Users.json")){
    
            ArrayList<User> users = gson.fromJson(reader,new TypeToken<ArrayList<User>>(){}.getType());
            for (int i = 0; i < users.size(); i++){
                if(users.get(i).getUserName().equals(updatedUser.getUserName())){
                    users.set(i, updatedUser);
                    break;
                }
            }
            try(Writer writer = new FileWriter("Users.json")){
                gson.toJson(users, writer);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (NullPointerException | IOException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * Handles writing user to Users.json
     * @param newUser user to be written
     * @param gson used to serialize
     * @param list the list of users
     */
    private void writeToUsersJson(User newUser, Gson gson, ArrayList<User> list) {
        if (list == null)
            list = new ArrayList<>();
        try(Writer writer = new FileWriter("Users.json")){
            list.add(newUser);
            gson.toJson(list, writer);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
