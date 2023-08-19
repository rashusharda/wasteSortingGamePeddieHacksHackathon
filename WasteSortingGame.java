package ui;

import model.WSGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// This class represents the main game window for the Waste Sorting Game.
public class WasteSortingGame extends JFrame {
    private WSGame game; // The game logic
    private GamePanel gamePanel; // The panel for rendering the game
    private static final int INTERVAL = 5; // Timer interval in milliseconds
    private Timer timer; // Timer for updating the game
    private ScorePanel scorePanel; // Panel for displaying the score

    // Constructor
    public WasteSortingGame() {
        super("Waste Sorting Game"); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        game = new WSGame(); // Initialize the game logic
        gamePanel = new GamePanel(game); // Create the game panel
        scorePanel = new ScorePanel(game); // Create the score panel
        add(gamePanel); // Add the game panel to the frame
        add(scorePanel, BorderLayout.NORTH); // Add the score panel to the top of the frame
        pack(); // Pack the components
        addKeyListener(new KeyHandler()); //
