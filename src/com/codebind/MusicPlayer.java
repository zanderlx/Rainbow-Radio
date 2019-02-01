/*
 * Created by JFormDesigner on Thu Jan 31 16:14:25 PST 2019
 */

package com.codebind;

import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class MusicPlayer extends JPanel {

    private JTable songInfoTable;
    private JTextArea textArea1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    String[][] data = {
            { "Kundan Kumar Jha", "4031", "CSE" },
            { "Anand Jha", "6014", "IT" }
    };

    // Column Names
    String[] columnNames = { "Name", "Roll Number", "Department" };

    // Constructor
    public MusicPlayer() {
        initComponents();
    }

    // Initialize music player components
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Lexzander Saplan
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - Lexzander Saplan
        JLabel playlistTitle = compFactory.createTitle("Playlist");
        JScrollPane songInfoPane = new JScrollPane();
        songInfoTable = new JTable();
        JScrollPane playlistPane = new JScrollPane();
        JList playlistItems = new JList();
        JLabel volumeLabel = compFactory.createLabel("Volume");
        JButton previousButton = new JButton();
        JToggleButton playPauseButton = new JToggleButton();
        JButton nextButton = new JButton();
        JButton muteButton = new JButton();
        JSlider volumeSlider = new JSlider();
        textArea1 = new JTextArea();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(e -> {if("border".equals(e.getPropertyName()))throw new RuntimeException();});

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
        add(playlistTitle, "cell 1 2 4 1,alignx center,growx 0");

        //======== songInfoPane ========
        {
            songInfoPane.setViewportView(songInfoTable);
        }
        add(songInfoPane, "cell 14 2 21 22");

        //======== playlistPane ========
        {
            playlistPane.setViewportView(playlistItems);
        }
        add(playlistPane, "cell 0 3 8 24,growy");
        add(volumeLabel, "cell 32 27,alignx center,growx 0");

        //---- previousButton ----
        previousButton.setText("Previous");
        add(previousButton, "cell 23 28");

        //---- playPauseButton ----
        playPauseButton.setText("Play/Pause");
        playPauseButton.addActionListener(this::playPauseButtonActionPerformed);
        add(playPauseButton, "cell 25 28");

        //---- nextButton ----
        nextButton.setText("Next");
        add(nextButton, "cell 27 28");

        //---- muteButton ----
        muteButton.setText("Mute");
        add(muteButton, "cell 28 28");
        add(volumeSlider, "cell 29 28 7 1,align center top,grow 0 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // Initializing the JTable
    public void setSongInfoTable(JTable songInfoTable) {
        this.songInfoTable = songInfoTable;
    }

    // Play or Pause the song
    private void playPauseButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        JFrame frame = new JFrame("App");
        frame.setContentPane(player);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1024, 720);
    }
}
