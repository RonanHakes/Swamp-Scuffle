import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tile {
    private boolean isBuffed;
    private int isOccupied;
    private int homeColumn;
    private int graphicsX; // x coordinate for where tile is being painted
    private int graphicsY; // y coordinate for where tile is being painted
    private int boardX; // x coordinate for board
    private int boardY; // y coordinate for board

    public Tile(int boardX, int boardY, int graphicsX, int graphicsY) { // tile constructor
        this.boardX = boardX;
        this.boardY = boardY;
        this.graphicsX = graphicsX;
        this.graphicsY = graphicsY;
    }

    public void paint(Graphics2D g2d) {
        if (isOccupied == 1) {
            g2d.setColor(Color.BLUE);
            g2d.fillRect(graphicsX, graphicsY, 100, 100);
        } else if (isOccupied == 2) {
            g2d.setColor(Color.RED);
            g2d.fillRect(graphicsX, graphicsY, 100, 100);
        } else {
            g2d.setColor(Color.BLACK);
            g2d.drawRect(graphicsX, graphicsY, 100, 100);
        }
    }

    public void setIsOccupied(int isOccupied) {
        this.isOccupied = isOccupied;
    }

    public int getIsOccupied(){
        return isOccupied;
    }
}
