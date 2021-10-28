import java.awt.*;
import java.util.ArrayList;

public class Player {
    private int numberofFrogs = 0;
    private int numberOfUnits = 0;
    private boolean isTurn;
    private int turnNumber = 0;
    private int energyNum = 3;
    private int playerNumber;
    private ArrayList<Unit> unitsOwned = new ArrayList<>(); // arrayList of unitsOwned

    public Player(int p) {
        playerNumber = p;
    }

    public void starterFrogTurn(Graphics2D g2d) { // changed it so starterFrogTurn can paint the frog after creating it
        unitsOwned.add(new GoliathFrog(5, 5, this)); // adds unit to end of unitsOwned list
        unitsOwned.get(unitsOwned.size()-1).paint(g2d); // paints last unit in list
    }

    public void turn() {
        turnNumber++; // increases turnNumber counter by 1

        for (int i = 0; i < unitsOwned.size(); i++){

        }

        if (unitsOwned.size() == 0) { // ends game if player has no units
            System.out.println("Game end!");
            System.exit(0);
        }
    }



    public void giveMeanToad() {
        //todo
    }

    public ArrayList<Unit> getUnitsOwned() {
        return unitsOwned;
    }
}
