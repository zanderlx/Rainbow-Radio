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
public class AppUI extends JPanel implements ActionListener {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Lexzander Saplan
    private JLabel playlistTitle;
    private JScrollPane songInfoPane;
    private JTable songInfoTable;
    private JScrollPane playlistPane;
    private JList playlistItems;
    private JLabel volumeLabel;
    private JButton previousButton;
    private JButton playPauseButton;
    private JButton nextButton;
    private JButton muteButton;
    private JSlider volumeSlider;
    private int currentSong = 0;
    private SongDatabase songDatabase;
    DefaultTableModel model;

    // JFormDesigner - End of variables declaration  //GEN-END:variables
    String[][] data = {
            {"Kundan Kumar Jha", "4031", "CSE"},
            {"Anand Jha", "6014", "IT"}
    };

    // Column Names
    String[] columnNames = {"Name", "Roll Number", "Department"};

    // Music Player Variables
    private MusicPlayer player;

    // Constructor
    public AppUI() {
        initComponents();
    }


    // Initialize music player components
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Lexzander Saplan
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        playlistTitle = compFactory.createTitle("Playlist");
        songInfoPane = new JScrollPane();
        songInfoTable = new JTable();
        playlistPane = new JScrollPane();
        playlistItems = new JList();
        volumeLabel = compFactory.createLabel("Volume");
        previousButton = new JButton();
        playPauseButton = new JButton();
        nextButton = new JButton();
        muteButton = new JButton();
        volumeSlider = new JSlider();
        songDatabase = new SongDatabase();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder()));

        addPropertyChangeListener(e -> {
            if ("border".equals(e.getPropertyName()))
                throw new RuntimeException();
        });

        setLayout( new MigLayout(
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

            Object[] columns = { "No.", "Song Title", "Artist", "Album" };
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
        add(songInfoPane, "cell 14 2 21 22");

        //======== playlistPane ========
        {
            playlistPane.setViewportView(playlistItems);
        }
        add(playlistPane, "cell 0 3 8 24, growy");
        add(volumeLabel, "cell 32 27, alignx center, growx 0");

        //---- playPauseButton ----
        playPauseButton.setText("Play");
        playPauseButtonActionPerformed();
        add(playPauseButton, "cell 25 28");

        //---- nextButton ----
        nextButton.setText("Next");
        nextButtonActionPerformed();
        add(nextButton, "cell 27 28");

        //---- previousButton ----
        previousButton.setText("Previous");
        previousButtonActionPerformed();
        add(previousButton, "cell 23 28");

        //---- muteButton ----
        muteButton.setText("Mute");
        add(muteButton, "cell 28 28");
        add(volumeSlider, "cell 29 28 7 1,align center top,grow 0 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        playSongOnDoubleClick();
    }

    // Initializing the JTable
    public void setSongInfoTable(JTable songInfoTable) {
        this.songInfoTable = songInfoTable;
    }

    public void addDefaultTableRows() {
        model.addRow(new Object[] {"1", "Faded", "Alan Walker", "Different World" });
        model.addRow(new Object[] {"2", "Mine", "Bazzi", "Cosmic" });
        model.addRow(new Object[] {"3", "Crab Rave", "Noisestorm", "Monstercat" });
        model.addRow(new Object[] {"4", "High Hopes", "Panic! At The Disco", "Pray for the Wicked" });
        model.addRow(new Object[] {"5", "Stressed Out", "Twenty One Pilots", "Blurryface" });
    }

    private void playSongOnDoubleClick() {
        songInfoTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    //System.out.println(songInfoTable.getValueAt(songInfoTable.getSelectedRow(), 1) + " - " + songInfoTable.getValueAt(songInfoTable.getSelectedRow(), 0) + " (8D AUDIO).mp3");
                    currentSong = songInfoTable.getSelectedRow();
                    System.out.println(currentSong);
                    player.stop();
                    String song = songDatabase.getSongList().get(currentSong);
                    player = new MusicPlayer(song);
                    playPauseButton.setText("Pause");
                    player.play();
                }
            }
        });
    }



    /**
     * This method will allow the functionality of playing or pausing a song
     *
     *
     */
    private void playPauseButtonActionPerformed() {
        System.out.println(currentSong);
        String song = songDatabase.getSongList().get(currentSong);
        player = new MusicPlayer(song);
        playPauseButton.addActionListener(ev -> {
            if (playPauseButton.getText().equals("Play")) {
                playPauseButton.setText("Pause");
                player.play();
            }
            else {
                playPauseButton.setText("Play");
                player.pause();
            }

        });
    }


    /**
     * This method will allow the functionality of going to the next song
     */
    private void nextButtonActionPerformed() {
        nextButton.addActionListener(ev -> {
            try {
                player.stop();
                currentSong++;
                currentSong %= songDatabase.getSongList().size();
                System.out.println(currentSong);
                String song = songDatabase.getSongList().get(currentSong);
                player = new MusicPlayer(song);
                playPauseButton.setText("Pause");
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * This method will allow the functionality of going to the previous song
     */
    private void previousButtonActionPerformed() {
        previousButton.addActionListener(ev -> {
            try {
                player.stop();
                currentSong--;
                if (currentSong < 0)
                    currentSong = songDatabase.getSongList().size() - 1;
                System.out.println(currentSong);
                String song = songDatabase.getSongList().get(currentSong);
                player = new MusicPlayer(song);
                playPauseButton.setText("Pause");
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * This method will allow the functionality of muting the volume
     *
     * @param e The action performed by the user
     */
    private void muteButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
