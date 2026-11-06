// Within the frame we color on the panel!! Our character will be a robot

package Main;

import java.awt.*;
import javax.swing.JPanel;
import Entities.player;
import Input.KeyboardInput;
import Level.Tile;
import Level.TileGrid;

public class GamePanel extends JPanel {
    private player player;
    private TileGrid TG;

    public GamePanel() { // Introduce input to our robot character
        TG  = new TileGrid(4, 4, 153, 153);
        player = new player(290, 51, TG);
        addKeyListener(new KeyboardInput(this)); // KeyboardInput is the movement input config we created in input
        setOpaque(false);
        setFocusable(true); // The panel must be focusable to receive key events
    }

    public player getPlayer() {
        return player;
    }

    @Override
    protected void paintComponent(Graphics g) { // g is basically a pen
        super.paintComponent(g); // Tell g to start painting something
        TG.render(g);
        player.updateAnimation();
        player.render(g);
    }
}
