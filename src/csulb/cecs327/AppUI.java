/*
 * Created by JFormDesigner on Thu Jan 31 16:14:25 PST 2019
 */

package csulb.cecs327;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.*;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class AppUI extends JPanel {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - PRAMOD REDDY CHAMALA
    private JLabel playlistTitle2;
    private JScrollPane songInfoPane;
    private JTable songInfoTable;
    private JLabel playlistTitle;
    private JButton button1;
    private JScrollPane playlistPane;
    private JList playlistItems;
    private JButton button2;
    private JLabel volumeLabel;
    private JButton previousButton;
    private JButton playPauseButton;
    private JButton nextButton;
    private JButton muteButton;
    private JSlider volumeSlider;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    // Music Player Variables
    private int currentSong = 0;
    private SongDatabase songDatabase = new SongDatabase();
    private String song = songDatabase.getSongList().get(currentSong);
    private MusicPlayer player = new MusicPlayer(song);


    private DefaultTableModel model;


    //User Object
    private User user;

    // Constructor
    public AppUI(User user) {
        initComponents();
        this.user = user;
    }

    private void playPauseButtonActionPerformed(ActionEvent e) {

        if (playPauseButton.getText().equals("Play")) {
            playPauseButton.setText("Pause");
            player.play();
        } else {
            playPauseButton.setText("Play");
            player.pause();
        }

    }

    private void previousButtonActionPerformed(ActionEvent e) {
        try {
            player.stop();
            currentSong--;
            if (currentSong < 0)
                currentSong = songDatabase.getSongList().size() - 1;
            song = songDatabase.getSongList().get(currentSong);
            player = new MusicPlayer(song);
            playPauseButton.setText("Pause");
            player.play();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void nextButtonActionPerformed(ActionEvent e) {
        try {
            player.stop();
            currentSong++;
            currentSong %= songDatabase.getSongList().size();
            song = songDatabase.getSongList().get(currentSong);
            player = new MusicPlayer(song);
            playPauseButton.setText("Pause");
            player.play();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void muteButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void playSongOnDoubleClick() {
        songInfoTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    currentSong = songInfoTable.getSelectedRow();
                    player.stop();
                    song = songDatabase.getSongList().get(currentSong);
                    player = new MusicPlayer(song);
                    playPauseButton.setText("Pause");
                    player.play();
                }
            }
        });
    }

    // Initialize music player components
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - PRAMOD REDDY CHAMALA
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        playlistTitle2 = compFactory.createTitle("RainbowRadio");
        songInfoPane = new JScrollPane();
        songInfoTable = new JTable();
        playlistTitle = compFactory.createTitle("Playlist");
        button1 = new JButton();
        playlistPane = new JScrollPane();
        playlistItems = new JList();
        button2 = new JButton();
        volumeLabel = compFactory.createLabel("Volume");
        previousButton = new JButton();
        playPauseButton = new JButton();
        nextButton = new JButton();
        muteButton = new JButton();
        volumeSlider = new JSlider();

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
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));
        add(playlistTitle2, "cell 34 1 1 2,alignx center,growx 0");

        //======== songInfoPane ========
        {
            songInfoPane.setViewportView(songInfoTable);
        }
        add(songInfoPane, "cell 22 2 26 28");
        add(playlistTitle, "cell 6 3 5 1,alignx center,growx 0");

        //---- button1 ----
        button1.setText("+");
        add(button1, "cell 16 4");

        //======== playlistPane ========
        {
            playlistPane.setViewportView(playlistItems);
        }
        add(playlistPane, "cell 1 4 14 25,growy");

        //---- button2 ----
        button2.setText("-");
        add(button2, "cell 16 5");
        add(volumeLabel, "cell 41 30,alignx center,growx 0");

        //---- previousButton ----
        previousButton.setText("Previous");
        previousButton.addActionListener(e -> previousButtonActionPerformed(e));
        add(previousButton, "cell 31 31");

        //---- playPauseButton ----
        playPauseButton.setText("Play");
        playPauseButton.addActionListener(e -> playPauseButtonActionPerformed(e));
        add(playPauseButton, "cell 33 31,width 100:100:100");

        //---- nextButton ----
        nextButton.setText("Next");
        nextButton.addActionListener(e -> nextButtonActionPerformed(e));
        add(nextButton, "cell 34 31");

        //---- muteButton ----
        muteButton.setText("Mute");
        muteButton.addActionListener(e -> muteButtonActionPerformed(e));
        add(muteButton, "cell 37 31");
        add(volumeSlider, "cell 38 31 7 1,align center top,grow 0 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        playSongOnDoubleClick();

        Object[] columns = {"No.", "Song Title", "Artist", "Album"};
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(columns);
        songInfoTable.getTableHeader().setReorderingAllowed(false);
        addDefaultTableRows();
        songInfoTable.setModel(model);
    }

    // Initializing the JTable
    public void setSongInfoTable(JTable songInfoTable) {
        this.songInfoTable = songInfoTable;
    }

    public void addDefaultTableRows() {
        model.addRow(new Object[]{"1", "Faded", "Alan Walker", "Different World"});
        model.addRow(new Object[]{"2", "Mine", "Bazzi", "Cosmic"});
        model.addRow(new Object[]{"3", "Crab Rave", "Noisestorm", "Monstercat"});
        model.addRow(new Object[]{"4", "High Hopes", "Panic! At The Disco", "Pray for the Wicked"});
        model.addRow(new Object[]{"5", "Stressed Out", "Twenty One Pilots", "Blurryface"});
    }
}
