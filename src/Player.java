import java.util.ArrayList;
import java.util.Iterator;
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
    private ArrayList<Tadpole> tadpolesOwned = new ArrayList<>(); // arrayList of tadpoles units that are owned
    private ArrayList<Egg> eggsOwned = new ArrayList<>(); // arrayList of egg units that are owned
    private int starterFrogTurnCounter = 0;
    private boolean hasClickedUnit;
    private int energyPerTurn;
    private PlayerInfoSegment pIS;
    private boolean isStarterFrogTurn;
    private int homeColumn;
    public int getHomeColumn() {
        return homeColumn;
    }
    private Player opponent;

    public ArrayList<Egg> getEggsOwned() {
        return eggsOwned;
    }

    public ArrayList<Tadpole> getTadpolesOwned() {
        return tadpolesOwned;
    }




    public Player getOpponent() {
        return opponent;
    }

    public int getStarterFrogTurnCounter() {
        return starterFrogTurnCounter;
    }

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
        pIS = new PlayerInfoSegment(this, w);
        if (playerNumber == 1) {
            homeColumn = 0;
        } else {
            homeColumn = 7;
        }
        if (w.getp1() == this) {
            opponent = w.getp2();
        } else {
            opponent = w.getp1();
        }
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

    public boolean getIsStarterFrogTurn(){
        return isStarterFrogTurn;
    }


    public void starterFrogTurn(MouseEvent e) { // changed it so starterFrogTurn can paint the frog after creating it
        int isClicked = 0;
        int x = e.getX();
        int y = e.getY();
        for (int i = 0; i < 7; i++) {
            if ( x >= 50 && x <= 200 && y>= 150 + i * 50 && y <= 200 + i * 50) {
                System.out.println("Player " + playerNumber + " starter frog turn");
                //if (getW().getBoard().getBoard()[homeColumn][starterFrogTurnCounter].getIsOccupied() == 0) {
//                    if (i == 0) {
//                        new AfricanBullFrog(homeColumn, starterFrogTurnCounter, this, w);
//                    } else if (i == 1) {
//                        new BluePoisonArrowFrog(homeColumn, starterFrogTurnCounter, this, w);
//                    } else if (i == 2) {
//                        new GoliathFrog(homeColumn, starterFrogTurnCounter, this, w);
//                    } else if (i == 3) {
//                        new PoisonDartFrog(homeColumn, starterFrogTurnCounter, this, w);
//                    } else if (i == 4) {
//                        new PurpleFrog(homeColumn, starterFrogTurnCounter, this, w);
//                    } else if (i == 5) {
//                        new SharpNosedRocketFrog(homeColumn, starterFrogTurnCounter, this, w);
//                    } else {
//                        new SpringPeeper(homeColumn, starterFrogTurnCounter, this, w);
//                    }
                    isClicked = i + 1;
                    System.out.println("Is Clicked " + isClicked);
//                    w.repaint();
//                    if (playerNumber == 1) {
//                        w.setWhoseStarterFrogTurn(w.getp2());
//                    } else {
//                        w.setWhoseStarterFrogTurn(w.getp1());
//                    }
//                    starterFrogTurnCounter++;
                //}
            }
        }

        if (isClicked != 0) {
            for (int i = 0; i < 8; i++) {
                if (getW().getBoard().getBoard()[homeColumn][i].getIsOccupied() == 0) {
                    System.out.println("WORKING!!!!!");
                    getW().getBoard().getBoard()[homeColumn][i].setAltColor(Color.YELLOW);
                    w.repaint();
                }

                if (x >= homeColumn * 100 + 560 && x <= homeColumn * 100 + 560 + 100 && y >= i * 100 + 100 && y <= i * 100 + 200 && getW().getBoard().getBoard()[homeColumn][i].getIsOccupied() == 0) {
                    if (isClicked == 1) {
                        System.out.println("i: " + i);
                        new AfricanBullFrog(homeColumn, i, this,w);
                        isClicked = 0;
                        starterFrogTurnCounter++;
                        for (int j = 0; j < 8; j++) {
                            getW().getBoard().getBoard()[homeColumn][j].setAltColor(null);
                        }
                        w.repaint();
                        if (playerNumber == 1) {
                            w.setWhoseStarterFrogTurn(w.getp2());
                        } else {
                            w.setWhoseStarterFrogTurn(w.getp1());
                        }
                        break;
                    } else if (isClicked == 2) {
                        new BluePoisonArrowFrog(homeColumn, i, this,w);
                        isClicked = 0;
                        starterFrogTurnCounter++;
                        for (int j = 0; j < 8; j++) {
                            getW().getBoard().getBoard()[homeColumn][j].setAltColor(null);
                        }
                        w.repaint();
                        if (playerNumber == 1) {
                            w.setWhoseStarterFrogTurn(w.getp2());
                        } else {
                            w.setWhoseStarterFrogTurn(w.getp1());
                        }
                        break;
                    } else if (isClicked == 3) {
                        new GoliathFrog(homeColumn, i, this,w);
                        isClicked = 0;
                        starterFrogTurnCounter++;
                        for (int j = 0; j < 8; j++) {
                            getW().getBoard().getBoard()[homeColumn][j].setAltColor(null);
                        }
                        w.repaint();
                        if (playerNumber == 1) {
                            w.setWhoseStarterFrogTurn(w.getp2());
                        } else {
                            w.setWhoseStarterFrogTurn(w.getp1());
                        }
                        break;
                    } else if (isClicked == 4) {
                        new PoisonDartFrog(homeColumn, i, this, w);
                        isClicked = 0;
                        starterFrogTurnCounter++;
                        for (int j = 0; j < 8; j++) {
                            getW().getBoard().getBoard()[homeColumn][j].setAltColor(null);
                        }
                        w.repaint();
                        if (playerNumber == 1) {
                            w.setWhoseStarterFrogTurn(w.getp2());
                        } else {
                            w.setWhoseStarterFrogTurn(w.getp1());
                        }
                        break;
                    } else if (isClicked == 5) {
                        new PurpleFrog(homeColumn, i, this, w);
                        isClicked = 0;
                        starterFrogTurnCounter++;
                        for (int j = 0; j < 8; j++) {
                            getW().getBoard().getBoard()[homeColumn][j].setAltColor(null);
                        }
                        w.repaint();
                        if (playerNumber == 1) {
                            w.setWhoseStarterFrogTurn(w.getp2());
                        } else {
                            w.setWhoseStarterFrogTurn(w.getp1());
                        }
                        break;
                    } else if (isClicked == 6) {
                        new SharpNosedRocketFrog(homeColumn, i, this, w);
                        isClicked = 0;
                        starterFrogTurnCounter++;
                        for (int j = 0; j < 8; j++) {
                            getW().getBoard().getBoard()[homeColumn][j].setAltColor(null);
                        }
                        w.repaint();
                        if (playerNumber == 1) {
                            w.setWhoseStarterFrogTurn(w.getp2());
                        } else {
                            w.setWhoseStarterFrogTurn(w.getp1());
                        }
                        break;
                    } else if (isClicked == 7) {
                        new SpringPeeper(homeColumn, i, this, w);
                        isClicked = 0;
                        starterFrogTurnCounter++;
                        for (int j = 0; j < 8; j++) {
                            getW().getBoard().getBoard()[homeColumn][j].setAltColor(null);
                        }
                        w.repaint();
                        if (playerNumber == 1) {
                            w.setWhoseStarterFrogTurn(w.getp2());
                        } else {
                            w.setWhoseStarterFrogTurn(w.getp1());
                        }
                        break;
                    }


                    w.repaint();

                }
            }
            System.out.println("Starter Frog Turn Counter: " + starterFrogTurnCounter);
        }


    }

    public void paint(Graphics2D g2d) {
        if (w.getp1().getStarterFrogTurnCounter() < 3 || w.getp2().getStarterFrogTurnCounter() < 3) {
            for (int i = 0; i < 7; i++) {
                g2d.drawRect(50, i * 50 + 150, 150, 50);
                g2d.drawString(w.getListOfChoosableFrogTypes()[i], 50, i * 50 + 175);
            }
        }

    }

    public void turn() throws InterruptedException {
        w.repaint();
        starterFrogTurnCounter++;
        w.repaint();
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
        for (Frog frog : frogsOwned) {
            frog.setHasPerformedAction(false);
            frog.setDisabled(false);

        }
        for (Tadpole tadpole : tadpolesOwned) {
            tadpole.setHasPerformedAction(false);
           tadpole.setDisabled(false);
        }


        ArrayList<Egg> toRemoveEgg = new ArrayList<Egg>();
        for (Egg egg : eggsOwned) {
            egg.increaseTurnsAfterLaid();
            System.out.println("turns after laid " + egg.getTurnsAfterLaid());
        }
        for (Egg egg : eggsOwned) {
            if (egg.getTurnsAfterLaid() >= 1) {
                toRemoveEgg.add(egg);
            }
        }
        for (Egg egg: toRemoveEgg) {
            egg.hatch();
        }


        for (Tadpole tadpole: tadpolesOwned) {
            tadpole.increaseTurnsAfterHatch();
            System.out.println("turns after hatch " + tadpole.getTurnsAfterHatch());
        }
        ArrayList<Tadpole> toRemoveTadpole = new ArrayList<Tadpole>();
        for (Tadpole tadpole: tadpolesOwned) {
            if (tadpole.getTurnsAfterHatch() >= 3) {
                toRemoveTadpole.add(tadpole);
            }
        }
        for (Tadpole tadpole: toRemoveTadpole) {
            tadpole.metamorphose();
        }


        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                tileArr[i][j].setAltColor(null);
                if(tileArr[i][j].getOccupiedBy() != null){
                    if (tileArr[i][j].getOccupiedBy().getAltSprite() != null){
                        tileArr[i][j].getOccupiedBy().setAltSprite(null);
                    }
                }
            }
        }
        for (int i = 0; i < frogsOwned.size(); i++) {
            if (frogsOwned.get(i).getClass() == PoisonDartFrog.class) {
                frogsOwned.get(i).increaseTurnAfterAttack();
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

    public void setStarterFrogTurn(boolean starterFrogTurn) {
        isStarterFrogTurn = starterFrogTurn;
    }



}
