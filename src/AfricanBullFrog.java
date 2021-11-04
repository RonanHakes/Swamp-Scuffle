import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AfricanBullFrog extends Frog {

    private boolean canUseUtility;

    public AfricanBullFrog(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);
        setHitPoints(2);

        if (p.getPlayerNumber() == 1){
            widthMultiplier = 1;
        } else {
            widthMultiplier = -1;
        }

        try {
            img = ImageIO.read(new File("res\\AfricanBullfrogSprite.png"));
            if (img != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
        try {
            zappedSprite = ImageIO.read(new File("res\\AfricanBullfrogLightning.png"));
            if (zappedSprite != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
    }

    @Override
    public boolean canAttack(Tile t) {
        return false;
    }

    public void liftAlly(Tile depart, Tile arrivingAt){
        if (canUseUtility(depart)){
            depart.getOccupiedBy().moveToTile(arrivingAt);
        }
    }

    public void onAllyClicked(){
        //This is a stupid dumb lazy way of doing this, and it probably won't work
        Tile[][] tileArr = getW().getBoard().getBoard();
        Tile current;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                current = tileArr[i][j];
                if(isValidOneTileRadius(current)){  //If the tile that the ally is trying to be moved to is empty, it sets the canUseUtility of that tile to true, its value will have to be changed back to false somewhere but im not sure where yet TODO: <-- come back to this when gameloop is closer to being completed
                    current.setCanUseUtility(true);
                }
                if (current.getBoardX() == boardX && current.getBoardY() == boardY){
                    current.setAltColor(Color.GREEN);
                } else if (canAttack(current)){
                    current.setAltColor(new Color(139,0,0));
                } else if (canUseUtility(current)){
                    current.setAltColor(new Color(35,60,150));
                } else if (canMoveTo(current)){
                    current.setAltColor(Color.YELLOW);
                }
                if (current.isCanUseUtility()){ //Maybe it works if I change it down here? Probably not tho
                    current.setCanUseUtility(false);
                }
            }
        }

    }

    @Override
    public boolean canUseUtility(Tile t){
        return t.isCanUseUtility()|| (t.getIsOccupied() == belongsTo.getPlayerNumber() && isValidOneTileRadius(t));

    }

    @Override
    public void useUtility(Tile t){
        //todo: fix this later
    }

    
}