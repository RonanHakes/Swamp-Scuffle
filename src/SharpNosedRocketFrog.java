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
    if (isDisabled || belongsTo.getEnergyNum() <= 0){
        return false;
    }
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

    @Override
    public boolean canAttack(Tile t){
        if (isDisabled || belongsTo.getEnergyNum() <= 1){
            return false;
        }
        int x = t.getBoardX();
        int y = t.getBoardX();
        if (t.getIsOccupied() != 0 && t.getIsOccupied() != belongsTo.getPlayerNumber()){
            switch (belongsTo.getPlayerNumber()){
                case 1:
                    if(x == boardX + 1 && y >= boardY - 1 && y <= boardY + 1) {
                        return true;
                    }
                case 2:
                    if(x == boardX - 1 && y >= boardY - 1 && y <= boardY + 1) {
                        return true;
                    }
                default:
                    System.out.println("How on Earth did you get here?");

            }


        }
        return false;
    }

    public boolean canJumpAttack(Tile t){   //Checks if a tile can be attacked by the jump move
        int x = t.getBoardX();
        int y = t.getBoardX();

        Tile avgTile = w.getBoard().avgTile(occupiedTile, t);

        if (avgTile.getIsOccupied() != 0){
            switch(belongsTo.getPlayerNumber()){
                case 1:
                    return t.getIsOccupied() == 2;
                case 2:
                    return t.getIsOccupied() == 1;
                default:
                    System.out.println("Seriously, this isn't possible to reach");
            }
        }
        return false;

    }

    @Override
    public void attack(Tile attackedTile){
        if (canJumpAttack(attackedTile)){
            attackedTile.getOccupiedBy().takeDamage(2);
            if(attackedTile.getOccupiedBy().getHitPoints() <= 0){
                attackedTile.getOccupiedBy().die();
            }
        } else if (canAttack(attackedTile)){
            attackedTile.getOccupiedBy().takeDamage(1);
            if (attackedTile.getOccupiedBy().getHitPoints() <= 0){
                attackedTile.getOccupiedBy().die();
                if(isBuffed){
                    rewardKill(attackedTile.getOccupiedBy());
                }
            }
            onUnclicked();
        }   //todo dont forget to add buffs
    }

}