package je.panse.doro.fourgate;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class EditableTable {

    public static void main(String[] a) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnTitles = {"First Name", "Last Name", "weight", "Qualification", "age(18+)"};
        Object[][] dataEntries = {
                {"ABHISHEK\nSecondLine", "DUBEY", 50, "B-tech", false},
                {"MANISH", "TIWARI", 80, "PG", true},
                {"ANURUDDHA", "PANDEY", 80, "Msc", true},
                {"Bindresh", "AGINHOTRI", 80, "Bsc", true},
                {"SOURABH", "TRIPATHI", 80, "PG", true},
                {"AMIT", "GUPTA", 70, "Graduate", false},
                {"AMIT", "VERMA", 55, "12TH", true},
        };

        EditableTableModel model = new EditableTableModel(columnTitles, dataEntries);
        JTable table = new JTable(model);

        String[] education = {"PG", "Msc", "B-Tech", "Bsc", "12th", "10th"};
        JComboBox<String> comboBox = new JComboBox<>(education);
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));

     // Set the TextAreaEditor and TextAreaRenderer for the First Name and Last Name columns
        table.getColumnModel().getColumn(0).setCellEditor(new TextAreaEditor());
        table.getColumnModel().getColumn(0).setCellRenderer(new TextAreaRenderer());
        table.getColumnModel().getColumn(1).setCellEditor(new TextAreaEditor());
        table.getColumnModel().getColumn(1).setCellRenderer(new TextAreaRenderer());

        
        frame.add(new JScrollPane(table));
        frame.setSize(1500, 900);
        frame.setVisible(true);

    }

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

    static class TextAreaRenderer extends JTextArea implements TableCellRenderer {
        public TextAreaRenderer() {

            setWrapStyleWord(true);
            setLineWrap(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value != null) ? value.toString() : "");
            
            // Set row height
            table.setRowHeight(1,30);
            table.setRowHeight(2,130);
            table.setRowHeight(3,230);

            
//            
//            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
//            if (table.getRowHeight(row) != getPreferredSize().height) {
//                table.setRowHeight(row, getPreferredSize().height);
//            }
            return this;
        }
    }
}
