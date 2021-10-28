import java.awt.*;
import java.util.ArrayList;

public class Player {
    private int numberofFrogs = 0;
    private int numberOfUnits = 0;
    private boolean isTurn;
    private int turnNumber = 0;
    private int energyNum = 3;
    private int playerNumber;
    private ArrayList<Unit> unitsOwned = new ArrayList<>();;

    public Player(int p) {
        playerNumber = p;
    }

    public void starterFrogTurn() {
        unitsOwned.add(new GoliathFrog(1, 1, this));
    }

    public void turn() {

        for (int i = 0; i < unitsOwned.size(); i++){

        }

        turnNumber++;
    }



    public void giveMeanToad() {
        //todo
    }

    public ArrayList<Unit> getUnitsOwned() {
        return unitsOwned;
    }
}
