package cs2110;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 * A button that depicts a lightbulb in either a lit or unlit state
 */
public class LightButton extends JButton {

    /**
     * Whether this button depicts a lit (true) or unlit (false) lightbulb (Model)
     */
    private boolean isLit;

    public LightButton() {
        isLit = false;
        setPreferredSize(new Dimension(100, 100));
        setBorderPainted(false);
        setFocusable(false);
        setOpaque(true);
    }

    /**
     * Switches light between lit/unlit and requests a repaint. Returns change in the number of lit
     * bulbs: -1 if bulb toggles from lit to unlit, +1 if bulb toggles from unlit to lit.
     */
    public int toggle() {
        // TODO 6: Implement this method according to its specifications.
        // switches light between lit/unlit and requests a repaint 
        if (this.isLit) {
            this.isLit = false;
            this.repaint();
            return -1;
        } else {
            this.isLit = true;
            this.repaint();
            return 1;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // TODO 5: We've completed most of the intricate lightbulb drawing for you, but left you
        //     some of the bigger bits to fill in.
        //  * If this button `isLit`, then the background of the button should be `Color.YELLOW`,
        //     otherwise it should be `Color.BLACK`.
        //  * Draw the circular top of the bulb, which should be an oval with left corner at
        //     coordinates (26,16) and width and height 48. It should be colored `Color.WHITE`
        //     if the button `isLit`, otherwise it should be `Color.DARK_GRAY.brighter()`.
        g.setColor(isLit ? Color.YELLOW : Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(isLit ? Color.WHITE : Color.DARK_GRAY.brighter());
        g.fillOval(26, 16, 48, 48);


        // base
        g.setColor(isLit ? Color.LIGHT_GRAY : Color.DARK_GRAY);
        g.fillRoundRect(35, 60, 30, 20, 5, 5);

        // threads
        g.setColor(Color.BLACK);
        g.drawLine(36, 65, 64, 61);
        g.drawLine(36, 70, 64, 66);
        g.drawLine(36, 75, 64, 71);
        g.drawLine(36, 80, 64, 76);

        // filament
        g.drawLine(45, 60, 42, 45);
        g.drawLine(55, 60, 57, 45);
        g.fillOval(40, 43, 4, 4);
        g.fillOval(55, 43, 4, 4);
        g.drawPolyline(new int[]{41, 43, 45, 47, 49, 51, 53, 55, 57},
                new int[]{45, 43, 45, 43, 45, 43, 45, 43, 45}, 9);
    }
}
