package ui;

import model.WSGame;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

// This class represents a panel displaying the score and statistics of the game.
public class ScorePanel extends JPanel implements Observer {

    // Constants for label text
    private static final String CORRECT_TXT = "Waste correctly sorted: ";
    private static final String INCORRECT_TXT = "Waste misplaced: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;

    // Labels for displaying scores
    private JLabel correctLbl;
    private JLabel misplacedLbl;

    // Constructor
    public ScorePanel(WSGame game) {
        setBackground(new Color(255, 255, 255)); // Set background color
        correctLbl = new JLabel(CORRECT_TXT + 0); // Initialize correct items label
        correctLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT)); // Set label dimensions
        misplacedLbl = new JLabel(INCORRECT_TXT + 0); // Initialize misplaced items label
        misplacedLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT)); // Set label dimensions
        add(correctLbl); // Add the correct items label to the panel
        add(Box.createVerticalStrut(10)); // Add a vertical spacing
        add(misplacedLbl); // Add the misplaced items label to the panel
    }

    // Update method from the Observer interface
    @Override
    public void update(Observable o, Object arg) {
        if (WSGame.CORRECTLY_SORTED.equals(arg) || WSGame.MISPLACED.equals(arg)) {
            WSGame game = (WSGame) o;
            correctLbl.setText(CORRECT_TXT + game.getCorrectItems()); // Update correct items count
            misplacedLbl.setText(INCORRECT_TXT + game.getIncorrectItems()); // Update misplaced items count
            repaint(); // Repaint the panel
        } else if (WSGame.GameStarts.equals(arg)) {
            WSGame game = (WSGame) o;
            correctLbl.setText(CORRECT_TXT + 0); // Reset correct items count
            misplacedLbl.setText(INCORRECT_TXT + 0); // Reset misplaced items count
            repaint(); // Repaint the panel
        }
    }
}
