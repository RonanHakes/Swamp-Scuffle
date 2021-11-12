import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Unit {
    protected int boardX;
    protected int boardY;
    protected int graphicsX;
    protected int graphicsY;
    protected Window w;
    protected BufferedImage img = null;
    protected int hitPoints;
    protected int maxHitPoints;
    protected Player belongsTo;
    protected Tile occupiedTile;
    protected boolean hasPerformedAction;
    protected BufferedImage altSprite;
    protected boolean isHeavy;
    protected int widthMultiplier;
    protected boolean isClicked;
    protected boolean isBuffed;
    protected boolean isDisabled = false;
    private boolean isClickedByAfricanBullfrog;
    private AfricanBullFrog beingMovedBy;

    public void setHeavy(boolean heavy) {
        isHeavy = heavy;
    }

    public boolean isHeavy() {
        return isHeavy;
    }

    public abstract void paint(Graphics2D g2d);

    public void paintAltSprite(Graphics2D g2d){
        g2d.drawImage(altSprite,graphicsX + 50, graphicsY, 50 * widthMultiplier,50, null);
    }

    public BufferedImage getAltSprite() {
        return altSprite;
    }

    public void setAltSprite(BufferedImage altSprite) {
        this.altSprite = altSprite;
    }

    public void die(){
        occupiedTile.setIsOccupied(0);
        occupiedTile.setOccupiedBy(null);
        belongsTo.getUnitsOwned().removeIf(Unit -> (Unit == this)); //this removes the unit that is dying from the owner player's unitsOwned arrayList through the use of a lambda function
        System.out.println("post-lambda:" + belongsTo.getUnitsOwned().toString());

        w.repaint();
    }

    public void layEgg() {}
    public boolean canLayEgg() {return false;}

    public void setDisabled(boolean b){
        isDisabled = b;
        if (b){
            occupiedTile.setAltColor(Color.GRAY);
        }
    }


    public Unit(int boardX, int boardY, Player p, Window w) {
        Tile[][] tileArr = w.getBoard().getBoard();
        System.out.println("tiles: " + Arrays.deepToString(tileArr));
        this.boardX = boardX;
        this.boardY = boardY;
        this.graphicsX = this.boardX * 100 + 560 + 30;
        this.graphicsY = this.boardY * 100 + 100 + 30;
        this.belongsTo = p;
        this.w = w;
        tileArr[boardX][boardY].setIsOccupied(belongsTo.getPlayerNumber());
        tileArr[boardX][boardY].setOccupiedBy(this);
        belongsTo.getUnitsOwned().add(this);
        System.out.println("Player " + belongsTo.getPlayerNumber() + " Units Owned: " + belongsTo.getUnitsOwned().size());
        //ArrayList<Unit> temp = belongsTo.getUnitsOwned().add(this);
        //belongsTo.setUnitsOwned(temp);
        occupiedTile = tileArr[boardX][boardY];
        w.getBoard().setBoard(tileArr);

        if (p.getPlayerNumber() == 1){
            widthMultiplier = 1;
        } else {
            widthMultiplier = -1;
        }

        hitPoints = 1;
        maxHitPoints = hitPoints;
        isHeavy = false;
        isClicked = false;
        System.out.println("HP:" + hitPoints);
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean b){
        isClicked = b;
    }



    public void takeDamage(int d){
        hitPoints -= d;
    }

    public void setBoardX(int boardX) {
        this.boardX = boardX;
        graphicsX = boardX * 100 + 585;
    }



    public abstract void onClicked();    //Cycles through all the tiles when a frog is clicked and changes the colour of any tiles that are moveable to, attackable, or able to use utility on

    public void onUnclicked(){  //Resets the alt colors of all the tiles when the frog is unclicked
        Tile[][] tileArr = getW().getBoard().getBoard();
        Tile current = null;

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                current = tileArr[i][j];
                if (current.isCanAttack() || current.isCanUseUtility() || current.isCanMoveTo() || current.getIsOccupied() != 0){
                    current.setAltColor(null);
                }
//                if (w.getMeanButton().getNumClicked() != 0 || w.getMeanButton().isClicked()){
//                    System.out.println("numC: " + w.getMeanButton().getNumClicked())
//                    current.setAltColor(null);
//                }


                current.unclickWipe();
            }
        }
        isClicked = false;
        belongsTo.setHasClickedUnit(false);
        w.repaint();
    }  //Resets the alt colors of all the tiles when the frog is unclicked

    public boolean canMoveTo(Tile t){
        if(isDisabled || belongsTo.getEnergyNum() <= 0 || hasPerformedAction){
            return false;
        }
        int x = t.getBoardX();
        int y = t.getBoardY();

        if (getW().getBoard().getBoard()[x][y].getIsOccupied() != 0 || !isValidOneTileRadius(t)) {
            return false;
        }
        return true;
    }

    public void move(Tile t) {
        if (canMoveTo(t)) {
            moveToTile(t);
            hasPerformedAction = true;
            setOccupiedTile(t);
            belongsTo.giveEnergy(-1);   //Giving the player -1 energy subtracts 1 energy for moving
        }
        onUnclicked();
        w.repaint();
        belongsTo.tileWipe();
    }

    public void attack(Tile t){}

    public void useUtility(Tile t){};

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public int getBoardX(){
        return boardX;
    }

    public void setBoardY(int boardY) {
        this.boardY = boardY;
        graphicsY = boardY * 100 + 125;
    }

    public int getBoardY(){
        return boardY;
    }

    public Player getBelongsTo() {
        return belongsTo;
    }

    public Window getW() {
        return w;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHasPerformedAction(boolean hasPerformedAction) {
        this.hasPerformedAction = hasPerformedAction;
    }

    public boolean getHasPerformedAction() {
        return hasPerformedAction;
    }

    public Tile getOccupiedTile() {
        return occupiedTile;
    }

    public void setOccupiedTile(Tile occupiedTile) {
        this.occupiedTile = occupiedTile;
    }

    public void moveToTile(Tile t){     //A method to move a unit onto a specific instance of the tile class, should make my life easier

        //This bit essentially removes the unit from the occupied tile that it is being moved off of, as long as it is actually being moved off of a tile
        if (getOccupiedTile() != null){
            getOccupiedTile().setOccupiedBy(null);
            getOccupiedTile().setIsOccupied(0);
        }
        this.isBuffed = t.isBuffed(); // If the tile being moved to is buffed, the unit will now be buffed as well, and vice versa
        occupiedTile = t;
        t.setOccupiedBy(this);
        t.setIsOccupied(belongsTo.getPlayerNumber());
        setBoardX(t.getBoardX());
        setBoardY(t.getBoardY());
        w.repaint();
    }

    protected boolean isValidOneTileRadius(Tile t){   //Returns true if the tile in question is a valid tile on the board and is within a one tile radius of the frog
        int x = t.getBoardX();
        int y = t.getBoardY();

        //Checks if the tile is actually on the board
        if(x < 0 || x > 7) {
            return false;
        }

        if(y < 0 || y > 7) {
            return false;
        }

        //Checks 1 square radius
        if (x > boardX + 1 || x < boardX - 1) {
            return false;
        }
        if (y > boardY + 1 || y < boardY - 1) {
            return false;
        }

        //Checks if the tile is the one that the unit is already on
        if (y == boardY && x == boardX){
            return false;
        }
        return true;
    }

    protected boolean isValidTwoTileRadius(Tile t){   //Returns true if the tile in question is a valid tile on the board and is within a two tile radius of the frog
        if (isValidOneTileRadius(t)) {
            return true;
        }
        int x = t.getBoardX();
        int y = t.getBoardY();

        //Checks if the tile is actually on the board
        if(x < 0 || x > 7) {
            return false;
        }

        if(y < 0 || y > 7) {
            return false;
        }

        if (y == boardY && x == boardX){
            return false;
        }

        //Checks 2 square radius
        if (x > boardX + 2 || x < boardX - 2) {
            return false;
        }
        if (y > boardY + 2 || y < boardY - 2) {
            return false;
        }

        //Checks if the tile is the one that the unit is already on

        return true;
    }

    public boolean isFrog(){    //I'm so smart, this gets overridden in Frog and returns true
        return false;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "boardX=" + boardX +
                ", boardY=" + boardY +
                ", graphicsX=" + graphicsX +
                ", graphicsY=" + graphicsY +
                ", w=" + w +
                ", hitPoints=" + hitPoints +
                ", belongsTo=" + belongsTo +
                ", occupiedTile=" + occupiedTile +
                '}';
    }


    public boolean isClickedByAfricanBullfrog() {
        return isClickedByAfricanBullfrog;
    }

    public void setClickedByAfricanBullfrog(boolean clickedByAfricanBullfrog) {
        isClickedByAfricanBullfrog = clickedByAfricanBullfrog;
    }

    public AfricanBullFrog getBeingMovedBy() {
        return beingMovedBy;
    }

    public void setBeingMovedBy(AfricanBullFrog beingMovedBy) {
        this.beingMovedBy = beingMovedBy;
    }
}
