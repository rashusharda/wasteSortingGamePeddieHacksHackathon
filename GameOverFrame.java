package ui;

import model.WSGame;

import javax.swing.*;
import java.awt.*;

// This class represents a JFrame that displays the game over screen.
public class GameOverFrame extends JFrame {

    // GUI components
    private JButton restartButton;
    private JButton quitButton;
    private JLabel results;
    private JPanel gameOverPanel;
    private int sorted; // Number of correctly sorted items
    private int misplaced; // Number of incorrectly placed items
    private Dimension dm = new Dimension(300, 240); // Dimension for panel size

    // Constructor
    public GameOverFrame(WSGame game) {
        super();
        setPreferredSize(new Dimension(400, 300)); // Set the preferred frame size
        gameOverPanel = new JPanel();
        setBackground(new Color(0, 0, 0)); // Set background color to black
        setSize(dm); // Set the size of the frame
        setVisible(true); // Make the frame visible
        sorted = game.getCorrectItems(); // Get the number of correctly sorted items from the game
        misplaced = game.getIncorrectItems(); // Get the number of incorrectly placed items from the game
        gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS)); // Set panel layout
        centreOnScreen(); // Center the frame on the screen
        String resultString = "<html>You successfully sorted " + sorted + " waste items,<br/> you misplaced "
                + misplaced + " waste items.<br/> Do you want to play again?<html>";
        results = new JLabel(resultString, SwingConstants.CENTER); // Create a label with the result message

        restartButton = new JButton("Play again!"); // Create a button for restarting the game
        quitButton = new JButton("Quit"); // Create a button for quitting the game

        // ActionListener for the restart button
        restartButton.addActionListener(e -> {
            dispose(); // Close the current frame
            new InstructionFrame(); // Open a new instruction frame for the next game
        });

        // ActionListener for the quit button
        quitButton.addActionListener(e -> {
            dispose(); // Close the current frame
        });

        // Add components to the panel
        gameOverPanel.add(results);
        gameOverPanel.add(restartButton);
        gameOverPanel.add(quitButton);
        add(gameOverPanel); // Add the panel to the frame
        pack(); // Pack the components
    }

    // Center the frame on the screen
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize(); // Get screen dimensions
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2); // Calculate center position
    }
}
