import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Unit {
    protected int boardX;
    protected int boardY;
    protected int graphicsX;
    protected int graphicsY;
    protected Window w;

    protected int hitPoints;
    protected Player belongsTo;
    protected Tile occupiedTile;

    public abstract void paint(Graphics2D g2d);

    public void die(){
        //todo
        occupiedTile.setIsOccupied(0);
        occupiedTile.setOccupiedBy(null);
        belongsTo.getUnitsOwned().removeIf(Unit -> (Unit == this));
        System.out.println("post-lamda:" + belongsTo.getUnitsOwned());
    }

    public Unit(int boardX, int boardY, Player p, Window w) {
        this.boardX = boardX;
        this.boardY = boardY;
        this.graphicsX = this.boardX * 100 + 560 + 30;
        this.graphicsY = this.boardY * 100 + 100 + 30;
        this.belongsTo = p;
        hitPoints = 1;
    }

    public void takeDamage(int d){
        hitPoints -= d;
    }
    public void setBoardX(int boardX) {
        this.boardX = boardX;
        graphicsX = boardX * 100 + 585;
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
        getOccupiedTile().setOccupiedBy(null);
        t.setOccupiedBy(this);
        setBoardX(t.get);
    }
}
