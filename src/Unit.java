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
        if (belongsTo.getUnitsOwned().size()==0) {
            w.endGame();
        }
        w.repaint();
    }

    public void layEgg() {}
    public boolean canLayEgg() {return false;}


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
        ArrayList<Unit> temp = belongsTo.getUnitsOwned().add(this);
        belongsTo.setUnitsOwned(temp);
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

    public abstract void onUnclicked();  //Resets the alt colors of all the tiles when the frog is unclicked

    public abstract boolean canMoveTo(Tile t);

    public void move(Tile t){};

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
        occupiedTile = t;
        t.setOccupiedBy(this);
        t.setIsOccupied(belongsTo.getPlayerNumber());
        setBoardX(t.getBoardX());
        setBoardY(t.getBoardY());
        w.repaint();
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

    public boolean isMeanToad(){
        return false;
    }
}
