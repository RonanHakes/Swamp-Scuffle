import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Player {
    private Window w;
    private int numberofFrogs = 0;
    private boolean isTurn;
    private int turnNumber = 0;
    private int energyNum = 3;
    private int playerNumber;
    private ArrayList<Unit> unitsOwned = new ArrayList<>(); // arrayList of unitsOwned
    private ArrayList<Frog> frogsOwned = new ArrayList<>(); // arrayList of frog units that are owned
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

        //This will have to be changed later once we add the menu to pick a frog, this bit sets the frog the player picks
        GoliathFrog f = new GoliathFrog(x, y, this, w);

        System.out.println(starterFrogTurnCounter + " count!");
        starterFrogTurnCounter++;
        unitsOwned.add(f); // adds unit to end of unitsOwned list
        frogsOwned.add(f);
        w.getBoard().getBoard()[x][y].setIsOccupied(playerNumber); //Sets the tile that the frog is on to the correct isOccupied value
        System.out.println("isOccupied " + w.getBoard().getBoard()[x][y].getIsOccupied());
        w.getBoard().getBoard()[x][y].setOccupiedBy(f);
        f.moveToTile(w.getBoard().getBoard()[x][y]);    //I have no idea if this is redundant or not, check this out later <-- todo
        w.getBoard().getBoard()[x][y].paint(g2d);   //repaints the tile
        unitsOwned.get(unitsOwned.size()-1).paint(g2d); // paints last unit in list

    }

    public void turn() {
        turnNumber++; // increases turnNumber counter by 1
        if (frogsOwned.size() >= turnNumber) {
            energyNum +=turnNumber;
        } else {
            energyNum += frogsOwned.size();
        }
        while (!w.getButton().getIsClicked()) { //Checks if the end turn button is clicked. If not, then it runs the loop again
            if (unitsOwned.size() == 0) { // ends game if player has no units
                System.out.println("Game end!");
                System.exit(0);
            }


        }

    }



    public void giveMeanToad() {
        //todo
    }

    public Window getW() {
        return w;
    }

    public ArrayList<Unit> getUnitsOwned() {
        return unitsOwned;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public ArrayList<Frog> getFrogsOwned() {
        return frogsOwned;
    }

    public void setFrogsOwned(ArrayList<Frog> fArr) {
        frogsOwned = fArr;
    }

    public void setUnitsOwned(ArrayList<Unit> unitsOwned) {
        this.unitsOwned = unitsOwned;
    }

    public void setStarterFrogTurnCounter(int starterFrogTurnCounter) {
        this.starterFrogTurnCounter = starterFrogTurnCounter;
    }
}
