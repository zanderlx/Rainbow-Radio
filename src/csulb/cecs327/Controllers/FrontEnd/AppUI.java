/*
 * Created by JFormDesigner on Thu Jan 31 16:14:25 PST 2019
 */

package csulb.cecs327.Controllers.FrontEnd;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.jgoodies.forms.factories.*;
import csulb.cecs327.Models.*;
import csulb.cecs327.Services.*;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class AppUI extends JPanel {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Lexzander Saplan
    private JLabel playlistTitle;
    private JLabel LibraryTitle;
    private JLabel SearchLabel;
    private JTextField searchBox;
    private JScrollPane songInfoPane;
    private JTable songInfoTable;
    private JScrollPane playlistPane;
    private JList playlistItems;
    private JLabel songLabel;
    private JProgressBar progressBar1;
    private JLabel artistLabel;
    private JButton shuffleButton;
    private JButton previousButton;
    private JButton playPauseButton;
    private JButton nextButton;
    private JButton muteButton;
    private JSlider volumeSlider;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    // Custom Variables
    private int currentSong = 0;
    private int fakeCurrent = 0;
    private SongDatabase songDatabase = new SongDatabase();
    private String song = songDatabase.getSongList().get(currentSong);
    private MusicPlayer player = new MusicPlayer(song);
    private DefaultTableModel model;
    private User user;
    private SongSerializer songSerializer = new SongSerializer();
    private RootObject[] musicJson = songSerializer.getRootObjects();
    private TableSearch tableSearch;

    // Constructor
    public AppUI(User user) {
        initComponents();
        this.user = user;
    }

    // Methods

    /**
     * This button allows the user to play or pause a song
     *
     * @param e The action performed when clicking the button
     */
    private void playPauseButtonActionPerformed(ActionEvent e) {
        if (playPauseButton.getText().equals("Play")) {
            playPauseButton.setText("Pause");
            int row = songInfoTable.getSelectedRow();
            songLabel.setText((String)songInfoTable.getValueAt(row, 0));
            artistLabel.setText((String)songInfoTable.getValueAt(row, 1));
            player.play();
        } else {
            playPauseButton.setText("Play");
            int row = songInfoTable.getSelectedRow();
            songLabel.setText((String)songInfoTable.getValueAt(row, 0));
            artistLabel.setText((String)songInfoTable.getValueAt(row, 1));
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
            player.play();
            int row = --fakeCurrent;
            if (row < 0)
                row = songInfoTable.getRowCount() - 1;
            fakeCurrent = row;
            songLabel.setText((String)songInfoTable.getValueAt(row, 0));
            artistLabel.setText((String)songInfoTable.getValueAt(row, 1));
            playPauseButton.setText("Pause");
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
            int row = ++fakeCurrent;
            fakeCurrent = row;
            row %= songInfoTable.getRowCount();
            playPauseButton.setText("Pause");
            songLabel.setText((String)songInfoTable.getValueAt(row, 0));
            artistLabel.setText((String)songInfoTable.getValueAt(row, 1));
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
                try {
                    if (e.getClickCount() == 2) {
                        currentSong = songInfoTable.getSelectedRow();
                        System.out.println(currentSong);
                        System.out.println(musicJson.length);
                        player.stop();
                        song = songDatabase.getSongList().get(currentSong);
                        player = new MusicPlayer(song);
                        playPauseButton.setText("Pause");
                        int row = songInfoTable.getSelectedRow();
                        songLabel.setText((String)songInfoTable.getValueAt(row, 0));
                        artistLabel.setText((String)songInfoTable.getValueAt(row, 1));
                        fakeCurrent = row;
                        player.play();
                    }
                } catch (Exception exception) {
                    song = songDatabase.getSongList().get(new Random().nextInt(5));
                    player = new MusicPlayer(song);
                    playPauseButton.setText("Pause");
                    int row = songInfoTable.getSelectedRow();
                    songLabel.setText((String)songInfoTable.getValueAt(row, 0));
                    artistLabel.setText((String)songInfoTable.getValueAt(row, 1));
                    fakeCurrent = row;
                    player.play();
                }
            }
        });
    }

    public void sortColumn(int column) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(songInfoTable.getModel());
        sorter.setComparator(column, Comparator.naturalOrder());
        sorter.setSortsOnUpdates(true);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(column, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        songInfoTable.setRowSorter(sorter);
    }

    // Initialize music player components
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Lexzander Saplan
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        playlistTitle = compFactory.createTitle("Playlist");
        LibraryTitle = new JLabel();
        SearchLabel = new JLabel();
        searchBox = new JTextField();
        songInfoPane = new JScrollPane();
        songInfoTable = new JTable();
        playlistPane = new JScrollPane();
        playlistItems = new JList();
        songLabel = new JLabel();
        progressBar1 = new JProgressBar();
        artistLabel = new JLabel();
        shuffleButton = new JButton();
        previousButton = new JButton();
        playPauseButton = new JButton();
        nextButton = new JButton();
        muteButton = new JButton();
        volumeSlider = new JSlider();

        //======== this ========
        setForeground(Color.blue);
        setBackground(Color.darkGray);

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new MigLayout(
            "fillx,hidemode 3",
            // columns
            "0[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[48:103,fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[0,fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]" +
            "[fill]" +
            "[fill]0" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[7,fill]0" +
            "[fill]0" +
            "[191,fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[14,fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0" +
            "[fill]0",
            // rows
            "0[]0" +
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
            "[22:n]" +
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
            "[]0" +
            "[]0" +
            "[]0" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]0" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]0" +
            "[]0" +
            "[]0" +
            "[]0" +
            "[]"));

        //---- playlistTitle ----
        playlistTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        playlistTitle.setForeground(Color.white);
        add(playlistTitle, "cell 6 20,alignx center,growx 0");

        //---- LibraryTitle ----
        LibraryTitle.setText("Song Library");
        LibraryTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        LibraryTitle.setForeground(Color.white);
        add(LibraryTitle, "cell 18 20,align center center,grow 0 0");

        //---- SearchLabel ----
        SearchLabel.setText("Search");
        SearchLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        SearchLabel.setForeground(Color.white);
        add(SearchLabel, "cell 25 20 10 1,align center bottom,grow 0 0");

        //---- searchBox ----
        searchBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        searchBox.setForeground(Color.white);
        add(searchBox, "cell 36 20 4 1");

        //======== songInfoPane ========
        {

            //---- songInfoTable ----
            songInfoTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            songInfoTable.setForeground(Color.darkGray);
            songInfoPane.setViewportView(songInfoTable);
        }
        add(songInfoPane, "cell 17 21 25 26");

        //======== playlistPane ========
        {
            playlistPane.setViewportView(playlistItems);
        }
        add(playlistPane, "cell 6 21 10 26,growy");

        //---- songLabel ----
        songLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        songLabel.setText("Song");
        add(songLabel, "cell 6 51");
        add(progressBar1, "cell 20 51");

        //---- artistLabel ----
        artistLabel.setText("Artist");
        artistLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(artistLabel, "cell 6 52,alignx left,growx 0");

        //---- shuffleButton ----
        shuffleButton.setText("Shuffle");
        shuffleButton.setForeground(Color.black);
        shuffleButton.addActionListener(e -> muteButtonActionPerformed(e));
        add(shuffleButton, "cell 20 52");

        //---- previousButton ----
        previousButton.setText("Previous");
        previousButton.setForeground(Color.black);
        previousButton.addActionListener(e -> previousButtonActionPerformed(e));
        add(previousButton, "cell 20 52");

        //---- playPauseButton ----
        playPauseButton.setText("Play");
        playPauseButton.addActionListener(e -> playPauseButtonActionPerformed(e));
        add(playPauseButton, "cell 20 52,width 75:75:75");

        //---- nextButton ----
        nextButton.setText("Next");
        nextButton.addActionListener(e -> nextButtonActionPerformed(e));
        add(nextButton, "cell 20 52");

        //---- muteButton ----
        muteButton.setText("Mute");
        muteButton.addActionListener(e -> muteButtonActionPerformed(e));
        add(muteButton, "cell 20 52");
        add(volumeSlider, "cell 36 52 2 1,aligny center,growy 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        playSongOnDoubleClick();

        Object[] columns = {"Song Title", "Artist", "Album", "Genre"};
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(columns);
        songInfoTable.getTableHeader().setReorderingAllowed(false);
        songInfoTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        addDefaultTableRows();
        songInfoTable.setShowVerticalLines(false);
        songInfoTable.setRowHeight(30);
        songInfoTable.setModel(model);

        sortColumn(0);
        tableSearch = new TableSearch(songInfoTable, songInfoPane, searchBox);
        searchBox.setBorder(BorderFactory.createMatteBorder(0, 0,2, 0, Color.decode("#1DB954")));
        searchBox.setBackground(null);

    }

    // Initializing the JTable
    public void setSongInfoTable(JTable songInfoTable) {
        this.songInfoTable = songInfoTable;
    }

    public void addDefaultTableRows() {
        model.addRow(new Object[]{"Faded", "Alan Walker", "Different World", "edm"});
        model.addRow(new Object[]{"Mine", "Bazzi", "Cosmic", "hip hop"});
        model.addRow(new Object[]{"Crab Rave", "Noisestorm", "Monstercat", "techno"});
        model.addRow(new Object[]{"High Hopes", "Panic! At The Disco", "Pray for the Wicked", "rock"});
        model.addRow(new Object[]{"Stressed Out", "Twenty One Pilots", "Blurryface", "alternative rock"});

        for (RootObject rootObject : musicJson) {
            model.addRow(new Object[]{
                    rootObject.getSong().getTitle(),
                    rootObject.getArtist().getName(),
                    rootObject.getRelease().getName(),
                    rootObject.getArtist().getTerms()
            });
        }
    }
}
