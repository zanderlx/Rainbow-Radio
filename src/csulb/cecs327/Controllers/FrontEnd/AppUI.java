/*
 * Created by JFormDesigner on Thu Jan 31 16:14:25 PST 2019
 */

package csulb.cecs327.Controllers.FrontEnd;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Timer;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
    private JButton logoutButton;
    private JLabel playlistTitle;
    private JButton addPlaylist;
    private JButton removePlaylist;
    private JLabel LibraryTitle;
    private JLabel SearchLabel;
    private JTextField searchBox;
    private JScrollPane songInfoPane;
    private JTable songInfoTable;
    private JScrollPane playlistPane;
    private JList playlistItems;
    private JLabel songLabel;
    private JProgressBar songProgress;
    private JLabel artistLabel;
    private JButton shuffleButton;
    private JButton previousButton;
    private JButton playPauseButton;
    private JButton nextButton;
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
    private static Thread songProgressThread;
    private boolean isPlaying = false;
    private int currentSongLength = 0;
    private Timer timer = new Timer();
    private DefaultListModel<Playlist> defaultListModel = new DefaultListModel<>();
    private int currentPlaylistNumber = 1;
    private int userIndex;
    
    private JMenu addToPlaylistMenu;

    // Constructor
    public AppUI(User user, int userIndex) {
        this.user = user;
        this.userIndex = userIndex;
        initComponents();
    }

    // Methods

    /**
     * This button allows the user to play or pause a song
     *
     * @param e The action performed when clicking the button
     */
    private void playPauseButtonActionPerformed(ActionEvent e) {
        if (!isPlaying) {
            playPauseButton.setIcon(new ImageIcon(getClass().getResource("/csulb/cecs327/Resources/icon/Button-Pause-icon.png")));
            player.play();
            updateProgressBar();
            isPlaying = true;
        } else {
            playPauseButton.setIcon(new ImageIcon(getClass().getResource("/csulb/cecs327/Resources/icon/Button-Play-icon.png")));
            player.pause();
            songProgress.setValue(currentSongLength);
            timer.cancel();
            isPlaying = false;
        }
        int row = songInfoTable.getSelectedRow();
        songLabel.setText((String)songInfoTable.getValueAt(row, 0));
        artistLabel.setText((String)songInfoTable.getValueAt(row, 1));
    }
    
    private void previousButtonActionPerformed(ActionEvent e) {
        try {
            player.stop();
            currentSong--;
            if (currentSong < 0)
                currentSong = songDatabase.getSongList().size() - 1;
            song = songDatabase.getSongList().get(currentSong);
            player = new MusicPlayer(song);
            timer.cancel();
            currentSongLength = 0;
            updateProgressBar();
            player.play();
            int row = --fakeCurrent;
            if (row < 0)
                row = songInfoTable.getRowCount() - 1;
            fakeCurrent = row;
            songLabel.setText((String)songInfoTable.getValueAt(row, 0));
            artistLabel.setText((String)songInfoTable.getValueAt(row, 1));
            playPauseButton.setIcon(new ImageIcon(getClass().getResource("/csulb/cecs327/Resources/icon/Button-Pause-icon.png")));
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
            timer.cancel();
            currentSongLength = 0;
            updateProgressBar();
            player.play();
            int row = ++fakeCurrent;
            fakeCurrent = row;
            row %= songInfoTable.getRowCount();
            playPauseButton.setIcon(new ImageIcon(getClass().getResource("/csulb/cecs327/Resources/icon/Button-Pause-icon.png")));
            songLabel.setText((String)songInfoTable.getValueAt(row, 0));
            artistLabel.setText((String)songInfoTable.getValueAt(row, 1));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    private void muteButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }
    
    private void addSongInfoTableMouseListener() {
        songInfoTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)){
                    ArrayList<String> playlistItemNames = new ArrayList<>();
                    
                    addToPlaylistMenu = new JMenu("Add to selected playlist.");
                    for (int i = 0; i < defaultListModel.getSize(); i++){
                        JMenuItem menuItem = new JMenuItem(defaultListModel.getElementAt(i).getNameOfPlaylist());
                        menuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JMenuItem menuItem = (JMenuItem) e.getSource();
                                JPopupMenu menu = (JPopupMenu) menuItem.getParent();
                                int index = menu.getComponentZOrder((JMenuItem)e.getSource());
                                Playlist playlist = defaultListModel.elementAt(index);
                                playlist.addSong((String) songInfoTable
                                        .getValueAt(songInfoTable.getSelectedRow(), 0));
                                defaultListModel.setElementAt(playlist, index);
                                
                            }
                        });
                        addToPlaylistMenu.add(menuItem);
                    }
                    
                    JPopupMenu jPopupMenu = new JPopupMenu();
                    jPopupMenu.add(addToPlaylistMenu);
                    jPopupMenu.show(songInfoTable, e.getX(), e.getY());
                }
                try {
                    if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                        currentSong = songInfoTable.getSelectedRow();
                        player.stop();
                        song = songDatabase.getSongList().get(currentSong);
                        player = new MusicPlayer(song);
                        timer.cancel();
                        currentSongLength = 0;
                        updateProgressBar();
                        player.play();
                        playPauseButton.setIcon(new ImageIcon(getClass().getResource("/csulb/cecs327/Resources/icon/Button-Pause-icon.png")));
                        int row = songInfoTable.getSelectedRow();
                        songLabel.setText((String)songInfoTable.getValueAt(row, 0));
                        artistLabel.setText((String)songInfoTable.getValueAt(row, 1));
                        fakeCurrent = row;
                    }
                } catch (Exception exception) {
                    song = songDatabase.getSongList().get(new Random().nextInt(5));
                    player = new MusicPlayer(song);
                    timer.cancel();
                    currentSongLength = 0;
                    updateProgressBar();
                    player.play();
                    playPauseButton.setIcon(new ImageIcon(getClass().getResource("/csulb/cecs327/Resources/icon/Button-Pause-icon.png")));
                    int row = songInfoTable.getSelectedRow();
                    songLabel.setText((String)songInfoTable.getValueAt(row, 0));
                    artistLabel.setText((String)songInfoTable.getValueAt(row, 1));
                    fakeCurrent = row;
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
    
    private void volumeSliderStateChanged(ChangeEvent e) {
//        System.out.println(volumeSlider.getValue());
    }
    
    private void logoutButtonActionPerformed(ActionEvent e) {
        System.out.println("Pressed Logout");
        try(Reader reader = new FileReader("Users.json")) {
            Gson gson = new GsonBuilder().registerTypeAdapter(User.class, new UserSerializer()).create();
            List<User> list = gson.fromJson(reader, new TypeToken<List<User>>() {
            }.getType());
            list.remove(userIndex);
            ArrayList<Playlist> playlistsArray = new ArrayList<>();
            for (int i = 0; i < defaultListModel.getSize(); i++){
                playlistsArray.add(defaultListModel.getElementAt(i));
            }
            user.setPlayLists(playlistsArray);
            list.add(user);
            System.out.println(userIndex);
            System.out.println(list);
            try(Writer writer = new FileWriter("Users.json")){
                gson.toJson(list, writer);
            }
            
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        JFrame root = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
        root.setContentPane(new LoginPage());
        root.pack();
        player.stop();
    }
    
    private void shuffleButtonMouseClicked(MouseEvent e) {
        System.out.println("Pressed shuffle");
        Random rand = new Random();
        try {
            player.stop();
            // currentSong++;
            currentSong = rand.nextInt(201);
            currentSong %= songDatabase.getSongList().size();
            song = songDatabase.getSongList().get(currentSong);
            player = new MusicPlayer(song);
            timer.cancel();
            currentSongLength = 0;
            updateProgressBar();
            player.play();
            int row = ++fakeCurrent;
            fakeCurrent = row;
            row %= songInfoTable.getRowCount();
            songLabel.setText((String)songInfoTable.getValueAt(row, 0));
            artistLabel.setText((String)songInfoTable.getValueAt(row, 1));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    private void muteButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
    }
    
    private void addPlaylistMouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)){
            Playlist playlist = new Playlist();
            playlist.setNameOfPlaylist("newPlaylist" + currentPlaylistNumber++);
            defaultListModel.addElement(playlist);
            
        }
    }
    
    private void removePlaylistMouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)){
            defaultListModel.removeElementAt(playlistItems.getSelectedIndex());
        }
    }
    
    private void playlistItemsMouseClicked(MouseEvent e) {
        if(SwingUtilities.isRightMouseButton(e)){
            int index = playlistItems.getSelectedIndex();
            JPopupMenu jPopupMenu = new JPopupMenu();
            JMenuItem rename = new JMenuItem("Rename");
            rename.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newName = JOptionPane.showInputDialog("Enter new name.");
                    int index = playlistItems.getSelectedIndex();
                    Playlist playlist = defaultListModel.elementAt(index);
                    playlist.setNameOfPlaylist(newName);
                    defaultListModel.setElementAt(playlist, index);
                }
            });
            Playlist playlist = defaultListModel.elementAt(index);
            JMenu songMenu = new JMenu("Songs");
            for (String currentSong : playlist.getListOfSongs()){
                JMenuItem menuItem = new JMenuItem(currentSong);
                menuItem.addActionListener(e1 -> {
                    //TODO: Play Song
                });
                songMenu.add(menuItem);
            }
            jPopupMenu.add(rename);
            jPopupMenu.add(songMenu);
            jPopupMenu.show(playlistItems, e.getX(), e.getY());
            
        }
        else if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2){
            //TODO: Play first song in playlist.
        }
    }
    
    private void playlistItemsMouseReleased(MouseEvent e) {
        if(SwingUtilities.isRightMouseButton(e))
            playlistItems.setSelectedIndex(playlistItems.locationToIndex(e.getPoint()));
    }
    
    // Initialize music player components
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Lexzander Saplan
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        logoutButton = new JButton();
        playlistTitle = compFactory.createTitle("Playlist");
        addPlaylist = new JButton();
        removePlaylist = new JButton();
        LibraryTitle = new JLabel();
        SearchLabel = new JLabel();
        searchBox = new JTextField();
        songInfoPane = new JScrollPane();
        songInfoTable = new JTable();
        playlistPane = new JScrollPane();
        playlistItems = new JList();
        songLabel = new JLabel();
        songProgress = new JProgressBar();
        artistLabel = new JLabel();
        shuffleButton = new JButton();
        previousButton = new JButton();
        playPauseButton = new JButton();
        nextButton = new JButton();

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
        
        //---- logoutButton ----
        logoutButton.setText("Log Out");
        logoutButton.setIcon(new ImageIcon(getClass().getResource("/csulb/cecs327/Resources/icon/Logout-icon.png")));
        logoutButton.setForeground(Color.white);
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        logoutButton.addActionListener(e -> logoutButtonActionPerformed(e));
        add(logoutButton, "cell 41 16,width 100:100:100");
        
        //---- playlistTitle ----
        playlistTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        playlistTitle.setForeground(Color.white);
        add(playlistTitle, "cell 6 20,alignx center,growx 0");
        
        //---- addPlaylist ----
        addPlaylist.setIcon(new ImageIcon(getClass().getResource("/csulb/cecs327/Resources/icon/Plus Icon.png")));
        addPlaylist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addPlaylistMouseClicked(e);
            }
        });
        add(addPlaylist, "cell 16 20,width 32:32:32");
        
        //---- removePlaylist ----
        removePlaylist.setIcon(new ImageIcon(getClass().getResource("/csulb/cecs327/Resources/icon/minus icon.png")));
        removePlaylist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                removePlaylistMouseClicked(e);
            }
        });
        add(removePlaylist, "cell 16 20,width 32:32:32");
        
        //---- LibraryTitle ----
        LibraryTitle.setText("Song Library");
        LibraryTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        LibraryTitle.setForeground(Color.white);
        add(LibraryTitle, "cell 20 20,align center center,grow 0 0");
        
        //---- SearchLabel ----
        SearchLabel.setText("Search");
        SearchLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        SearchLabel.setForeground(Color.white);
        add(SearchLabel, "cell 27 20 12 1,align center bottom,grow 0 0");
        
        //---- searchBox ----
        searchBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        searchBox.setForeground(Color.white);
        add(searchBox, "cell 40 20 6 1");
        
        //======== songInfoPane ========
        {
            
            //---- songInfoTable ----
            songInfoTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            songInfoTable.setForeground(Color.darkGray);
            songInfoPane.setViewportView(songInfoTable);
        }
        add(songInfoPane, "cell 19 21 29 26");
        
        //======== playlistPane ========
        {
            
            //---- playlistItems ----
            playlistItems.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    playlistItemsMouseClicked(e);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    playlistItemsMouseReleased(e);
                }
            });
            playlistPane.setViewportView(playlistItems);
        }
        add(playlistPane, "cell 6 21 12 26,growy");
        
        //---- songLabel ----
        songLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        songLabel.setText("Song");
        songLabel.setForeground(Color.white);
        add(songLabel, "cell 6 51");
        add(songProgress, "cell 22 51,width 400:400:400,height 5:5:5");
        
        //---- artistLabel ----
        artistLabel.setText("Artist");
        artistLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        artistLabel.setForeground(Color.white);
        add(artistLabel, "cell 6 52,alignx left,growx 0");
        
        //---- shuffleButton ----
        shuffleButton.setForeground(Color.black);
        shuffleButton.setIcon(new ImageIcon(getClass().getResource("/csulb/cecs327/Resources/icon/Shuffle.png")));
        shuffleButton.addActionListener(e -> muteButtonActionPerformed(e));
        shuffleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                shuffleButtonMouseClicked(e);
            }
        });
        add(shuffleButton, "cell 22 52,width 32:32:32");
        
        //---- previousButton ----
        previousButton.setForeground(Color.black);
        previousButton.setIcon(new ImageIcon(getClass().getResource("/csulb/cecs327/Resources/icon/Button-Back-icon.png")));
        previousButton.addActionListener(e -> previousButtonActionPerformed(e));
        add(previousButton, "cell 22 52,width 32:32:32");
        
        //---- playPauseButton ----
        playPauseButton.setIcon(new ImageIcon(getClass().getResource("/csulb/cecs327/Resources/icon/Button-Play-icon.png")));
        playPauseButton.addActionListener(e -> playPauseButtonActionPerformed(e));
        add(playPauseButton, "cell 22 52,width 32:32:32");
        
        //---- nextButton ----
        nextButton.setIcon(new ImageIcon(getClass().getResource("/csulb/cecs327/Resources/icon/Button-Forward-icon.png")));
        nextButton.addActionListener(e -> nextButtonActionPerformed(e));
        add(nextButton, "cell 22 52,width 32:32:32");

        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        
        addSongInfoTableMouseListener();
        if(user.getPlayLists() != null && !user.getPlayLists().isEmpty()) {
            for (Playlist p : user.getPlayLists()) {
                defaultListModel.addElement(p);
            }
        }
        playlistItems.setModel(defaultListModel);
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
        songInfoTable.setRowSelectionInterval(0, 0);
        
        // Make background of button invisible
        playPauseButton.setBackground(null);
        playPauseButton.setBorder(null);
        playPauseButton.setFocusPainted(false);
        
        nextButton.setBackground(null);
        nextButton.setBorder(null);
        nextButton.setFocusPainted(false);
        
        previousButton.setBackground(null);
        previousButton.setBorder(null);
        previousButton.setFocusPainted(false);
        
        removePlaylist.setBackground(null);
        removePlaylist.setBorder(null);
        removePlaylist.setFocusPainted(false);
        
        addPlaylist.setBackground(null);
        addPlaylist.setBorder(null);
        addPlaylist.setFocusPainted(false);
        
        logoutButton.setBackground(null);
        logoutButton.setBorder(null);
        logoutButton.setFocusPainted(false);
        
        
        songProgress.setMinimum(0);
        songProgress.setMaximum(100);
        songProgress.setForeground(Color.decode("#1DB954"));
    }
    
    // Initializing the JTable
    public void setSongInfoTable(JTable songInfoTable) {
        this.songInfoTable = songInfoTable;
    }
    
    public void updateProgressBar() {
        int delay = 1000; // delay for 5 sec.
        int period = 1000; // repeat every sec.
        songProgress.setMaximum(player.getSongLength());
        System.out.println(player.getSongLength());
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                // Your code
                songProgress.setValue(currentSongLength);
                System.out.println(currentSongLength);
                currentSongLength++;
                if (currentSongLength == player.getSongLength()) currentSongLength = 0;
            }
        }, delay, period);
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
