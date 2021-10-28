import java.awt.*;
import java.util.ArrayList;

public class Player {
    private Window w;
    private int numberofFrogs = 0;
    private int numberOfUnits = 0;
    private boolean isTurn;
    private int turnNumber = 0;
    private int energyNum = 3;
    private int playerNumber;
    private ArrayList<Unit> unitsOwned = new ArrayList<>(); // arrayList of unitsOwned
    private int starterFrogTurnCounter = 0;

    public Player(int p, Window w) {
        playerNumber = p;
        this.w = w;

    }

    public void starterFrogTurn(Graphics2D g2d) { // changed it so starterFrogTurn can paint the frog after creating it

        int x = 0;
        int y = starterFrogTurnCounter;
        if (playerNumber == 1){
            x = 0;
        } else if (playerNumber == 2){
            x = 7;
        }


        System.out.println(starterFrogTurnCounter + " count!");
        starterFrogTurnCounter++;

        unitsOwned.add(new GoliathFrog(x, y, this)); // adds unit to end of unitsOwned list
        w.getBoard().getBoard()[x][y].setIsOccupied(playerNumber); //Sets the tile that the frog is on to the correct isOccupied value
        unitsOwned.get(unitsOwned.size()-1).paint(g2d); // paints last unit in list
        System.out.println("isOccupied " + w.getBoard().getBoard()[x][y].getIsOccupied());
        w.getBoard().getBoard()[x][y].paint(g2d);   //repaints the tile
    }

    public void turn() {
        turnNumber++; // increases turnNumber counter by 1


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

    public int getPlayerNumber() {
        return playerNumber;
    }
}
