import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Player implements MouseListener{
    private Window w;
    private int numberofFrogs = 0;
    private boolean isTurn;
    private int turnNumber = 0;
    private int energyNum = 3;
    private int playerNumber;
    private ArrayList<Unit> unitsOwned = new ArrayList<>(); // arrayList of unitsOwned
    private ArrayList<Frog> frogsOwned = new ArrayList<>(); // arrayList of frog units that are owned
    private int starterFrogTurnCounter = 0;
    private boolean hasClickedUnit;
    private int energyPerTurn;
    private PlayerInfoSegment pIS;

    public void mouseClicked(MouseEvent e) {

    }
    public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }

    public Player(int pNum, Window w) {
        playerNumber = pNum;
        this.w = w;
        hasClickedUnit = false;
        pIS = new PlayerInfoSegment(this);
    }

    public boolean isHasClickedUnit() {
        return hasClickedUnit;
    }

    public void setHasClickedUnit(boolean hasClickedUnit) {
        this.hasClickedUnit = hasClickedUnit;
    }

    public void wipeAll(){      //Allows everything from the previous game to be reset to starting default so a new game can begin
        numberofFrogs = 0;
        turnNumber = 0;
        energyNum = 3;
        unitsOwned.clear();
        frogsOwned.clear();
    }

    public void starterFrogTurn() { // changed it so starterFrogTurn can paint the frog after creating it

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

        f.moveToTile(w.getBoard().getBoard()[x][y]);    //I have no idea if this is redundant or not, check this out later <-- todo

        w.repaint();
    }

    public void turn() throws InterruptedException {
        w.getButton().setIsClicked(false);
        turnNumber++; // increases turnNumber counter by 1

        w.repaint();
        System.out.println("turn: " + w.getWhoseTurn().playerNumber);
        for (int i = 0; i < frogsOwned.size(); i++) {
            frogsOwned.get(i).setHasPerformedAction(false);
        }
        System.out.println("turno " + turnNumber);
        if (frogsOwned.size() >= turnNumber) {
            energyPerTurn = turnNumber;
        } else {
            energyPerTurn = frogsOwned.size();
        }
        energyNum += energyPerTurn;
        /*while (!w.getButton().getIsClicked()) { //Checks if the end turn button is clicked. If not, then it runs the loop again
            if (unitsOwned.size() == 0) { // ends game if player has no units
                System.out.println("Game end!");
                System.exit(0);
            }

        }

         */

    }






    public void giveMeanToad() {
        //todo
    }

    public Window getW() {
        return w;
    }

    public int getTurnNumber() {
        return turnNumber;
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

    public int getEnergyNum() {
        return energyNum;
    }


    public void setEnergyNum(int energyNum) {
        this.energyNum = energyNum;
    }

    public int getEnergyPerTurn() {
        return energyPerTurn;
    }

    public void giveEnergy(int num){
        if (energyNum + num < 0){
            setEnergyNum(0);
        } else {
            setEnergyNum(energyNum + num);
        }

    }

    public PlayerInfoSegment getpIS() {
        return pIS;
    }

    public void setpIS(PlayerInfoSegment pIS) {
        this.pIS = pIS;
    }
}
