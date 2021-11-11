import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SpringPeeper extends Frog {


    public SpringPeeper(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);

        if (p.getPlayerNumber() == 1){
            widthMultiplier = 1;
        } else {
            widthMultiplier = -1;
        }

        try {
            img = ImageIO.read(new File("res\\SpringPeeperSprite.png"));
            if (img != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
        turnAfterAttack = 2;
    }

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
    @Override
    public boolean canMoveTo(Tile t) {
        return super.canMoveTo(t) && turnAfterAttack >= 2;
    }

    @Override
    public boolean canAttack(Tile attackedTile) {
        return false;
    }


    public boolean canUseUtility(Tile t) {
        if ((!isDisabled && belongsTo.getEnergyNum() >= 2 && t.getIsOccupied() != 0 && t.getIsOccupied() != belongsTo.getPlayerNumber() && !hasPerformedAction && isValidTwoTileRadius(t) && turnAfterAttack >= 2)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void useUtility(Tile t) {
        if (canUseUtility(t)) {
            t.getOccupiedBy().setDisabled(true);
            belongsTo.giveEnergy(-2);
            hasPerformedAction = true;
            onUnclicked();
            turnAfterAttack = 0;
        } else {
            onUnclicked();
        }

    }


}