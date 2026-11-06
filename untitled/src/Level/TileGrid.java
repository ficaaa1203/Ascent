package Level;

import java.awt.*;

public class TileGrid {
    private Tile[][] map;
    private int left_limit, right_limit, up_limit, down_limit;
    public TileGrid(int row, int col, int x, int y) {
        map = new Tile[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = new Tile(330 + i*x, 151 + j*y);
            }
        }
        left_limit = 290;
        right_limit = 290 + (row - 1) * x;
        up_limit = 51;
        down_limit = 51 + (col - 1) * y;
    }
    public void render(Graphics g) {
        for (int i=0;i<map.length;i++){
            for (int j=0;j<map[i].length;j++){
                map[i][j].render(g);
            }
        }
    }
    public int getLeft_limit(){
        return left_limit;
    }
    public int getRight_limit(){
        return right_limit;
    }
    public int getUp_limit(){
        return up_limit;
    }
    public int getDown_limit(){
        return down_limit;
    }
}
