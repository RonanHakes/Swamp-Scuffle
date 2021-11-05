import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.*;

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
    private boolean isStarterFrogTurn;

    public void mouseClicked(MouseEvent e) {

    }
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (isStarterFrogTurn) {
            if (playerNumber == 1) {
                if ( x >= 50 && x <= 200 && y>= 0 && y <= 50) {
                    if (getW().getBoard().getBoard()[0][1].getIsOccupied() == 0) {
                        AfricanBullFrog f = new AfricanBullFrog(0, 1, this, w);
                    }
                }
            } else {
                if ( x >= 50 && x <= 1920 && y>= 0 && y <= 50) {
                    if (getW().getBoard().getBoard()[0][1].getIsOccupied() == 0) {
                        AfricanBullFrog f = new AfricanBullFrog(0, 1, this, w);
                    }
                }
            }

        }
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

    public void setfrogsOwned(ArrayList<Frog> fl){
        this.frogsOwned = fl;
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
        MeanToad f = new MeanToad(x, y, this, w);

        System.out.println(starterFrogTurnCounter + " count!");
        starterFrogTurnCounter++;

        w.getBoard().getBoard()[x][y].setIsOccupied(playerNumber); //Sets the tile that the frog is on to the correct isOccupied value
        System.out.println("isOccupied " + w.getBoard().getBoard()[x][y].getIsOccupied());

        f.moveToTile(w.getBoard().getBoard()[x][y]);    //I have no idea if this is redundant or not, check this out later <-- todo

        w.repaint();
    }

    public void paint(Graphics2D g2d) {
        if (this.playerNumber == 1) {
            for (int i = 0; i < 7; i++) {
                g2d.drawRect(50, i*50+150, 150, 50);
                g2d.drawString(w.getListOfChoosableFrogTypes()[i], 50, i * 50 + 175);
            }

        } else {
            for (int i = 0; i < 7; i++) {
                g2d.drawRect(1920-200, i*50+150, 150, 50);
                g2d.drawString(w.getListOfChoosableFrogTypes()[i], 1920-200, i * 50 + 175);
            }
        }
    }

    public void turn() throws InterruptedException {
        Tile[][] tileArr = w.getBoard().getBoard();
        w.getButton().setIsClicked(false);
        turnNumber++; // increases turnNumber counter by 1
        System.out.println("Hello");
        if (frogsOwned.size() >= turnNumber) {
            energyPerTurn = turnNumber;
        } else {
        System.out.println("Frogs ownes size " + frogsOwned.size());
            energyPerTurn = frogsOwned.size();
        }
        energyNum += energyPerTurn;
        w.repaint();
        System.out.println("turn: " + w.getWhoseTurn().playerNumber);
        for (int i = 0; i < frogsOwned.size(); i++) {
            frogsOwned.get(i).setHasPerformedAction(false);

        }
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                tileArr[i][j].setAltColor(null);
            }
        }
        System.out.println("turno " + turnNumber);

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
