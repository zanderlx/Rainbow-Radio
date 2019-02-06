/*
 * Created by JFormDesigner on Fri Feb 01 17:30:38 PST 2019
 */

package csulb.cecs327.Controllers.FrontEnd;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import csulb.cecs327.Models.User;
import csulb.cecs327.Services.UserSerializer;
import net.miginfocom.swing.*;

/**
 * @author PRAMOD REDDY CHAMALA
 */
public class LoginPage extends JPanel {
    public LoginPage() {
        initComponents();
    }

  
    private void logInButtonMouseClicked(MouseEvent e) {
        String userName = usernameField.getText();
        char[] password = passwordField.getPassword();
        
        if (userName.equals(""))
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Verify that the username is entered.");
        else if (password.length == 0)
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Verify that the password is entered.");
        else{
            try(Reader reader = new FileReader("Users.json")) {
                Gson gson = new GsonBuilder().registerTypeAdapter(User.class, new UserSerializer()).create();
                List<User> list = gson.fromJson(reader, new TypeToken<List<User>>() {
                }.getType());
                boolean found = false;
                for (User j : list)
                {
                    if (j.getUserName().equals(userName))
                    {
                        if (Arrays.equals(password, j.getPassword().toCharArray())){
                            JFrame root = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
                            root.setContentPane(new AppUI(j));
                            root.pack();
                             found = true;
                        }
                        else
                            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Password incorrect.");
                    }
                }
                if (!found)
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Username not registered.");
                
            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "No users detected. Please register.");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        
    }

    private void registerButtonMouseClicked(MouseEvent e) {
        RegisterDialog dialog = new RegisterDialog();
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Lexzander Saplan
        userLabel = new JLabel();
        usernameField = new JFormattedTextField();
        passLabel = new JLabel();
        passwordField = new JPasswordField();
        registerButton = new JButton();
        logInButton = new JButton();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- userLabel ----
        userLabel.setText("Username");
        add(userLabel, "cell 8 7");
        add(usernameField, "cell 9 7");

        //---- passLabel ----
        passLabel.setText("Password");
        add(passLabel, "cell 8 8");
        add(passwordField, "cell 9 8");

        //---- registerButton ----
        registerButton.setText("Register");
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registerButtonMouseClicked(e);
            }
        });
        add(registerButton, "cell 8 9");

        //---- logInButton ----
        logInButton.setText("Log In");
        logInButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logInButtonMouseClicked(e);
            }
        });
        add(logInButton, "cell 9 9");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Lexzander Saplan
    private JLabel userLabel;
    private JFormattedTextField usernameField;
    private JLabel passLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton logInButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
