package Entities;

import Level.TileGrid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import static Util.Constants.robotConstant.*;
import static Util.Constants.directions.*;


public class player extends entity {
    private BufferedImage[][] animations;
    private BufferedImage img;
    private boolean isMoving = false;
    private int animationIndex, animationTick, animationSpeed = 10;
    private int robotStatus = IDLE;
    private int robotDirection = DOWN;

    public int movementBoundaryDown;
    public int movementBoundaryUp;
    public int movementBoundaryLeft;
    public int movementBoundaryRight;

    public player(int x, int y, TileGrid tileGrid) {
        movementBoundaryDown = tileGrid.getDown_limit();
        movementBoundaryUp = tileGrid.getUp_limit();
        movementBoundaryLeft = tileGrid.getLeft_limit();
        movementBoundaryRight = tileGrid.getRight_limit();
        super(x, y);
        loadAnimations();
    }

    private void changexCoord(int value, int count) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int aux = count * 50;
            public void run() {
                aux--;
                x += value / 50.0;
                if(aux < 0){
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 20);
    }

    private void changeyCoord(int value, int count) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int aux = count * 50;
            public void run() {
                aux--;
                y += value / 50.0;
                if(aux < 0){
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 20);
    }

    public void moveUp(int value){
        new Thread(() -> {
            if (isMoving==true){
                return;
            }
            if (y + value < movementBoundaryUp){
                return;
            }
            try {
                isMoving = true;
                robotDirection = UP;
                robotStatus = MOVE;
                animationIndex = 0;
                changeyCoord(-50, 1); // take off
                Thread.sleep(1100);
                changeyCoord(value, 1); // move
                Thread.sleep(1100);
                changeyCoord(50, 1); // land
                Thread.sleep(1000);
                isMoving = false;
                robotStatus = IDLE;
                animationIndex = 0;
            } catch (InterruptedException e) {}
        }).start();
    }

    public void moveDown(int value){
        new Thread(() -> {
            if (isMoving==true){
                return;
            }
            if (y + value > movementBoundaryDown){
                return;
            }
            try {
                isMoving = true;
                robotDirection = DOWN;
                robotStatus = MOVE;
                animationIndex = 0;
                changeyCoord(-50, 1); // take off
                Thread.sleep(1100);
                changeyCoord(value, 1); // move
                Thread.sleep(1100);
                changeyCoord(50, 1); // land
                Thread.sleep(1000);
                isMoving = false;
                robotStatus = IDLE;
                animationIndex = 0;
            } catch (InterruptedException e) {}
        }).start();
    }

    public void moveLeft(int value){
        new Thread(() -> {
            if (isMoving==true){
                return;
            }
            if (x + value < movementBoundaryLeft){
                return;
            }
            try {
                isMoving = true;
                robotDirection = LEFT;
                robotStatus = MOVE;
                animationIndex = 0;
                changeyCoord(-50, 1); // take off
                Thread.sleep(1100);
                changexCoord(value, 1); // move
                Thread.sleep(1100);
                changeyCoord(50, 1); // land
                Thread.sleep(1000);
                isMoving = false;
                robotStatus = IDLE;
                animationIndex = 0;
            } catch (InterruptedException e) {}
        }).start();
    }

    public void moveRight(int value){
        new Thread(() -> {
            if (isMoving==true){
                return;
            }
            if (x + value > movementBoundaryRight){
                return;
            }
            try {
                isMoving = true;
                robotDirection = RIGHT;
                robotStatus = MOVE;
                animationIndex = 0;
                changeyCoord(-50, 1);
                Thread.sleep(1100);
                changexCoord(value, 1);
                Thread.sleep(1100);
                changeyCoord(50, 1);
                Thread.sleep(1000);
                isMoving = false;
                robotStatus = IDLE;
                animationIndex = 0;
            } catch (InterruptedException e) {}
        }).start();
    }
    public void updateAnimation(){
        animationTick++;
        if(animationTick > animationSpeed){
            animationTick = 0;
            animationIndex++;
            if(animationIndex >= getFrameAmount(robotStatus)){
                animationIndex = 0;
            }
        }
    }
    public void render(Graphics g){
        g.drawImage(animations[robotStatus == IDLE ? 4 + robotDirection : robotDirection][animationIndex], x, y, 160, 175, null);
    }
    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/Robot/FullSpriteSheet.png");
        try{
            img =  ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        animations = new BufferedImage[8][8];
        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(j * 64, i * 70, 64, 70);
            }
        }
    }
}
