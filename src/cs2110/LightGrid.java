package cs2110;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * A panel containing a grid of Light buttons
 */
public class LightGrid extends JPanel {

    /**
     * Grid size (Model)
     */
    private final int size;

    /**
     * Number of clicks since the game was reset. (Model)
     */
    private int numClicks;

    /**
     * Number of lit cells. Used to check if game has been won. (Model)
     */
    private int numLit;

    /**
     * Random generator used to initialize random board states (Model)
     */
    private final Random rand;

    /**
     * Array of lightbulb buttons (View)
     */
    private final LightButton[][] buttons;

    /**
     * Construct a JPanel with a `size`x`size` GridLayout of lightbulb buttons
     * <p> Initialize the model to a random configuration of lights
     */
    public LightGrid(int size) {
        this.size = size;
        rand = new Random(12345); // seed the randomness for reproducibility during testing
        setBackground(Color.DARK_GRAY);

        buttons = new LightButton[this.size][this.size];
        // TODO 4: Initialize each entry of `buttons` to be a `LightButton`.
        //  * These buttons should be arranged into a `size x size` grid in this `JPanel`. In
        //     addition, there should be horizontal and vertical gaps of 10 units between
        //     adjacent buttons. Think about which LayoutManager we can designate to achieve this.
        //  * For the button in the i'th row and the j'th column, call the method
        //      `addActionListener(processClick(i,j))`. This method will specify how the state of
        //      the model and view should be updated when this button is clicked.
        setLayout(new GridLayout(this.size, this.size, 10, 10));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                LightButton button = new LightButton();
                buttons[i][j] = button;
                button.addActionListener(processClick(i, j));
                add(button);
            }
        }

        reset(); // set up the game instance
    }

    ////////////////////////////
    // Controller: Game Logic //
    ////////////////////////////

    /**
     * Resets the game by generating a new random board and setting the number of clicks to 0.
     */
    public void reset() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (rand.nextBoolean()) {
                    toggleNeighborhood(i, j);
                }
            }
        }
        setNumClicks(0);
    }

    /**
     * Update the value of `numClicks` to `newClicks`. The value of `numClicks` should ONLY be
     * modified through this method to ensure that any observers are properly notified.
     */
    private void setNumClicks(int newNumClicks) {
        int oldNumClicks = numClicks;
        numClicks = newNumClicks;
        firePropertyChange("Clicks", oldNumClicks, numClicks);
    }

    /**
     * Process a click of cell (i,j) by toggling the surrounding cells, increasing the number of
     * clicks, and displaying a dialog if the player has won.
     */
    private ActionListener processClick(int i, int j) {
        return e -> {
            // TODO 8: Fill in the start of this lambda expression body to appropriately
            //    update the state of the view/model. You should
            //  * Update the state of the buttons neighboring the one that was just clicked.
            //  * Update the count of how many times a button has been clicked.
            toggleNeighborhood(i, j);
            setNumClicks(numClicks + 1);

            if (numLit == 0) { // show win message
                JOptionPane.showMessageDialog(this, "You won with " + numClicks +
                                " clicks.", "Congratulations!",
                        JOptionPane.PLAIN_MESSAGE);
                reset(); // reset for a new game
            }
        };
    }

    /**
     * Toggles cell (i,j) and any of its neighboring cells, updates 'numLit' appropriately.
     */
    private void toggleNeighborhood(int i, int j) {
        // TODO 7: Implement this method according to its specifications.
        for (int r = i - 1; r <= i + 1; r++) {
            for (int c = j - 1; c <= j + 1; c++) {
                if (r >= 0 && r < size && c >= 0 && c < size) {
                    if (r == i || c == j) {
                        numLit += buttons[r][c].toggle();
                    }
                }
            }
        }
    }
}