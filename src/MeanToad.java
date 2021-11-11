import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MeanToad extends Frog {

    private boolean hasIncreasedHP = false;

    public boolean getHasIncreasedHP() {
        return hasIncreasedHP;
    }

    public void increaseHP() {
        hasIncreasedHP = true;
        hitPoints++;
    }

    public MeanToad(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);
        isHeavy = true;
        setHitPoints(3);
        maxHitPoints = hitPoints;

        if (p.getPlayerNumber() == 1){
            widthMultiplier = 1;
        } else {
            widthMultiplier = -1;
        }

        try {
            img = ImageIO.read(new File("res\\MeanToadSprite.png"));
            if (img != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
        try {
            zappedSprite = ImageIO.read(new File("res\\MeanToadLightning.png"));
            if (zappedSprite != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
        checkForFriends();
    }


    public boolean canAttack(Tile t){
        return !hasPerformedAction && t.getIsOccupied() != 0 && t.getIsOccupied() != belongsTo.getPlayerNumber() && isValidTwoTileRadius(t) && !isDisabled && belongsTo.getEnergyNum() >= 2;
    }

    @Override
    public void layEgg() {}


    @Override
    public boolean canMoveTo(Tile t){
        if(isDisabled || belongsTo.getEnergyNum() <= 0 || hasPerformedAction){
            return false;
        }
        int x = t.getBoardX();
        int y = t.getBoardY();

        if (getW().getBoard().getBoard()[x][y].getIsOccupied() != 0 || !isValidOneTileRadius(t) && !isValidTwoTileRadius(t)) {
            return false;
        }
        return true;
    }

    @Override
    public void move(Tile t){
        super.move(t);
        int x = t.getBoardX();
        int y = t.getBoardX();
        checkForFriends();
        onUnclicked();
    }

    @Override
    public void moveToTile(Tile t){
        super.moveToTile(t);
        checkForFriends();
    }

    private void checkForFriends(){
        for (int i = 0; i < 8; i++) {   //Cycle through 8 squares around it, check if they are occupied, check if they are a mean toad
            for (int j = 0; j < 8; j++) {
                System.out.println("blahblahblah!@!!!!");
                if ( getW().getBoard().getBoard()[i][j].getIsOccupied() != 0 && isValidOneTileRadius(getW().getBoard().getBoard()[i][j]) && (i != boardX || j != boardY)) {
                    if (getW().getBoard().getBoard()[i][j].getOccupiedBy() instanceof MeanToad){
                        if (!hasIncreasedHP) {
                            increaseHP();
                        }
                        if (!((MeanToad) getW().getBoard().getBoard()[i][j].getOccupiedBy()).hasIncreasedHP){   //Basym dum
                            System.out.println("olololololololol");
                            ((MeanToad) getW().getBoard().getBoard()[i][j].getOccupiedBy()).increaseHP();
                        }

                    }
                }
            }
        }
    }

    public void attack(Tile attackedTile){
        if (canAttack(attackedTile)){
            belongsTo.giveEnergy(-2);
            attackedTile.getOccupiedBy().takeDamage(2);
            if (attackedTile.getOccupiedBy().getHitPoints() <= 0){
                if(isBuffed){
                    rewardKill(attackedTile.getOccupiedBy());
                }
                attackedTile.getOccupiedBy().die();
                moveToTile(attackedTile);
                if (belongsTo.getUnitsOwned().size()==0) {
                    System.out.println(Arrays.deepToString(belongsTo.getUnitsOwned().toArray()));
                    w.endGame();
                    System.out.println("WRONG WRONG WRONG");
                }
            }
            hasPerformedAction = true;
            onUnclicked();
        }
    }

    @Override
    public boolean isMeanToad(){
        return true;
    }

    @Override
    public void paint(Graphics2D g2d){
        if (hitPoints == 1){
            try {
                img = ImageIO.read(new File("res\\MEANESTTOADSprite.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        } else if (hitPoints == 2){
            try {
                img = ImageIO.read(new File("res\\LowHPMeanToadSprite.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        } else if (hitPoints == 4){
            try {
                img = ImageIO.read(new File("res\\LovingToadSprite.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        }
        super.paint(g2d);
    }
}