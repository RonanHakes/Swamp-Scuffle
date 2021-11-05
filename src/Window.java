import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Window extends JPanel{
    public Window() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();


                if (x >= button.getGraphicsX() && x <= button.getGraphicsX() + button.getImg().getWidth() && y >= button.getGraphicsY() && y <= button.getGraphicsY() + button.getImg().getHeight()){   //Checks if the mousepress is within the end turn button
                    button.mousePressed(e);
                    try {
                        switchTurn();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                } else if (x >= 560 && y >= 100 && x <= 1360 && y <= 900){  //this looks ridiculous, but it is just checking if the click is in the whole board area
                    Tile t = b.clickedOn(e);
                    System.out.println("Tile clicked: " + t.toString());
                    if (t.getIsOccupied() == whoseTurn.getPlayerNumber()){ //Checks if there is an allied unit on the tile being clicked on
                        if (!t.getOccupiedBy().isClicked() && !t.getOccupiedBy().getBelongsTo().isHasClickedUnit()){ //Will run the onClicked method of a unit on this tile, as long as it is not already clicked
                            t.getOccupiedBy().onClicked();
                            System.out.println("reached it!");
                        } else {
                            t.getOccupiedBy().onUnclicked();
                        }
                    }
                    for (int i = 0; i < whoseTurn.getFrogsOwned().size(); i++) {
                        if (whoseTurn.getFrogsOwned().get(i).isClicked()) {
                            if (whoseTurn.getFrogsOwned().get(i).canAttack(t)) {
                                whoseTurn.getFrogsOwned().get(i).attack(t);
                            } else if (whoseTurn.getFrogsOwned().get(i).canUseUtility(t)) {
                                whoseTurn.getFrogsOwned().get(i).useUtility(t);
                            } else if (whoseTurn.getFrogsOwned().get(i).canMoveTo(t)) {
                                whoseTurn.getFrogsOwned().get(i).move(t);
                            }
                        }
                    }
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        setFocusable(true);
    }
    private int width;
    private int height;
    private boolean isTurn;
    private Player p1 = new Player(1, this);
    private Player p2 = new Player(2, this);
    private Board b = new Board(); // create instance of board <-- todo:stinks!
    private EndturnButton button = new EndturnButton();
    private Player whoseTurn = p1;
    private boolean isStarterFrogTurn = true;
    private String listOfChoosableFrogTypes[] = {"African Bullfrog", "Blue Poison Arrow Frog", "Goliath Frog", "Poison Dart Frog", "Purple Frog", "Sharp Nosed Rocket Frog", "Spring Peeper"};



    public String[] getListOfChoosableFrogTypes() {
        return listOfChoosableFrogTypes;
    }

    public void switchTurn() throws InterruptedException {
        isStarterFrogTurn = false;
        System.out.println("turn end");
        if (whoseTurn == p1) {
            whoseTurn = p2;
        } else if (whoseTurn == p2){
            whoseTurn = p1;
        } else {
            System.out.println("what");
        }
        whoseTurn.turn();
        repaint();
    }

    public Player getWhoseTurn(){
        return whoseTurn;
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("BattleFrogs");
        Window w = new Window();
        frame.add(w);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            w.gameLoop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void endGame(){
        System.out.println("Game end");
        System.exit(0);
    }


    public void gameLoop() throws InterruptedException { //This is where the whole game runs from

        repaint();


        //This fixes the issue where the starter frogs move down when the window is resized, however there could still be a potential issue where units move around when the window is resized <-- todo:look into this issue later
        p1.setStarterFrogTurnCounter(0);
        p2.setStarterFrogTurnCounter(0);


        for (int i = 0; i < 3; i++){    //Loops the starter frog choice turn 3 times per player
            p1.starterFrogTurn();
            System.out.println("Player 1 starter frog pick!");  //for testing, TODO: remove this later
            p2.starterFrogTurn();
            System.out.println("Player 2 starter frog pick!");  //Ditto
            System.out.println("i " + i);
        }

        System.out.println("moving!");
        System.out.println("p1.getW: " + p1.getW());                          //Okay why does this line work
        System.out.println("p1.getFrogsOwned.get(0).getW: " + p1.getFrogsOwned().get(0).getW());   //And this line does not??? <-- todo: someone figure this out please god i have spent so long and i do not understand -1am Ronan
        System.out.println("p2.getFrogsOwned.get(0): " + p2.getFrogsOwned().get(0));
//        p1.getFrogsOwned().get(0).move(b.getBoard()[1][1]);                 //Because the previous line doesn't work, this one doesn't either
//        p2.getFrogsOwned().get(0).moveToTile(b.getBoard()[2][1]);
        repaint();
        System.out.println(p1.getFrogsOwned().get(0).boardX + "," + p1.getFrogsOwned().get(0).boardY);        //p1.getFrogsOwned().get(0).die; //This one does though...      but also why does it not repaint missing the dead frog be honest i can't figure that out
        System.out.println("Player 1 Units: " + Arrays.deepToString(p1.getUnitsOwned().toArray()));
        System.out.println("Player 1 Units Length" + p1.getUnitsOwned().size());
        System.out.println("Player 2 Units: " + Arrays.deepToString(p2.getUnitsOwned().toArray()));
        System.out.println("Player 2 Units Length" + p2.getUnitsOwned().size());
        System.out.println("Player 1 Frogs: " + Arrays.deepToString(p1.getFrogsOwned().toArray()));
        System.out.println("Player 1 Frogs Length" + p1.getFrogsOwned().size());
        System.out.println("Player 2 Frogs: " + Arrays.deepToString(p2.getFrogsOwned().toArray()));
        System.out.println("Player 2 Frogs Length" + p2.getFrogsOwned().size());
        new Egg(5,5,p1, p1.getFrogsOwned().get(0),this);
        //while (true) { // changed it so it checks if a player has no units at the end of each turn in the turn method
//        p1.turn();

        //}

        //fixed that error im so smart the frogs don't multiply anymore
//        p1.wipeAll();
//        p2.wipeAll();



    }

@Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        b.paint(g2d); // paint board
        button.paint(g2d);
        g2d.drawString(String.valueOf(whoseTurn.getPlayerNumber()), 1920/2, 50);
        p1.getpIS().paint(g2d);
        p2.getpIS().paint(g2d);
        p1.paint(g2d);
        p2.paint(g2d);



    //make this show end of game menu, also, add that menu <-- todo
//        System.exit(0);
    }

    public Board getBoard(){
        return b;
    }

    public EndturnButton getButton(){
        return button;
    }


}
