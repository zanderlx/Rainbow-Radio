package csulb.cecs327;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame login = new JFrame("LoginPage");
        login.setContentPane(new AppUI());
        login.setSize(1024, 720);
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login.setVisible(true);
        login.pack();
    }

}
