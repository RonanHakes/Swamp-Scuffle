import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tile {
    private boolean isBuffed;
    private int isOccupied;
    private int homeColumn;
    private int graphicsX; // x coordinate for where tile is being painted
    private int graphicsY; // y coordinate for where tile is being painted
    private int boardX; // x coordinate for board
    private int boardY; // y coordinate for board
    private Unit occupiedBy;
    private Color altColor; //Any color other than the default team colours that a square should be painted
    private boolean canUseUtility;
    private boolean canMoveTo;
    private boolean canAttack;

//    private int

    public Tile(int boardX, int boardY, int graphicsX, int graphicsY) { // tile constructor
        this.boardX = boardX;
        this.boardY = boardY;
        this.graphicsX = graphicsX;
        this.graphicsY = graphicsY;
        canUseUtility = false;
    }



    public void paint(Graphics2D g2d) {

        if (isOccupied == 1) {
            if(altColor != null){   //Alt colors take priority
                g2d.setColor(altColor);
            } else {
                g2d.setColor(Color.BLUE);
            }
            g2d.fillRect(graphicsX, graphicsY, 100, 100);
            if(occupiedBy != null){
                occupiedBy.paint(g2d);
//              System.out.println("null1: " + this.toString());
                if (occupiedBy.altSprite != null){
                    occupiedBy.paintAltSprite(g2d);
                }

            }


        } else if (isOccupied == 2) {
            if(altColor != null){
                g2d.setColor(altColor);
            } else {
                g2d.setColor(Color.RED);
            }
            g2d.fillRect(graphicsX, graphicsY, 100, 100);
            if(occupiedBy != null){
                occupiedBy.paint(g2d);
                if (occupiedBy.altSprite != null){
                    occupiedBy.paintAltSprite(g2d);
                }
//                System.out.println("null2: " + this.toString());    //Prints out the tile, this is for testing

            }

        } else {
//            System.out.println("alt color (for real): " + altColor);
            if(altColor != null){
                g2d.setColor(altColor);
//                System.out.println("Current color: " + g2d.getColor()); //<- try this basym
            } else {
                g2d.setColor(new Color(20, 150, 40));
            }

            g2d.fillRect(graphicsX,graphicsY,100,100);


//
        }
        g2d.setColor(Color.BLACK);
        g2d.drawRect(graphicsX, graphicsY, 100, 100);



    }

    public void unclickWipe(){
        canMoveTo = false;
        canAttack = false;
        canUseUtility = false;
    }

    public void setIsOccupied(int isOccupied) {
        this.isOccupied = isOccupied;
    }

    public int getIsOccupied(){
        return isOccupied;
    }

    public void setOccupiedBy(Unit occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

    public Unit getOccupiedBy() {
        return occupiedBy;
    }

    public void setBoardY(int boardY) {
        this.boardY = boardY;
    }

    public void setBoardX(int boardX) {
        this.boardX = boardX;
    }

    public void setBuffed(boolean b){
        isBuffed = b;
    }

    public boolean isBuffed() {
        return isBuffed;
    }

    public int getBoardX() {
        return boardX;
    }

    public int getBoardY() {
        return boardY;
    }





    @Override
    public String toString() {
        if (occupiedBy == null){
            return "Tile{" +
                    "isBuffed=" + isBuffed +
                    ", isOccupied=" + isOccupied +
                    ", homeColumn=" + homeColumn +
                    ", graphicsX=" + graphicsX +
                    ", graphicsY=" + graphicsY +
                    ", boardX=" + boardX +
                    ", boardY=" + boardY +
                    '}';
        } else {
            return "Tile{" +
                    "isBuffed=" + isBuffed +
                    ", isOccupied=" + isOccupied +
                    ", homeColumn=" + homeColumn +
                    ", graphicsX=" + graphicsX +
                    ", graphicsY=" + graphicsY +
                    ", boardX=" + boardX +
                    ", boardY=" + boardY +
                    ", occupiedBy (info)=" + occupiedBy.getBelongsTo().getPlayerNumber() +
                    '}';
        }

    }

    public Color getAltColor() {
        return altColor;
    }

    public void setAltColor(Color altColor) {
        this.altColor = altColor;
    }

    public boolean isCanUseUtility() {
        return canUseUtility;
    }

    public void setCanUseUtility(boolean canUseUtility) {
        this.canUseUtility = canUseUtility;
    }

    public boolean isCanMoveTo() {
        return canMoveTo;
    }

    public void setCanMoveTo(boolean canMoveTo) {
        this.canMoveTo = canMoveTo;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }
}
