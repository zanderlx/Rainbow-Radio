package csulb.cecs327;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new LoginPage());
        frame.setSize(1024, 720);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }

}
