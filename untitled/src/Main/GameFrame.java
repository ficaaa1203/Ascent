package Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;


public class GameFrame {
    // Create the frame of the video game (the window that is shown when opening any game)
    private JFrame frame;

    public GameFrame(GamePanel GP) {
        frame = new JFrame();
        // Terminate the application when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add the panel to the frame so it gets drawn
        JPanel layers = new JPanel();
        layers.setLayout(new OverlayLayout(layers));
        layers.add(GP); // added first = drawn on top
        frame.add(layers);
        // Disable manual resizing of the window
        frame.setResizable(false);
        // Size the window to fit the panel's preferred size
        frame.pack();
        // Center the window on the screen (null = no reference component)
        frame.setLocationRelativeTo(null);
         // Make the window visible (keep this at the end)
        frame.setVisible(true);
        GP.requestFocusInWindow();
    }
}
