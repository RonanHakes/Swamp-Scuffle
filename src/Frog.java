public abstract class Frog extends Unit {
    private boolean isBuffed;
    private boolean isSpecialFrog;
    private boolean isDisabled;

    public Frog(int boardX, int boardY, Player p) {
        super(boardX, boardY, p);
    }

    public abstract void move();

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
