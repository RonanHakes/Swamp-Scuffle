import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PoisonDartFrog extends Frog {
    boolean isCharged = true;
    Tile targetTile;

    public PoisonDartFrog(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);
        setHitPoints(2);

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

    public void move(){
        //TODO: move
    }

    public void attack(){
        //TODO: Attack
    }
@Override
    public boolean canAttack(Tile t){
        if (t.getIsOccupied() != 0 && t.getIsOccupied() != belongsTo.getPlayerNumber()) {   //Checks if the tile being attacked is occupied by an enemy
            if (isValidOneTileRadius(t) || isValidTwoTileRadius(t)) {
                return true;
            }

        }
        return false;
    }

    public void attack(Tile attackedTile){
        if (isCharged) {
            if (canAttack(targetTile)){
                belongsTo.giveEnergy(-2);
                targetTile.getOccupiedBy().takeDamage(1);
                isCharged = false;
                if (targetTile.getOccupiedBy().getHitPoints() <= 0){
                    targetTile.getOccupiedBy().die();
                    if(isBuffed){
                        rewardKill(targetTile.getOccupiedBy());
                    }
                }
                targetTile = null;
                onUnclicked();
            }


        } else {
            isCharged = true;
            targetTile = attackedTile;
        }

    }


    @Override
    public boolean isValidTwoTileRadius(Tile t){  //This is a misnomer actually, because this will only return true not for a radius but only in straight lines
        if (!super.isValidTwoTileRadius(t)) {
            return false;
        }
        int x = t.getBoardX();
        int y = t.getBoardY();
        if(x < 0 || x > 7) {
            return false;
        }

        if(y < 0 || y > 7) {
            return false;
        }

        if ((x == boardX + 2 || x == boardY - 2) && (y != boardY + 1 && y != boardY - 1 )) {
            return true;
        }
        if ((y == boardY + 2 || y == boardY - 2) && (x != boardX + 1 && x != boardX - 1 )) {
            return true;
        }

        return false;
    }

}