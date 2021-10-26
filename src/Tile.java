import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tile {
    private boolean isBuffed;
    private boolean isOccupied;
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
        g2d.setColor(Color.BLACK);
        g2d.drawRect(graphicsX, graphicsY, 100, 100);
    }
}
