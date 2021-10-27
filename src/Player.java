import java.awt.*;

public class Player {
    private int numberofFrogs = 0;
    private int numberOfUnits = 0;
    private boolean isTurn;
    private int turnNumber = 0;
    private int energyNum = 3;
    private int playerNumber;
    private Unit[] unitsOwned;

    public Player(int p) {
        playerNumber = p;
        this.unitsOwned = new Unit[]{new GoliathFrog(1, 1, this)};
    }

    public void starterFrogTurn() {

    }

    public void turn() {

        for (int i = 0; i < unitsOwned.length; i++){

        }

        turnNumber++;
    }



    public void giveMeanToad() {
        //todo
    }

    public Unit[] getUnitsOwned() {
        return unitsOwned;
    }
}
