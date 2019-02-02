/*
 * Created by JFormDesigner on Fri Feb 01 17:30:38 PST 2019
 */

package csulb.cecs327;

import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author PRAMOD REDDY CHAMALA
 */
public class LoginPage extends JPanel {
    public LoginPage() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Lexzander Saplan
        userLabel = new JLabel();
        usernameField = new JFormattedTextField();
        passLabel = new JLabel();
        passwordField = new JPasswordField();
        button1 = new JButton();

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

        //---- button1 ----
        button1.setText("Log In");
        add(button1, "cell 9 9");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Lexzander Saplan
    private JLabel userLabel;
    private JFormattedTextField usernameField;
    private JLabel passLabel;
    private JPasswordField passwordField;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
