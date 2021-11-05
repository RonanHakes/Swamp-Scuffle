import java.awt.*;
import java.util.Arrays;

public class Egg extends Unit{

    private Class<?extends Frog> frogType;  //Frog type stores which subclass of Frog needs to be hatched from the egg
    private int turnsAfterLaid;

    public Egg(int boardX, int boardY, Player p, Frog parent, Window w){
        super(boardX, boardY, p, w);
        this.frogType = parent.getClass();
        System.out.println("FROG TYPE!!!!: " + frogType);
    }

//    public void hatch(){  TODO
//        Tadpole t = new Tadpole(boardX, boardY, belongsTo, frogType,w){
//
//        }
//        die();
//
//    }


    public void paint(Graphics2D g2d) {
        //TODO: create paint method
    }

    public void move() {
        //this doesn't do anything. Eggs can't move dummy
    }

    @Override
    public boolean canMoveTo(Tile t) {
        return false;
    }

    @Override
    public void onClicked() {

    }

    @Override
    public void onUnclicked() {

    }


}
