import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SharpNosedRocketFrog extends Frog {



    public SharpNosedRocketFrog(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);


        try {
            img = ImageIO.read(new File("res\\SharpNosedSprite.png"));
            if (img != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }

        try {
            zappedSprite = ImageIO.read(new File("res\\SharpNosedLightning.png"));
            if (zappedSprite != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
    }

@Override
    public boolean canMoveTo(Tile t) {
    if (isDisabled || belongsTo.getEnergyNum() <= 0 || hasPerformedAction){
        return false;
    }
        int x = t.getBoardX();
        int y = t.getBoardY();
        System.out.println("ot: " + getOccupiedTile());
        Tile avgTile = w.getBoard().avgTile(occupiedTile, t);
        System.out.println("at: " + avgTile);
        if(x < 0 || x > 7) {
        return false;
        }
        if(y < 0 || y > 7) {
        return false;
        }

        if (super.canMoveTo(t)) { // checks if it is moving only one square
            return true;
        }
        if ((avgTile.getIsOccupied() != 0 && isValidTwoTileRadius(t)) && t.getIsOccupied() == 0) {
            return true;
        }

        return false;
//        if (avgTile.getIsOccupied() != 0 && isValidTwoTileRadius(t)){  //Checks if there is a unit to jump over
//        return true;
//        } else {
//        return false;
//        }




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

        if ((x == boardX + 2 || x == boardX - 2) && (y != boardY + 1 && y != boardY - 1 )) {
            return true;
        }
        if ((y == boardY + 2 || y == boardY - 2) && (x != boardX + 1 && x != boardX - 1 )) {
            return true;
        }

        return false;
    }

    @Override
    public boolean canAttack(Tile t) {
        if (isDisabled || belongsTo.getEnergyNum() <= 1 || hasPerformedAction) {
            return false;
        }
        Tile avgTile = w.getBoard().avgTile(occupiedTile, t);
        int x = t.getBoardX();
        int y = t.getBoardY();
        if (t.getIsOccupied() != 0 && t.getIsOccupied() != belongsTo.getPlayerNumber()) {
            switch (belongsTo.getPlayerNumber()){
                case 1:
                    if (x == boardX + 1 && y >= boardY - 1 && y <= boardY + 1) {
                        return true;
                    }
                    break;
                case 2:
                    if (x == boardX - 1 && y >= boardY - 1 && y <= boardY + 1) {
                        return true;
                    }
                    break;
                default:
                    System.out.println(belongsTo.getPlayerNumber());
                    System.out.println("How on Earth did you get here?");
            }
        }
        if (((avgTile.getIsOccupied() != 0 && isValidTwoTileRadius(t)) && t.getIsOccupied() != w.getWhoseTurn().getPlayerNumber())){
            switch (belongsTo.getPlayerNumber()) {
                case 1 -> {

                    return t.getIsOccupied() == 2;
                }
                case 2 -> {

                    return t.getIsOccupied() == 1;
                }
                default -> System.out.println("Seriously, this isn't possible to reach");
            }
        }

        return false;
    }


    @Override
    public void onClicked(){
        Tile[][] tileArr = getW().getBoard().getBoard();
        Tile current;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                current = tileArr[i][j];
                if (current.getBoardX() == boardX && current.getBoardY() == boardY){
                    current.setAltColor(Color.GREEN);
                } else if (canAttack(current)){
                    current.setAltColor(new Color(139,0,0));
                    current.setCanAttack(true);
                } else if (canUseUtility(current)){
                    current.setAltColor(new Color(35,60,150));
                    current.setCanUseUtility(true);
                } else if (canMoveTo(current)){
                    current.setAltColor(Color.YELLOW);
                    current.setCanMoveTo(true);
                }
            }
        }
        isClicked = true;
        belongsTo.setHasClickedUnit(true);
        w.repaint();

    }


    @Override
    public void attack(Tile attackedTile){
        if (isValidTwoTileRadius(attackedTile)) {
            attackedTile.getOccupiedBy().takeDamage(1);

        }
        super.attack(attackedTile);
    }

}