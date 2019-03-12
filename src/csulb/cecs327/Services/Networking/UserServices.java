package csulb.cecs327.Services.Networking;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import csulb.cecs327.Controllers.FrontEnd.AppUI;
import csulb.cecs327.Models.User;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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
}
