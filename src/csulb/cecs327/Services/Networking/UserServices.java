package csulb.cecs327.Services.Networking;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import csulb.cecs327.Controllers.FrontEnd.AppUI;
import csulb.cecs327.Models.User;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UserServices {
    
    public String login(String user, String password){
        String userInfo = "";
        try(Reader reader = new FileReader("Users.json")) {
            Gson gson = new Gson();
            ArrayList<User> users = gson.fromJson(reader, new TypeToken<ArrayList<User>>(){}.getType());
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
        
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    
        return userInfo;
    }
    
    public String register(String userJson){
        Gson gson = new Gson();
        User newUser = gson.fromJson(userJson, new TypeToken<User>(){}.getType());
        try(Reader reader = new FileReader("Users.json")){
            boolean duplicateUserName = false;
            
            ArrayList<User> users = gson.fromJson(reader, new TypeToken<ArrayList<User>>(){}.getType());
            for (User j : users){
                if (j.getUserName().equals(newUser.getUserName())) {
                    duplicateUserName = true;
                    break;
                }
            }
            if (duplicateUserName){
                //todo: send back an error message
            }
            else{
                writeToUsersJson(newUser, gson, users);
            }
        } catch (FileNotFoundException | NullPointerException e1) {
            writeToUsersJson(newUser, gson, null);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "Success";
    }
    
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
