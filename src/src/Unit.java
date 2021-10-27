import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Unit {
    protected int boardX;
    protected int boardY;
    protected int graphicsX;
    protected int graphicsY;

    protected int hitPoints;
    protected Player belongsTo;
    protected Tile occupiedTile;

    public abstract void paint(Graphics2D g2d);

    public void die(){
        //todo
    }

    public Unit(int boardX, int boardY, Player p) {
        this.boardX = boardX;
        this.boardY = boardY;
        this.graphicsX = this.boardX * 100 + 560 + 25;
        this.graphicsY = this.boardY * 100 + 100 + 25;
        this.belongsTo = p;
    }

}
