package csulb.cecs327.Client;

import csulb.cecs327.Client.Views.LoginPage;

import javax.swing.*;

/**
 * This thread runs a single UI
 */
public class Client extends Thread {
    @Override
    public void run() {
        JFrame login = new JFrame("Rainbow Radio");
        login.setContentPane(new LoginPage());
        login.setSize(1024, 720);
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login.setResizable(false);
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        login.pack();
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}

