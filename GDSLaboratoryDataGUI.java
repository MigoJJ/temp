import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GDSLaboratoryDataGUI extends JFrame implements ActionListener {

	static JTextArea inputTextArea;
    static JTextArea outputTextArea;
    private JButton[] buttons;

    public GDSLaboratoryDataGUI() {
        setupFrame();
        setupTextAreas();
        setupButtons();
        arrangeComponents();
    }

    private void setupFrame() {
        setTitle("GDS Laboratory Data");
        setSize(1200, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupTextAreas() {
        inputTextArea = new JTextArea(55, 35);
        outputTextArea = new JTextArea(55, 35);
        outputTextArea.setEditable(true); // Make the output area editable
        outputTextArea.setText("""
        ----------------------------------------------------
        make table

        Parameter Value Unit 
        if parameter does not exist -> remove the row;
        using value format 
        if parameter does not exist -> remove the row;
        """);
    }
    static void appendTextAreas(String bardline) {
        outputTextArea.append(bardline + "\n");
    }
    
    private void setupButtons() {
        String[] buttonLabels = {"Modify", "Copy Result", "Clear Input", "Clear Output", "Clear All", "Save and Quit"};
        buttons = new JButton[buttonLabels.length];

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
        }
    }

 // ... [snip] ...

private void arrangeComponents() {
    // Create a panel for buttons
    JPanel buttonPanel = new JPanel(new FlowLayout());
    for (JButton button : buttons) {
        buttonPanel.add(button);
    }

    // Layout components using GridBagLayout
    JPanel contentPanel = new JPanel(new GridBagLayout());

    // Input Label
    addComponent(contentPanel, new JLabel("Input Data:"), 0, 0, GridBagConstraints.NORTH);
    // Input TextArea below the label
    addComponent(contentPanel, new JScrollPane(inputTextArea), 1, 0, GridBagConstraints.BOTH);

    // Output Label
    addComponent(contentPanel, new JLabel("Output Data:"), 2, 0, GridBagConstraints.NORTH);
    // Output TextArea below the label
    addComponent(contentPanel, new JScrollPane(outputTextArea), 3, 0, GridBagConstraints.BOTH);

    // Button Panel
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(5, 5, 5, 5); // Padding
    constraints.gridx = 0;
    constraints.gridy = 2; // Placing the button panel below the text areas
    constraints.gridwidth = 4; // Span across 4 columns
    constraints.anchor = GridBagConstraints.SOUTH;
    contentPanel.add(buttonPanel, constraints);

    add(contentPanel);
}

    private void addComponent(JPanel panel, Component comp, int x, int y, int fill) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Padding
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.fill = fill;
        panel.add(comp, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Modify":
            	String textFromInputArea = inputTextArea.getText();
            	System.out.println(textFromInputArea);   
            	
            	GDSLaboratoryDataModify.main(textFromInputArea);
            	
            	break;
            case "Copy Result":
                // Implement copy result functionality here
                break;
            case "Clear Input":
                inputTextArea.setText("");
                break;
            case "Clear Output":
                outputTextArea.setText("");
                break;
            case "Clear All":
                inputTextArea.setText("");
                outputTextArea.setText("");
                break;
            case "Save and Quit":
                // Implement save functionality if needed before exiting
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GDSLaboratoryDataGUI gui = new GDSLaboratoryDataGUI();
            gui.setVisible(true);
        });
    }
}
