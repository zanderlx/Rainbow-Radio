/*
 * Created by JFormDesigner on Thu Jan 31 16:14:25 PST 2019
 */

package com.codebind;

import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class MusicPlayer1 extends JPanel {
    public MusicPlayer1() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - PRAMOD REDDY CHAMALA
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        title1 = compFactory.createTitle("Playlist");
        scrollPane6 = new JScrollPane();
        table1 = new JTable();
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        label1 = compFactory.createLabel("Volume");
        button1 = new JButton();
        toggleButton1 = new JToggleButton();
        button4 = new JButton();
        button5 = new JButton();
        slider1 = new JSlider();
        textArea1 = new JTextArea();

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
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
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
        add(title1, "cell 1 2 4 1,alignx center,growx 0");

        //======== scrollPane6 ========
        {
            scrollPane6.setViewportView(table1);
        }
        add(scrollPane6, "cell 14 2 21 22");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(list1);
        }
        add(scrollPane1, "cell 0 3 8 24,growy");
        add(label1, "cell 32 27,alignx center,growx 0");

        //---- button1 ----
        button1.setText("Previous");
        add(button1, "cell 23 28");

        //---- toggleButton1 ----
        toggleButton1.setText("Play/Pause");
        add(toggleButton1, "cell 25 28");

        //---- button4 ----
        button4.setText("Next");
        add(button4, "cell 27 28");

        //---- button5 ----
        button5.setText("Mute");
        add(button5, "cell 28 28");
        add(slider1, "cell 29 28 7 1,align center top,grow 0 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - PRAMOD REDDY CHAMALA
    private JLabel title1;
    private JScrollPane scrollPane6;
    private JTable table1;
    private JScrollPane scrollPane1;
    private JList list1;
    private JLabel label1;
    private JButton button1;
    private JToggleButton toggleButton1;
    private JButton button4;
    private JButton button5;
    private JSlider slider1;
    private JTextArea textArea1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    String[][] data = {
            { "Kundan Kumar Jha", "4031", "CSE" },
            { "Anand Jha", "6014", "IT" }
    };

    // Column Names
    String[] columnNames = { "Name", "Roll Number", "Department" };

    // Initializing the JTable

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

}
