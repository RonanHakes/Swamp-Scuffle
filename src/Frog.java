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


    public void move(Tile t) {

        if (canMoveTo(t)) {
            moveToTile(t);
            hasPerformedAction = true;
            setOccupiedTile(t);
            onUnclicked();
        }
        w.repaint();
    }

    @Override
    public boolean canMoveTo(Tile t){
        int x = t.getBoardX();
        int y = t.getBoardX();

        if (getW().getBoard().getBoard()[x][y].getIsOccupied() != 0 || !isValidOneTileRadius(t)) {
            return false;
        }
        return true;
    }

    @Override
    public void die(){      //this overrides the Unit class' die method so that if a Frog is dying, it will also be removed from the owning player's frogsOwned arrayList

        super.die();
        belongsTo.getFrogsOwned().removeIf(Unit -> (Unit == this)); //this removes the frog that is dying from the owner player's frogsOwned arrayList through the use of a lambda function

    }

    //Default frog attack method
    public void attack(Tile attackedTile){
        if (canAttack(attackedTile)){
            attackedTile.getOccupiedBy().takeDamage(1);
            if (attackedTile.getOccupiedBy().getHitPoints() <= 0){
                attackedTile.getOccupiedBy().die();
            }
            onUnclicked();
        }   //todo dont forget to add buffs and unclick
    };

    public boolean canAttack(Tile t){

        return t.getIsOccupied() != 0 && t.getIsOccupied() != belongsTo.getPlayerNumber() && isValidOneTileRadius(t);
    }

    @Override
    public void onClicked(){    //Cycles through all the tiles when a frog is clicked and changes the colour of any tiles that are moveable to, attackable, or able to use utility on
        Tile[][] tileArr = getW().getBoard().getBoard();
        Tile current;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                current = tileArr[i][j];
                if (current.getBoardX() == boardX && current.getBoardY() == boardY){
                    current.setAltColor(Color.GREEN);
                } else if (canAttack(current)){
                    current.setAltColor(new Color(139,0,0));
                } else if (canUseUtility(current)){
                    current.setAltColor(new Color(35,60,150));
                } else if (canMoveTo(current)){
                    current.setAltColor(Color.YELLOW);
                }
            }
        }

        /*
        The hierarchy of which action on a tile should take priority (and its color) goes as follows:

            --Green: it is the tile the selected unit is on--
            --Dark Red: it is a tile the selected unit can attack--
            --Deep Blue: the selected unit can use a utility ability on that tile--
            --Yellow: the selected unit can move to that tile--
            --Corresponding team color: the tile is occupied by a team member--
            --White: nothing can be done on this tile, and it is unoccupied--
        */
    }

    @Override
    public void onUnclicked(){  //Resets the alt colors of all the tiles when the frog is unclicked
        Tile[][] tileArr = getW().getBoard().getBoard();
        Tile current = null;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                current = tileArr[i][j];
                current.setAltColor(null);
            }
        }
    }


    public boolean canUseUtility(Tile t){ //This will be overridden by the frogs who have utility moves, but do not remove here!
        return false;
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

//        System.out.println(this);

    }

    @Override
    public boolean isFrog(){    //Overrides the isFrog in Unit, so that if the unit is a frog, it will return true and if a unit is not, it will return false
        return true;
    }

    @Override
    public String toString() {
        return "Frog{" +
                "isBuffed=" + isBuffed +
                ", isSpecialFrog=" + isSpecialFrog +
                ", isDisabled=" + isDisabled +
                ", hasPerformedAction=" + hasPerformedAction +
                ", img=" + img +
                ", widthMultiplier=" + widthMultiplier +
                "} " + super.toString();
    }

    @Override
    public boolean isMeanToad(){
        return false;
    }
}
