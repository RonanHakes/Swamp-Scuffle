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

    public boolean canMoveTo(Tile t) {
        int x = t.getBoardX();
        int y = t.getBoardX();
        if (super.canMoveTo(t)) {
            return true;
        }
        if(x < 0 || x > 7) {
            return false;
        }
        if(y < 0 || y > 7) {
            return false;
        }
        if (x > boardX + 2 || x < boardX - 2) { // checks if x value of tile to be moved to is 2 tiles or less away from current tile
            return false;
        }
        if (y > boardY + 2 || y < boardY - 2) { // checks if y value of tile to be moved to is 2 tiles or less away from current tile
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
    }

    public void attack(Tile t){
        //TODO: Attack
    }

    @Override
    public boolean isMeanToad(){
        return true;
    }
}