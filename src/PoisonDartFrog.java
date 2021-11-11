import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PoisonDartFrog extends Frog {
    boolean isCharged = true;
    int turnAfterAttack;

    public void increaseTurnAfterAttack() {
        turnAfterAttack++;
    }

    public PoisonDartFrog(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);
        turnAfterAttack = 2;
        if (p.getPlayerNumber() == 1){
            widthMultiplier = 1;
        } else {
            widthMultiplier = -1;
        }

        try {
            img = ImageIO.read(new File("res\\PoisonDartSprite.png"));
            if (img != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
        try {
            zappedSprite = ImageIO.read(new File("res\\PoisonDartLightning.png"));
            if (zappedSprite != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
    }

    @Override
    public boolean canAttack(Tile t) {
        if ((!isDisabled && belongsTo.getEnergyNum() >= 2 && t.getIsOccupied() != 0 && t.getIsOccupied() != belongsTo.getPlayerNumber() && !hasPerformedAction && isValidTwoTileRadius(t) && turnAfterAttack >= 2)) {
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public void attack(Tile attackedTile){
        if (canAttack(attackedTile)) {
            super.attack(attackedTile);
            turnAfterAttack = 0;
        } else {
            onUnclicked();
        }
        belongsTo.tileWipe();

    }

    public void resetCharge(){
        if (!isCharged){
            isCharged = true;
        }
    }


    @Override
    public boolean isValidTwoTileRadius(Tile t){  //This is a misnomer actually, because this will only return true not for a radius but only in straight lines
        if (!super.isValidTwoTileRadius(t)) {
            return false;
        }
        if (super.isValidOneTileRadius(t)) {
            return true;
        }

        int x = t.getBoardX();
        int y = t.getBoardY();
        if(x < 0 || x > 7) {
            return false;
        }

        if(y < 0 || y > 7) {
            return false;
        }

        if ((x == boardX + 2 || x == boardX - 2) && (y != boardY + 1 && y != boardY - 1 )) {
            return true;
        }
        if ((y == boardY + 2 || y == boardY - 2) && (x != boardX + 1 && x != boardX - 1 )) {
            return true;
        }

        return false;
    }

}