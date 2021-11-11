import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AfricanBullFrog extends Frog {

    private boolean canUseUtility;
    private boolean canLiftAlly;
    private Unit ally;

    public Unit getAlly() {
        return ally;
    }

    public void setAlly(Unit ally) {
        this.ally = ally;
    }

    public AfricanBullFrog(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);
        setHitPoints(2);
        isHeavy = true;
        maxHitPoints = hitPoints;
        canLiftAlly = false;
        this.ally = null;

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
        if (canUseUtility(depart, arrivingAt)){
            depart.getOccupiedBy().moveToTile(arrivingAt);
            arrivingAt.getOccupiedBy().setClickedByAfricanBullfrog(false);
            arrivingAt.getOccupiedBy().setBeingMovedBy(null);
            hasPerformedAction = true;
            belongsTo.setEnergyNum(belongsTo.getEnergyNum() - 2);
        }

        onUnclicked();
    }

    public void onAllyClicked(Unit ally){

        this.ally = ally;
        ally.setClickedByAfricanBullfrog(true);
        ally.setBeingMovedBy(this);







        Tile[][] tileArr = getW().getBoard().getBoard();
        Tile current;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                current = tileArr[i][j];
                if (canUseUtility(this.ally.occupiedTile, current)) {
                    current.setAltColor(new Color(60, 60, 200));
                    canLiftAlly = true;

                }
            }
        }
        w.repaint();


        //This is a stupid dumb lazy way of doing this, and it probably won't work
//        Tile[][] tileArr = getW().getBoard().getBoard();
//        Tile current;
//        for(int i = 0; i < 8; i++){
//            for(int j = 0; j < 8; j++){
//                current = tileArr[i][j];
//                if(isValidOneTileRadius(current)){  //If the tile that the ally is trying to be moved to is empty, it sets the canUseUtility of that tile to true, its value will have to be changed back to false somewhere but im not sure where yet TODO: <-- come back to this when gameloop is closer to being completed
//                    current.setCanUseUtility(true);
//                }
//                if (current.getBoardX() == boardX && current.getBoardY() == boardY){
//                    current.setAltColor(Color.GREEN);
//                } else if (canAttack(current)){
//                    current.setAltColor(new Color(139,0,0));
//                } else if (canUseUtility(current)){
//                    current.setAltColor(new Color(60, 60, 200));
//                } else if (canMoveTo(current)){
//                    current.setAltColor(Color.YELLOW);
//                }
//                if (current.isCanUseUtility()){ //Maybe it works if I change it down here? Probably not tho
//                    current.setCanUseUtility(false);
//                }
//            }
//        }

    }


    public boolean canUseUtility(Tile depart, Tile arrivingAt){
        if (depart.getOccupiedBy() instanceof AfricanBullFrog) {
            return false;
        }
        if (arrivingAt.getIsOccupied() != 0){
            return false;
        }
        return !hasPerformedAction && (depart.getIsOccupied() == belongsTo.getPlayerNumber() && isValidOneTileRadius(arrivingAt) && isValidOneTileRadius(depart));
    }

    @Override
    public void onUnclicked(){
        super.onUnclicked();
        if (ally != null){
            ally.setClickedByAfricanBullfrog(false);
            ally.setBeingMovedBy(null);
            ally = null;

        }
    }

    @Override
    public void onClicked() {    //Cycles through all the tiles when a frog is clicked and changes the colour of any tiles that are moveable to, attackable, or able to use utility on
        Tile[][] tileArr = getW().getBoard().getBoard();
        Tile current;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                current = tileArr[i][j];
                if (current.getBoardX() == boardX && current.getBoardY() == boardY) {
                    if (isDisabled) {
                        current.setAltColor(Color.GRAY);
                    } else {
                        current.setAltColor(Color.GREEN);
                    }
                } else if (canAttack(current)) {
                    current.setAltColor(new Color(139, 0, 0));
                    current.setCanAttack(true);
                } else if (isValidOneTileRadius(current) && current.getIsOccupied() == belongsTo.getPlayerNumber() && !(current.getOccupiedBy() instanceof  AfricanBullFrog) && !current.getOccupiedBy().isHeavy()) {
                    current.setAltColor(new Color(60, 180, 200));
                    current.setCanUseUtility(true);
                } else if (canMoveTo(current)) {
                    current.setAltColor(Color.YELLOW);
                    current.setCanMoveTo(true);
                }
            }
        }
        isClicked = true;
        belongsTo.setHasClickedUnit(true);
        w.repaint();


    }
    public void useUtility(Tile depart, Tile arrivingAt){

    }

    @Override
    public void paint(Graphics2D g2d){
        if (hitPoints == 1){
            try {
                img = ImageIO.read(new File("res\\LowHPAfricanBullfrogSprite.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        }
        super.paint(g2d);
    }


    public boolean isCanLiftAlly() {
        return canLiftAlly;
    }

    public void setCanLiftAlly(boolean canLiftAlly) {
        this.canLiftAlly = canLiftAlly;
    }
}