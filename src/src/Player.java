public class Player {
    int numberofFrogs = 0;
    int numberOfUnits = 0;
    boolean isTurn;
    int turnNumber = 0;
    int energyNum = 3;
    int playerNumber;

    public Player(int p) {
        playerNumber = p;
    }

    public void starterFrogTurn() {

    }

    public void turn() {
        turnNumber++;
    }



    public void giveMeanToad() {
        //todo
    }
}
