import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class MeanToad extends Frog {

    private boolean hasIncreasedHP = false;

    public boolean getHasIncreasedHP() {
        return hasIncreasedHP;
    }

    public void increaseHP() {
        hasIncreasedHP = true;
        hitPoints++;
    }

    public MeanToad(int boardY, int boardX, Player p, Window w){
        super(boardX, boardY, p, w);
        setHitPoints(3);

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
    }


    public boolean canAttack(Tile t){
        return t.getIsOccupied() != 0 && t.getIsOccupied() != belongsTo.getPlayerNumber() && isValidTwoTileRadius(t) && !isDisabled && belongsTo.getEnergyNum() >= 2;
    }

    @Override
    public boolean canMoveTo(Tile t){
        int x = t.getBoardX();
        int y = t.getBoardX();

        if (getW().getBoard().getBoard()[x][y].getIsOccupied() != 0 || !isValidTwoTileRadius(t) || isDisabled || belongsTo.getEnergyNum() <= 1) {
            return false;
        }
        return true;
    }

    @Override
    public void move(Tile t){
        super.move(t);
        int x = t.getBoardX();
        int y = t.getBoardX();
        for (int i = boardX - 1; i <= boardX + 1; i++) {   //Cycle through 8 squares around it, check if they are occupied, check if they are a mean toad
            for (int j = boardY - 1; j <= boardY + 1; j++) {
                if (i >= 0 && i <= 7 && j >= 0 && j <= 7 && (i != boardX || j != boardY)) {
                    if (getW().getBoard().getBoard()[i][j].getIsOccupied() != 0){
                        if (getW().getBoard().getBoard()[i][j].getOccupiedBy().isMeanToad()) {
                            if (!hasIncreasedHP) {
                                increaseHP();
                            }
                            if (!((MeanToad) getW().getBoard().getBoard()[i][j].getOccupiedBy()).hasIncreasedHP){   //Basym dum
                                ((MeanToad) getW().getBoard().getBoard()[i][j].getOccupiedBy()).increaseHP();
                            }
                        }
                    }
                }
            }
        }
        onUnclicked();
    }

    public void attack(Tile attackedTile){
        if (canAttack(attackedTile)){
            attackedTile.getOccupiedBy().takeDamage(2);
            if (attackedTile.getOccupiedBy().getHitPoints() <= 0){
                attackedTile.getOccupiedBy().die();
                if(isBuffed){
                    rewardKill(attackedTile.getOccupiedBy());
                }
            }
            onUnclicked();
        }   //todo dont forget to add buffs
    }

    @Override
    public boolean isMeanToad(){
        return true;
    }
}