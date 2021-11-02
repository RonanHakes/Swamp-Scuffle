import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SharpNosedRocketFrog extends Frog {

    public SharpNosedRocketFrog(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);
        setHitPoints(2);

        if (p.getPlayerNumber() == 1){
            widthMultiplier = 1;
        } else {
            widthMultiplier = -1;
        }

        try {
            img = ImageIO.read(new File("res\\SharpNosedSprite.png"));
            if (img != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
    }

@Override
    public boolean canMoveTo(Tile t) {
        int x = t.getBoardX();
        int y = t.getBoardX();
        Tile avgTile = w.getBoard().avgTile(occupiedTile, t);
        if(x < 0 || x > 7) {
        return false;
        }
        if(y < 0 || y > 7) {
        return false;
        }

        if (super.canMoveTo(t)) { // checks if it is moving only one square
            return true;
        }
        if (x > boardX + 2 || x < boardX - 2) { // checks if x value of tile to be moved to is 2 tiles or less away from current tile
        return false;
        }
        if (y > boardY + 2 || y < boardY - 2) { // checks if y value of tile to be moved to is 2 tiles or less away from current tile
        return false;
        }
        if (avgTile.getIsOccupied() != 0){  //Checks if there is a unit to jump over
            return true;
        } else {
            return false;
        }




    }


    public void attack(){
        //TODO: Attack
    }
}