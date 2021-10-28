public abstract class Frog extends Unit {
    private boolean isBuffed;
    private boolean isSpecialFrog;
    private boolean isDisabled;

    public Frog(int boardX, int boardY, Player p, Window w) {
        super(boardX, boardY, p, w);
    }

    //the xDiff and yDiff ints can either be 1, 0, or -1 and they are the way that the frog is moving in each direction
    //For example, if the frog is moving up and to the right they would have xDiff  = 1, yDiff = -1
    public void move(int xDiff, int yDiff){
        Tile[][] tileArr = getW().getBoard().getBoard();    //creating a tile array
        if (){

        }
        tileArr[getBoardX()][getBoardY()].setIsOccupied(0);    //sets the isOccupied of the tile the frog is moving off of to zero
        tileArr[getBoardX() + xDiff][getBoardY() + yDiff].setIsOccupied(getBelongsTo().getPlayerNumber());     //Sets the isOccupied of the tile the frog is moving onto to the appropriate number

    };

    public abstract void attack();

    public void layEgg(){
        //TODO: create lay egg method
    }

    public boolean isSpecialFrog() {

        return isSpecialFrog;
    }

    public void setSpecialFrog(boolean specialFrog) {

        isSpecialFrog = specialFrog;
    }
}
