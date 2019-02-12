package csulb.cecs327;

import csulb.cecs327.Controllers.FrontEnd.AppUI;
import csulb.cecs327.Controllers.FrontEnd.LoginPage;
import csulb.cecs327.Models.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        JFrame login = new JFrame("Rainbow Radio");
        // Dummy user
        User user = new User("admin", "password", "email", new ArrayList<>());
        login.setContentPane(new LoginPage());
        login.setSize(1024, 720);
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login.setResizable(false);
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        login.pack();
    }

}
