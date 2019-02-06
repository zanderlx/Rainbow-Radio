package csulb.cecs327;

import csulb.cecs327.Controllers.FrontEnd.AppUI;
import csulb.cecs327.Models.User;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame login = new JFrame("Login Page");
        // Dummy user
        User user = new User("admin", "password", "email");
        login.setContentPane(new AppUI(user));
        login.setSize(1024, 720);
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login.setVisible(true);
        login.setResizable(false);
        login.pack();
    }

}
