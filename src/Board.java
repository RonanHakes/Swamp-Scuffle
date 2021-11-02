import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class Board {

    private Tile[][] board; // create 2d array of tiles with length 8 and height 8

    public Board(){
        board = new Tile[8][8];
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                board[i][j] = new Tile(i, j, i * 100 + 560, j * 100 + 100);     //Fills up the board and sets the proper values for each tile
            }
        }
    }

    public void paint(Graphics2D g2d) {
        for (int i = 0; i < 8; i++) { // loops horizontally
            for (int j = 0; j < 8; j++) { // loops vertically
                board[i][j].paint(g2d); // paints tile
                g2d.drawString(String.valueOf(i) + "," + String.valueOf(j), i * 100 + 560 + 50, j * 100 + 100 + 50); // writes coordinate on tile <--remove eventually
            }
        }



    }

    public Tile[][] getBoard() {
        return board;
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }

    public Tile avgTile(Tile t1, Tile t2){      //Returns the tile that is closest to the mid point between two tiles
        return board[(t1.getBoardX() + t2.getBoardX()) / 2][(t1.getBoardY() + t2.getBoardX()) / 2];
    }


}


