package Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Tile {
    private BufferedImage img;
    private int x,y;
    private int width,height;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 32*5/2;
        this.height = 60*5/2;
        InputStream is = getClass().getResourceAsStream("/Island_Tile.png");
        try{
            img =  ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void render(Graphics g){
        g.drawImage(img, x, y, width, height, null);
    }
}
