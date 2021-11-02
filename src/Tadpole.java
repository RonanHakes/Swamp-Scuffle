import java.awt.*;

public class Tadpole extends Unit{
    private Frog frogType;
    private int turnsAfterHatch;
    private boolean isDisabled;

    public Tadpole(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);
    }

    public void move() {
        //TODO: create move method
    }

    public void metamorphose() {
        //TODO: create metamorphose method
    }

    public void paint(Graphics2D g2d) {
        //TODO: create paint method
    }

    @Override
    public boolean canMoveTo(Tile t) {
        return false;
    }

    @Override
    public void onUnclicked() {

    }

    @Override
    public void onClicked() {

    }
}
