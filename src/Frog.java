import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Frog extends Unit {
    protected boolean isBuffed;
    protected boolean isSpecialFrog;
    protected boolean isDisabled;
    protected BufferedImage img = null;
    protected BufferedImage zappedSprite;
    int turnAfterAttack;

    public void increaseTurnAfterAttack() {
        turnAfterAttack++;
    }

    public Frog(int boardX, int boardY, Player p, Window w) {
        super(boardX, boardY, p, w);
        ArrayList<Frog> fl = belongsTo.getFrogsOwned();
        ArrayList<Unit> ul = belongsTo.getUnitsOwned();
        ul.add(this); // adds unit to end of unitsOwned list
        fl.add(this); // adds unit to end of frogsOwned list

        belongsTo.setfrogsOwned(fl);    //Actually moves those changes to player
        belongsTo.setUnitsOwned(ul);
        w.repaint();
        System.out.println("w: " + w);
    }

    public void setDisabled(boolean b){
        isDisabled = b;
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

    }


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

    @Override
    public void die(){      //this overrides the Unit class' die method so that if a Frog is dying, it will also be removed from the owning player's frogsOwned arrayList

        super.die();
        belongsTo.getFrogsOwned().removeIf(Unit -> (Unit == this)); //this removes the frog that is dying from the owner player's frogsOwned arrayList through the use of a lambda function

    }

    //Default frog attack method
    public void attack(Tile attackedTile){
        if (canAttack(attackedTile)){

            System.out.println("hpO: " + attackedTile.getOccupiedBy().getHitPoints());
            attackedTile.getOccupiedBy().takeDamage(1);
            belongsTo.giveEnergy(-2);
            System.out.println("hp: " + attackedTile.getOccupiedBy().getHitPoints());
            if (attackedTile.getOccupiedBy().getHitPoints() <= 0){
                System.out.println("WHAT");
                attackedTile.getOccupiedBy().die();
                if(isBuffed){
                    rewardKill(attackedTile.getOccupiedBy());
                }
            }
            hasPerformedAction = true;
            onUnclicked();
        }   //todo dont forget to add buffs
    }

    protected void attackNoCheck(Tile attackedTile){
        attackedTile.getOccupiedBy().takeDamage(1);
        belongsTo.giveEnergy(-2);
        if (attackedTile.getOccupiedBy().getHitPoints() <= 0){
            attackedTile.getOccupiedBy().die();
            if(isBuffed){
                rewardKill(attackedTile.getOccupiedBy());
            }

        }
        onUnclicked();
    }

    public boolean canAttack(Tile t){
        if (isDisabled || belongsTo.getEnergyNum() <= 1 || hasPerformedAction){
            return false;
        }

        return t.getIsOccupied() != 0 && t.getIsOccupied() != belongsTo.getPlayerNumber() && isValidOneTileRadius(t);   //Checks if the tile is occupied by an enemy and within a one tile radius
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
                    current.setCanAttack(true);
                } else if (canUseUtility(current)){
                    current.setAltColor(new Color(35,60,150));
                    current.setCanUseUtility(true);
                } else if (canMoveTo(current)){
                    current.setAltColor(Color.YELLOW);
                    current.setCanMoveTo(true);
                }
            }
        }
        isClicked = true;
        belongsTo.setHasClickedUnit(true);
        w.repaint();

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
                current.unclickWipe();
            }
        }
        isClicked = false;
        belongsTo.setHasClickedUnit(false);
        w.repaint();
    }

    public void rewardKill(Unit victim){
        belongsTo.giveEnergy(victim.getMaxHitPoints());
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

    public void layEgg(){
        System.out.println("Layed egg");
        //TODO: create lay egg method
        Tile[][] tileArr = w.getBoard().getBoard();
        int xCurrent = boardX;
        int yCurrent = boardY;

        if(canLayEgg()){
            Egg e = new Egg(xCurrent, yCurrent, belongsTo, this, w);

            switch(belongsTo.getPlayerNumber()){
                case 1:
                    moveToTile(tileArr[boardX + 1][boardY]);
                    e.moveToTile(tileArr[xCurrent][yCurrent]);
                case 2:
                    moveToTile(tileArr[boardX + 1][boardY]);
                    e.moveToTile(tileArr[xCurrent][yCurrent]);
                default:
                    System.out.println("impossible.");
            }
        }
    }

    public boolean canLayEgg(){      //Checks if an egg can be layed by the currently selected instance of frog
        Tile[][] tileArr = w.getBoard().getBoard();
        if (!isClicked) {
            return false;0
        }
        switch ((belongsTo.getPlayerNumber())){
            case 1:
                if (boardX == 0 && tileArr[boardX + 1][boardY].getIsOccupied() == 0){
                    return true;
                }
            case 2:
                if (boardX == 7 && tileArr[boardX - 1][boardY].getIsOccupied() == 0){
                    return true;
                }
            default:
                System.out.println("How");
        }
        return false;
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

//        if()

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
