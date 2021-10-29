public abstract class Frog extends Unit {
    private boolean isBuffed;
    private boolean isSpecialFrog;
    private boolean isDisabled;
    private boolean hasPerformedAction;

    public Frog(int boardX, int boardY, Player p, Window w) {
        super(boardX, boardY, p, w);
    }

    //the xDiff and yDiff ints can either be 1, 0, or -1 and they are the way that the frog is moving in each direction
    //For example, if the frog is moving up and to the right they would have xDiff  = 1, yDiff = -1
    public void move(int xDiff, int yDiff){
        Tile[][] tileArr = getW().getBoard().getBoard();    //creating a tile array locally
        if (tileArr[getBoardX() + xDiff][getBoardY() + yDiff].getIsOccupied() == 0){
            tileArr[getBoardX()][getBoardY()].setIsOccupied(0);    //sets the isOccupied of the tile the frog is moving off of to zero
            tileArr[getBoardX() + xDiff][getBoardY() + yDiff].setIsOccupied(getBelongsTo().getPlayerNumber());     //Sets the isOccupied of the tile the frog is moving onto to the appropriate number
            tileArr[getBoardX() + xDiff][getBoardY() + yDiff].setOccupiedBy(tileArr[getBoardX()][getBoardY()].getOccupiedBy());     //sets the unit occupying the tile that is being moved to to the unit that is moving
            tileArr[getBoardX()][getBoardY()].setOccupiedBy(null);      //removes the unit that is moving from the occupiedBy field of the tile it is moving off of

            //Changing the actual location of the frog
            setBoardX(getBoardX() + xDiff);
            setBoardY(getBoardY() + yDiff);
            setOccupiedTile(tileArr[getBoardX()][getBoardY()]);
            hasPerformedAction = true;

        }

    };

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
}
