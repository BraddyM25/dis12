package cs2110;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 * Main class for Lights Out game. Creates window with game board, score label, and reset button.
 */
public class LightsOutApp extends JFrame {

    /**
     * Constructs application window with title "Lights Out", a label displaying the number of
     * clicks at the top, a reset button at the bottom, and the game grid in the center.
     */
    public LightsOutApp(int size) {

        //////////////////////////////
        // View: Visible Components //
        //////////////////////////////

        // TODO 2: Use inherited methods from the JFrame class to set the following properties:
        //  * The application window should be titled "Lights Out"
        //  * When the user closes the application window, the application should terminate
        //     gently (i.e., adopting the behavior signaled by `JFrame.DISPOSE_ON_CLOSE`
        //  * The user should not be able to resize the window by dragging its edges
        this.setTitle("Lights Out");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();

        // A label displaying the number of clicks
        JLabel clicksLabel = new JLabel("Clicks: 0", SwingConstants.CENTER);
        clicksLabel.setFont(clicksLabel.getFont().deriveFont(30.0f)); // increase font size
        clicksLabel.setBackground(Color.DARK_GRAY);
        clicksLabel.setOpaque(true);
        clicksLabel.setForeground(Color.WHITE); // text color
        clicksLabel.setBorder(new EmptyBorder(10, 10, 10, 10)); // padding around text

        // The panel containing the main game grid
        LightGrid grid = new LightGrid(size);

        // A button to reset the board and start a new game
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(resetButton.getFont().deriveFont(30.0f)); // increase font size

        // TODO 3: Complete the build-out of the view by doing the following.
        //  * Add this panel to the center of the frame, with the `clicksLabel` positioned
        //     above it and the `resetButton` positioned below it.
        //  *  Ask the layout manager to determine the appropriate sizes/layouts of these widgets.
        add(grid, BorderLayout.CENTER);
        add(resetButton, BorderLayout.SOUTH);
        add(clicksLabel, BorderLayout.NORTH);
        pack();

        ////////////////////////////
        // Controller: Game Logic //
        ////////////////////////////

        // When the reset button is clicked, the GameGrid should be reset.
        resetButton.addActionListener(e -> grid.reset());

        // When the game's score changes, update the score label.
        // The corresponding part of the model (numClicks) lives in GameGrid,
        // but the part of the view (clicksLabel) lives here. We'll need to use
        // a PropertyChangeListener to detect the change in the model.
        grid.addPropertyChangeListener("Clicks",
                e -> clicksLabel.setText("Clicks: " + e.getNewValue()));
    }

    /**
     * Start the application
     */
    public static void main(String[] args) {
        // TODO 1: Create a new `LightsOutApp` with a 5x5 light grid on the event dispatch
        //  thread and make it appear on the screen.
        SwingUtilities.invokeLater(() -> {
            LightsOutApp app = new LightsOutApp(5);
            app.setVisible(true);
        });
    }
}
