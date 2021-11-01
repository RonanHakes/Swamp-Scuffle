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
    public Unit occupiedBy;

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
            occupiedBy.paint(g2d);
            System.out.println("wAWAWAW: " + occupiedBy.toString());
        } else if (isOccupied == 2) {
            g2d.setColor(Color.RED);
            g2d.fillRect(graphicsX, graphicsY, 100, 100);
            occupiedBy.paint(g2d);
            System.out.println("wAWAWAW: " + occupiedBy.toString());
        } else {
            g2d.setColor(Color.BLACK);
            g2d.drawRect(graphicsX, graphicsY, 100, 100);
            System.out.println("weenie fart");
        }


    }

    public void setIsOccupied(int isOccupied) {
        this.isOccupied = isOccupied;
    }

    public int getIsOccupied(){
        return isOccupied;
    }

    public void setOccupiedBy(Unit occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

    public Unit getOccupiedBy() {
        return occupiedBy;
    }

    public void setBoardY(int boardY) {
        this.boardY = boardY;
    }

    public void setBoardX(int boardX) {
        this.boardX = boardX;
    }

    public int getBoardX() {
        return boardX;
    }

    public int getBoardY() {
        return boardY;
    }
}
