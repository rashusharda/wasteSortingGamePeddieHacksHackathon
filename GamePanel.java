package ui;

import model.WSGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// This class represents the panel where the game is rendered.
public class GamePanel extends JPanel {
    // Constants for "Game Over!" and replay instructions
    private static final String OVER = "Game Over!";
    private static final String REPLAY = "R to replay";

    // Image source for the background
    private static final String imagePath = "src/data/images/background.jpg";
    private WSGame game; // The game instance to be displayed
    private Image bgImg; // Background image

    // Constructor
    public GamePanel(WSGame g) {
        setPreferredSize(new Dimension(WSGame.WIDTH, WSGame.HEIGHT)); // Set panel size
        this.game = g; // Initialize the game instance
        try {
            // Load and scale the background image
            bgImg = ImageIO.read(new File(imagePath)).getScaledInstance(WSGame.WIDTH, WSGame.HEIGHT, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            System.out.print("Failed to load background image");
            e.printStackTrace();
        }
    }

    // This method is called when the panel is being repainted
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        g.drawImage(bgImg, 0, 0, null);

        // Render the game elements
        renderGame(g);

        // If the game is over, draw the "game over" message and replay instructions
        if (game.isOver()) {
            gameOver(g);
        }
    }

    // Draws the "game over" message and replay instructions
    private void gameOver(Graphics g) {
        Color saved = g.getColor(); // Save the current color
        g.setColor(new Color(0, 0, 0)); // Set color for the message
        g.setFont(new Font("Arial", Font.PLAIN, 20)); // Set font for the message
        FontMetrics fm = g.getFontMetrics();
        // Center the "Game Over!" message and replay instructions vertically
        centreString(OVER, g, fm, WSGame.HEIGHT / 2);
        centreString(REPLAY, g, fm, WSGame.HEIGHT / 2 + 40);
        g.setColor(saved); // Restore the original color
    }

    // Draws the game elements
    private void renderGame(Graphics g) {
        game.render(g); // Delegate the rendering to the game instance
    }

    // Centers a string on the screen horizontally
    private void centreString(String str, Graphics g, FontMetrics fm, int yPos) {
        int width = fm.stringWidth(str);
        // Calculate the x-coordinate to center the string
        g.drawString(str, (WSGame.WIDTH - width) / 2, yPos);
    }
}
