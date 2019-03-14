package csulb.cecs327.Services.Networking;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import csulb.cecs327.Models.MusicEntry;
import csulb.cecs327.Models.Song;
import csulb.cecs327.Models.SongTableEntry;
import csulb.cecs327.Services.SongSerializer;
import csulb.cecs327.Services.TableSearch;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static javax.swing.plaf.synth.Region.TABLE_HEADER;

public class MusicServices {
    private SongSerializer songSerializer = new SongSerializer();
    ArrayList<MusicEntry> songs = null;
    Gson gson = new Gson();
    private JTable songInfoTable = new JTable();
    DefaultTableModel model = new DefaultTableModel();
    private SongSerializer serializer = new SongSerializer();
    private static final String[] TABLE_HEADER = new String[]{"Song", "Artist", "Album", "Genre"};
    JScrollPane scrollPane = new JScrollPane();
    JTextField textField = new JTextField();
    private TableSearch tableSearch;

    public MusicServices() {

        try (Reader reader = new FileReader("src/csulb/cecs327/Services/Networking/music.json")) {
            songs = gson.fromJson(reader, new TypeToken<ArrayList<MusicEntry>>(){}.getType());
            initTable();
            tableSearch = new TableSearch(songInfoTable, scrollPane, textField);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String getSongsFromServer(String test) {
        ArrayList<MusicEntry> results = new ArrayList<>();


            for (int i = 0; i < 10; i++) {
                results.add(songs.get(i));
            }

        return gson.toJson(results);
    }

    private ArrayList<SongTableEntry> searchSongs(String search) {
        ArrayList<SongTableEntry> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            songInfoTable.setRowSelectionInterval(i, i);
                SongTableEntry entry = new SongTableEntry(
                        (String) model.getValueAt(i, 0),
                        (String) model.getValueAt(i, 1),
                        (String) model.getValueAt(i, 2),
                        (String) model.getValueAt(i, 3)
                );
                results.add(entry);
            }
        return results;
    }

    public String getSongTableEntrySearch( String search ) {
        ArrayList<SongTableEntry> songTableEntries = searchSongs(search);

        return gson.toJson(songTableEntries);
    }

    public void initTable() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
            model.setColumnIdentifiers(TABLE_HEADER);
        addDefaultTableRows();
        songInfoTable.getTableHeader().setReorderingAllowed(false);
        songInfoTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        songInfoTable.setShowVerticalLines(false);
        songInfoTable.setRowHeight(30);
        songInfoTable.setModel(model);
    }

    private void addDefaultTableRows() {

       for (MusicEntry song : songs) {
            SongTableEntry entry = new SongTableEntry(
                    song.getSong().getTitle(),
                    song.getArtist().getName(),
                    song.getRelease().getName(),
                    song.getArtist().getTerms());
            setRow(entry, model);
        }
    }

    private void setRow(SongTableEntry songTableEntry, DefaultTableModel model){
        model.addRow(new String[] {songTableEntry.getSong(),songTableEntry.getArtist(), songTableEntry.getAlbum(),
                songTableEntry.getGenre()});
    }

}
