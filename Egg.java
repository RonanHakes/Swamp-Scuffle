import java.awt.*;

public class Egg extends Unit{

    public Egg(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);
    }
    private Frog frogType;
    private int turnsAfterLaid;

    public void paint(Graphics2D g2d) {
        //TODO: create paint method
    }

    public void move() {
        //this doesn't do anything. Eggs can't move dummy
    }
}
