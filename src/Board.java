import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class Board {



    private Tile[][] board = new Tile[8][8]; // create 2d array of tiles with length 8 and height 8

    public void paint(Graphics2D g2d) {
        for (int i = 0; i < 8; i++) { // loops horizontally
            for (int j = 0; j < 8; j++) { // loops vertically
                board[i][j] = new Tile(i, j, i * 100 + 560, j * 100 + 100); // sets graphicsX and graphicsY values for tile
                board[i][j].paint(g2d); // paints tile
                g2d.drawString(String.valueOf(i) + "," + String.valueOf(j), i * 100 + 560 + 50, j * 100 + 100 + 50); // writes coordinate on tile
            }
        }

    }


}


