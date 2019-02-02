/*
 * Created by JFormDesigner on Thu Jan 31 16:14:25 PST 2019
 */

package cecs327;

import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class AppUI extends JPanel {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - PRAMOD REDDY CHAMALA
    private JLabel playlistTitle;
    private JScrollPane songInfoPane;
    private JTable songInfoTable;
    private JScrollPane playlistPane;
    private JList playlistItems;
    private JLabel volumeLabel;
    private JButton previousButton;
    private JToggleButton playPauseButton;
    private JButton nextButton;
    private JButton muteButton;
    private JSlider volumeSlider;
    private JTextArea textArea1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    String[][] data = {
            { "Kundan Kumar Jha", "4031", "CSE" },
            { "Anand Jha", "6014", "IT" }
    };

    // Column Names
    String[] columnNames = { "Name", "Roll Number", "Department" };

    // Music Player Variables
    MusicPlayer player = new MusicPlayer();

    // Constructor
    public AppUI() {
        initComponents();
    }

    // Initialize music player components
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - PRAMOD REDDY CHAMALA
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        playlistTitle = compFactory.createTitle("Playlist");
        songInfoPane = new JScrollPane();
        songInfoTable = new JTable();
        playlistPane = new JScrollPane();
        playlistItems = new JList();
        volumeLabel = compFactory.createLabel("Volume");
        previousButton = new JButton();
        playPauseButton = new JToggleButton();
        nextButton = new JButton();
        muteButton = new JButton();
        volumeSlider = new JSlider();
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
        playPauseButton.addActionListener(e -> playPauseButtonActionPerformed(e));
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

    /**
     * This method will allow the functionality of playing or pausing a song
     * @param e The action performed by the user
     */
    private void playPauseButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        playPauseButton.addItemListener(ev -> {
            try {
                player.play();
                // TODO: Get threading to work to play and pause different songs
                //                if (ev.getStateChange() == ItemEvent.SELECTED) {
                //                    player.play();
                //                }
                //                else if (ev.getStateChange() == ItemEvent.DESELECTED) {
                //                    player.stop();
                //
                //                }
            } catch (Exception exception) {
                exception.getStackTrace();
            }

        });
    }

    /**
     * This method will allow the functionality of going to the next song
     * @param e The action performed by the user
     */
    private void nextButtonActionPerformed(ActionEvent e) {
        player.next();
    }

    /**
     * This method will allow the functionality of going to the previous song
     * @param e The action performed by the user
     */
    private void previousButtonActionPerformed(ActionEvent e) {
        player.previous();
    }

    /**
     * This method will allow the functionality of muting the volume
     * @param e The action performed by the user
     */
    private void muteButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }
}
