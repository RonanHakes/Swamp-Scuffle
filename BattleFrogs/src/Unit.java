import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Unit {
    private int x;
    private int y;
    private int hitPoints;
    private Player belongsTo;
    private Tile occupiedTile;

    public abstract void paint(Graphics2D g2d);

    public void die(){
        //todo
    }
}
