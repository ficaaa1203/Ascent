package Main;

import java.awt.Dimension;

public class Game implements Runnable { // We must Runnable in order to build a game loop (TASK EXECUTED BY A THREAD)
    // Here we essentially build the game and integrate it into main
    // Introduce the GameWindow class we build in GameWindow.Java in main
    private GameFrame gameFrame;
    private GamePanel GP;
    private Thread thread;
    private int FPS = 120; // Set the FPS at which the game runs
    private int width, height;

    public Game() {
        GP = new GamePanel();
        setPanelSize(1200, 800);
        gameFrame = new GameFrame(GP);
        thread = new Thread(this); // Create the thread that executes run()
        thread.start(); // Start the game loop
    }

    private void setPanelSize(int width, int height) {
        this.width = width;
        this.height = height;
        Dimension size = new Dimension(width, height);
        GP.setPreferredSize(size);
    }

    public int getScreenWidth() {
        return width;
    }

    public int getScreenHeight() {
        return height;
    }

    public void run(){ // Run the game loop
        double timePerFrame = 1000000000.0 / FPS; // How long one frame lasts in nanoseconds (1s = 1.000.000.000ns), at 120 FPS ~8.3ms
        long lastTime = System.nanoTime(); // The moment the last frame was drawn (only differences between nanoTime values matter)
        long now = System.nanoTime(); // The current moment, read again on every pass of the loop
        while (true) { // The loop spins nonstop, checking the time on every pass
            now  = System.nanoTime(); // What time is it right now?
            if (now - lastTime >= timePerFrame) { // Has one frame's worth of time passed since the last frame?
                GP.repaint(); // Yes -> redraw the frame
                lastTime = now; // Reset the "stopwatch" so the next frame waits another timePerFrame (WITHOUT THIS WE WOULD REPAINT ON EVERY PASS)
            }
        }
    }
}
