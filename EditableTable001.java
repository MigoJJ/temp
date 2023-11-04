package je.panse.doro.fourgate;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a UI component that displays an editable table
 * with functionalities to load and save its content to a CSV file.
 */
public class EditableTable001 {

    private static final String[] COLUMN_TITLES = {"First Name", "Last Name", "weight", "Qualification", "age(18+)"};
    private static final String FILENAME = "tabledata.csv";

    // --- Main Method ---
    
    public static void main(String[] a) {
        JFrame frame = setupUI();
        frame.setVisible(true);
    }

    // --- UI Setup Methods ---
    
    private static JFrame setupUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        JTable table = createTable();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
            }
        });
        
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(createSaveButton(table), BorderLayout.SOUTH);
        frame.setSize(1500, 900);
        
        return frame;
    }
    
    private static JTable createTable() {
        Object[][] dataFromCSV = loadFromFile(FILENAME, COLUMN_TITLES.length);
        EditableTableModel model = new EditableTableModel(COLUMN_TITLES, dataFromCSV);
        JTable table = new JTable(model);

        String[] education = {"PG", "Msc", "B-Tech", "Bsc", "12th", "10th"};
        JComboBox<String> comboBox = new JComboBox<>(education);
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));

        TextAreaEditor textAreaEditor = new TextAreaEditor();
        TextAreaRenderer textAreaRenderer = new TextAreaRenderer();

        table.getColumnModel().getColumn(0).setCellEditor(textAreaEditor);
        table.getColumnModel().getColumn(0).setCellRenderer(textAreaRenderer);
        table.getColumnModel().getColumn(1).setCellEditor(textAreaEditor);
        table.getColumnModel().getColumn(1).setCellRenderer(textAreaRenderer);

        return table;
    }

    private static JButton createSaveButton(JTable table) {
        JButton saveButton = new JButton("Save Data");
        saveButton.addActionListener(e -> saveToFile((EditableTableModel) table.getModel(), FILENAME));
        return saveButton;
    }

    // --- File Handling Methods ---
    private static void saveToFile(EditableTableModel model, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    writer.write(model.getValueAt(row, col).toString());
                    if (col < model.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    private static Object[][] loadFromFile(String filename, int numColumns) {
        List<Object[]> dataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == numColumns) {
                    dataList.add(parseRowData(tokens, numColumns));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList.toArray(new Object[0][]);
    }

    private static Object[] parseRowData(String[] tokens, int numColumns) {
        Object[] rowData = new Object[numColumns];
        for (int i = 0; i < numColumns; i++) {
            if (tokens[i].matches("^\\d+$")) {
                rowData[i] = Integer.parseInt(tokens[i]);
            } else if (tokens[i].equalsIgnoreCase("true") || tokens[i].equalsIgnoreCase("false")) {
                rowData[i] = Boolean.parseBoolean(tokens[i]);
            } else {
                rowData[i] = tokens[i];
            }
        }
        return rowData;
    }
    
    // --- Nested Static Classes ---

    static class EditableTableModel extends AbstractTableModel {
        private final String[] columnTitles;
        private final Object[][] dataEntries;

        public EditableTableModel(String[] columnTitles, Object[][] dataEntries) {
            this.columnTitles = columnTitles;
            this.dataEntries = dataEntries;
        }

        @Override
        public int getRowCount() {
            return dataEntries.length;
        }

        @Override
        public int getColumnCount() {
            return columnTitles.length;
        }

        @Override
        public Object getValueAt(int row, int column) {
            return dataEntries[row][column];
        }

        @Override
        public String getColumnName(int column) {
            return columnTitles[column];
        }

        @Override
        public Class<?> getColumnClass(int column) {
            return getValueAt(0, column).getClass();
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }

        @Override
        public void setValueAt(Object value, int row, int column) {
            dataEntries[row][column] = value;
        }
    }
    
    static class TextAreaRenderer extends JTextArea implements TableCellRenderer {
        public TextAreaRenderer() {
            setWrapStyleWord(true);
            setLineWrap(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value != null) ? value.toString() : "");
            table.setRowHeight(1,30);
            table.setRowHeight(2,130);
            table.setRowHeight(3,100);

            return this;
        }
    }

    static class TextAreaEditor extends AbstractCellEditor implements TableCellEditor {
        private final JScrollPane scrollPane;
        private final JTextArea textArea;

        public TextAreaEditor() {
            textArea = new JTextArea();
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            scrollPane = new JScrollPane(textArea);
        }

        @Override
        public Object getCellEditorValue() {
            return textArea.getText();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            textArea.setText((value != null) ? value.toString() : "");
            return scrollPane;
        }
    }
}
