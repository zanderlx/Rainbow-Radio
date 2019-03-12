package csulb.cecs327;

import csulb.cecs327.Controllers.FrontEnd.AppUI;
import csulb.cecs327.Controllers.FrontEnd.LoginPage;
import csulb.cecs327.Models.User;
import csulb.cecs327.Services.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        Client client1 = new Client();
        Client client2 = new Client();
        Client client3 = new Client();
        client1.run();
        client2.run();
        client3.run();
        
    }
}
    

