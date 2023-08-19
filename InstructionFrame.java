package ui;

import model.WSGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

// This class represents a JFrame that displays the game instructions and difficulty level selection.
public class InstructionFrame extends JFrame {

    // GUI components
    private JButton startButton;
    private Collection<JButton> buttons;

    // Constant for the game start button text
    public static final String gameStarts = "Start the Game!";
    private JPanel instPanel;
    private Dimension dm = new Dimension(300, 240);

    // Constructor
    public InstructionFrame() {
        super();
        setBackground(new Color(184, 184, 184)); // Set background color
        setSize(dm); // Set frame size
        centreOnScreen(); // Center the frame on the screen
        setVisible(true); // Make the frame visible
        startButton = new JButton(gameStarts); // Create the start button
        instPanel = new JPanel(); // Panel to hold instructions and buttons
        instPanel.setLayout(new BoxLayout(instPanel, BoxLayout.Y_AXIS)); // Set panel layout

        // Set up the start button
        startButton.setActionCommand(gameStarts);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> {
            dispose(); // Close the current frame
            new WasteSortingGame(); // Start a new game
        });

        // Instructions and labels
        String instructions = "Please choose from a difficulty level: ";
        String welcome = "Welcome to the Waste Sorting Game!";
        String inst1 = "Use <- or -> key to control the position";
        String inst2 = "Use down arrow or space key to speed up waste falling";

        // Add labels for instructions to the panel
        addALabel(welcome, instPanel);
        addALabel(inst1, instPanel);
        addALabel(inst2, instPanel);
        addALabel(instructions, instPanel);

        // Create difficulty level buttons
        createButtons();

        instPanel.add(startButton); // Add the start button
        add(instPanel, BorderLayout.CENTER); // Add the panel to the frame's center
        pack(); // Pack the components
    }

    // Create difficulty level buttons
    private void createButtons() {
        String[] levels = {"Easy", "Medium", "Difficult"};
        buttons = new ArrayList<>();
        for (String str : levels) {
            JButton btn = new JButton(str); // Create a button for each difficulty level
            buttons.add(btn);
            btn.setActionCommand(str);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.addActionListener(e -> {
                btn.setSelected(true);
                unselectRest(btn); // Unselect other buttons
                WSGame.setDifficultyLevel(e); // Set the difficulty level in the game
            });

            instPanel.add(btn); // Add the button to the panel
        }
    }

    // Add a label to a container with centered alignment
    private void addALabel(String text, Container container) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(label);
    }

    // Center the frame on the screen
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize(); // Get screen dimensions
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2); // Calculate center position
    }

    // Unselect other buttons when a button is selected
    private void unselectRest(JButton btn) {
        for (JButton b : buttons) {
            if (!b.equals(btn)) {
                b.setSelected(false);
            }
        }
    }
}
