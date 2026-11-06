package Input;

import Main.GamePanel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyboardInput implements KeyListener {

    private GamePanel robot; // Enter the robot as a parameter in order to manipulate its coords
    public KeyboardInput(GamePanel robot) {
        this.robot = robot;
    }

    // WE MUST CALL THOSE 3 METHODS ONLY KEYPRESSED IS IMPORTANT FOR US (BECAUSE ABSTRACT)
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {// Get keyboard input
            case KeyEvent.VK_W:
                robot.getPlayer().moveUp(-150);
                break;
            case KeyEvent.VK_S:
                robot.getPlayer().moveDown(150);
                break;
            case KeyEvent.VK_A:
                robot.getPlayer().moveLeft(-150);
                break;
            case KeyEvent.VK_D:
                robot.getPlayer().moveRight(150);
                break;
        }
    }
}
