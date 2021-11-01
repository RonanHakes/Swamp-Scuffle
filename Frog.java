import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Frog extends Unit {
    private boolean isBuffed;
    private boolean isSpecialFrog;
    private boolean isDisabled;
    private boolean hasPerformedAction;
    protected BufferedImage img = null;
    protected int widthMultiplier;

    public Frog(int boardX, int boardY, Player p, Window w) {
        super(boardX, boardY, p, w);

        System.out.println("w: " + w);
    }


    public void move(int x, int y){
        Tile[][] tileArr = getW().getBoard().getBoard();    //creating a tile array locally
        if (tileArr[x][y].getIsOccupied() == 0){
            tileArr[getBoardX()][getBoardY()].setIsOccupied(0);    //sets the isOccupied of the tile the frog is moving off of to zero
            tileArr[x][y].setIsOccupied(getBelongsTo().getPlayerNumber());     //Sets the isOccupied of the tile the frog is moving onto to the appropriate number
            tileArr[x][y].setOccupiedBy(tileArr[getBoardX()][getBoardY()].getOccupiedBy());     //sets the unit occupying the tile that is being moved to to the unit that is moving
            tileArr[getBoardX()][getBoardY()].setOccupiedBy(null);      //removes the unit that is moving from the occupiedBy field of the tile it is moving off of

//            tileArr[getBoardX()][getBoardY()].paint(g2d);     //Removing g2d stuff come back later and delete this if everything works <-- todo

            //Changing the actual location of the frog
            moveToTile(tileArr[x][y]);  //this replaces the individually changing the BoardX and boardY of the frog and this actually works
            setOccupiedTile(tileArr[getBoardX()][getBoardY()]);
            hasPerformedAction = true;

            getW().getBoard().setBoard(tileArr);

//            paint(g2d);   //Removing g2d stuff come back later and delete this if everything works <-- todo
            w.repaint();
        }
    }

    @Override
    public void die(){      //this overrides the Unit class' die method so that if a Frog is dying, it will also be removed from the owning player's frogsOwned arrayList

        super.die();
        belongsTo.getFrogsOwned().removeIf(Unit -> (Unit == this)); //this removes the frog that is dying from the owner player's frogsOwned arrayList through the use of a lambda function

    }

    //Default frog attack method
    public void attack(Tile attackedTile){
        Tile[][] tileArr = getW().getBoard().getBoard();
        if (attackedTile.getIsOccupied() != 0 && attackedTile.getIsOccupied() != getBelongsTo().getPlayerNumber()){
            attackedTile.getOccupiedBy().takeDamage(1);
            if (attackedTile.getOccupiedBy().getHitPoints() <= 0){
                attackedTile.getOccupiedBy().die();
            }
        }
    };

    public void layEgg(){
        //TODO: create lay egg method
    }

    public boolean isSpecialFrog() {

        return isSpecialFrog;
    }

    public void setSpecialFrog(boolean specialFrog) {

        isSpecialFrog = specialFrog;
    }

    public void setHasPerformedAction(boolean hasPerformedAction) {
        this.hasPerformedAction = hasPerformedAction;
    }

    public boolean getHasPerformedAction() {
        return hasPerformedAction;
    }

    @Override
    public Window getW() {
        return super.getW();
    }

    public void paint(Graphics2D g2d){

        if (widthMultiplier == -1){     //There's gotta be a better way of doing this (changing the x co-ordinate based on which way it should be facing)
            g2d.drawImage(img, graphicsX + 50, graphicsY, 50 * widthMultiplier,50, null);
        } else {
            g2d.drawImage(img, graphicsX , graphicsY, 50 * widthMultiplier,50, null);
        }

    }
}
