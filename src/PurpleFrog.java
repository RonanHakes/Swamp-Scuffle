import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PurpleFrog extends Frog {

    public PurpleFrog(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);
        setHitPoints(2);

        if (p.getPlayerNumber() == 1){
            widthMultiplier = 1;
        } else {
            widthMultiplier = -1;
        }

        try {
            img = ImageIO.read(new File("res\\PurpleFrogSprite.png"));
            if (img != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }

        try {
            zappedSprite = ImageIO.read(new File("res\\PurpleFrogLightning.png"));
            if (zappedSprite != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
    }

    @Override
    public void attack(Tile attackedTile){
        Tile[][] tileArr = getW().getBoard().getBoard();
        int xDiff = attackedTile.getBoardX() - occupiedTile.getBoardX();
        int yDiff = attackedTile.getBoardY() - occupiedTile.getBoardY();
        Tile toCheck = tileArr[attackedTile.getBoardX() + xDiff][attackedTile.getBoardY() + yDiff];     //gets the tile behind the attacking tile so that it can be checked

        super.attack(attackedTile); //Attacks the attacked tile
        altSprite = zappedSprite;
        if (toCheck.getIsOccupied() != 0 && toCheck.getIsOccupied() != belongsTo.getPlayerNumber()){
            belongsTo.giveEnergy(2);    //This sucks
            super.attackNoCheck(toCheck);   //Attacks the tile behind the first if it is occupied

        }
    }
}