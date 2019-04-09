package csulb.cecs327.Client.Services;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

public class TableSearch {

    private Vector originalTableModel;
    private DocumentListener documentListener;
    private JTable table;
    private JScrollPane scrollPane;
    private JCheckBox searchOnType;
    private JTextField textField;

    /**
     * This class is responsible for displaying the information in the display GUI for song data when the information is sent from the
     * server to the client.
     *
     * @param table
     * @param scrollPane
     * @param textField
     */
    public TableSearch(JTable table, JScrollPane scrollPane, JTextField textField) {
        initComponents(table, scrollPane, textField);
        originalTableModel = (Vector) ((DefaultTableModel) table.getModel()).getDataVector().clone();
        addDocumentListener();
    }

    /**
     * Initialize components
     *
     * @param table
     * @param scrollPane
     * @param textField
     */
    private void initComponents(JTable table, JScrollPane scrollPane, JTextField textField) {
        this.scrollPane = scrollPane;
        this.table = table;
        this.textField = textField;
        searchOnType = new JCheckBox();

        searchOnType.setText("Search on Type");
        searchOnType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                searchOnType(e);
            }
        });
    }

    /**
     * Searches based on type of filter
     *
     * @param e
     */
    public void searchOnType(ItemEvent e) {
        if (searchOnType.isSelected())
            textField.getDocument().addDocumentListener(documentListener);
        else
            textField.getDocument().addDocumentListener(null);
    }

    private void addDocumentListener() {
        documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchTableContents(textField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchTableContents(textField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchTableContents(textField.getText());
            }
        };
        searchOnType.setSelected(true);
    }

    /**
     * Searches through table contents
     *
     * @param searchString
     */
    public void searchTableContents(String searchString) {
        DefaultTableModel currtableModel = (DefaultTableModel) table.getModel();
        //To empty the table before search
        currtableModel.setRowCount(0);
        //To search for contents from original table content
        for (Object rows : originalTableModel) {
            Vector rowVector = (Vector) rows;
            for (Object column : rowVector) {
                if (column.toString().contains(searchString)) {
                    //content found so adding to table
                    currtableModel.addRow(rowVector);
                    break;
                }
            }

        }
    }

}
