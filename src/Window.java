import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Window extends JPanel{
    public Window() {
<<<<<<< HEAD
        //setZappedSprite();
=======
        setZappedSprite();
>>>>>>> 55e624b3ecb446602accd916017ce05688a1cf6a
        button = new EndturnButton(this);
        meanButton = new MeanToadButton(this);
        try {
            background = ImageIO.read(new File("res\\PlopSpluppleBackground.png"));
            if (background != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
        try {
            title = ImageIO.read(new File("res\\SwampScuffleGameTitle.png"));
            if (title != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }



        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (x >= 1920 - 50 && x <= 1920 && y >= 0 && y <= 22) {
                    close.mousePressed(e);
                }

                if (x >= 50 && x <= 50 + 462 && y >= 1080 - 250 && y <= 1080 - 250 + 198) {
                    meanButton.mousePressed(e);
                }

                if (meanButton.isClicked()) {
                    if (!(x >= 560 && y >= 100 && x <= 1360 && y <= 900) && !(x >= 50 && x <= 50 + 462 && y >= 1080 - 250 && y <= 1080 - 250 + 198)) {
                        meanButton.setNumClicked(0);
                        meanButton.setIsClicked(false);
                        System.out.println("UNHIGHLIGHT");
                        whoseTurn.dehighlightHomeColumn();
                        repaint();
                    }
                    if (p1.getStarterFrogTurnCounter() > 3) {
                        for (int i = 0; i < 8; i++) {
                            if (x >= getWhoseTurn().getHomeColumn() * 100 + 560 && x <= getWhoseTurn().getHomeColumn() * 100 + 560 + 100 && y >= i * 100 + 100 && y <= i * 100 + 200 && getBoard().getBoard()[getWhoseTurn().getHomeColumn()][i].getIsOccupied() == 0) {
                                meanButton.setNumClicked(i);
                                meanButton.setIsClicked(true);
                                if (getWhoseTurn().getEnergyNum() >= 30) {
                                    getWhoseTurn().setEnergyNum(getWhoseTurn().getEnergyNum() - 30);
                                    new MeanToadEgg(getWhoseTurn().getHomeColumn(), i, getWhoseTurn(), whoseTurn.getW());
                                    repaint();
                                    meanButton.setNumClicked(0);
                                    meanButton.setIsClicked(false);
                                    getBoard().getBoard()[whoseTurn.getHomeColumn()][i].setAltColor(null);
                                    repaint();
                                    whoseTurn.tileWipe();
                                }
                            }
                        }
                    }

                }


                if (whoseStarterFrogTurn == p1 && p1.getStarterFrogTurnCounter() < 3){
                    p1.starterFrogTurn(e);
                }
                else if (whoseStarterFrogTurn == p2 && p2.getStarterFrogTurnCounter() < 3) {
                    p2.starterFrogTurn(e);
                }

                if (x >= button.getGraphicsX() && x <= button.getGraphicsX() + button.getImg().getWidth() && y >= button.getGraphicsY() && y <= button.getGraphicsY() + button.getImg().getHeight() && (p1.getStarterFrogTurnCounter() >= 3 && p2.getStarterFrogTurnCounter() >= 3)) {   //Checks if the mousepress is within the end turn button
                    button.mousePressed(e);
                    for (Frog frog : whoseTurn.getFrogsOwned()) {
                        frog.setHasPerformedAction(false);
                        frog.setDisabled(false);

                    }
                    for (Tadpole tadpole : whoseTurn.getTadpolesOwned()) {
                        tadpole.setHasPerformedAction(false);
                        tadpole.setDisabled(false);
                    }
                    try {
                        switchTurn();
                        repaint();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                } else if (x >= 560 && y >= 100 && x <= 1360 && y <= 900 && (p1.getStarterFrogTurnCounter() >= 4 && !meanButton.isClicked())){  //this looks ridiculous, but it is just checking if the click is in the whole board area
                    Tile t = b.clickedOn(e);
                    System.out.println("Tile clicked: " + t.toString());
                    System.out.println("reached it!");

                    if (e.getClickCount() == 2) {
                        if (t.getIsOccupied() == whoseTurn.getPlayerNumber()) {
                            if (t.getOccupiedBy().canLayEgg()) {
                                t.getOccupiedBy().layEgg();
                            }
                        }

                    }

//                    if (t.getOccupiedBy().isClickedByAfricanBullfrog()){
//
//                    }
                    if (t.getIsOccupied() == whoseTurn.getPlayerNumber()){ //Checks if there is an allied unit on the tile being clicked on
                        if (!t.getOccupiedBy().isClicked() && !t.getOccupiedBy().getBelongsTo().isHasClickedUnit()){ //Will run the onClicked method of a unit on this tile, as long as it is not already clicked
                            t.getOccupiedBy().onClicked();
                            System.out.println("reached it!");
                        }
                    }
                    for (int i = 0; i < whoseTurn.getFrogsOwned().size(); i++) {
<<<<<<< HEAD

=======
>>>>>>> 55e624b3ecb446602accd916017ce05688a1cf6a
                        if (whoseTurn.getFrogsOwned().get(i).isClicked()) {
                            if (whoseTurn.getFrogsOwned().get(i).canAttack(t)) {
                                whoseTurn.getFrogsOwned().get(i).attack(t);
                            } else if (whoseTurn.getFrogsOwned().get(i).canUseUtility(t)) {
                                whoseTurn.getFrogsOwned().get(i).useUtility(t);
<<<<<<< HEAD
                            }
                            else if (whoseTurn.getFrogsOwned().get(i).canMoveTo(t)) {
                                whoseTurn.getFrogsOwned().get(i).move(t);
                            } else if (whoseTurn.getFrogsOwned().get(i) instanceof AfricanBullFrog && t.getIsOccupied() != 0) {

                                if (whoseTurn.getFrogsOwned().get(i).isValidOneTileRadius(t) && t.getOccupiedBy().belongsTo == whoseTurn) {
                                    System.out.println("a print statement");
                                    ((AfricanBullFrog) whoseTurn.getFrogsOwned().get(i)).setAlly(t.getOccupiedBy());
                                    ((AfricanBullFrog) whoseTurn.getFrogsOwned().get(i)).onAllyClicked(t.getOccupiedBy());
//                                } else if (whoseTurn.getFrogsOwned().get(i).canAttack(t)) {
//                                    whoseTurn.getFrogsOwned().get(i).attack(t);
//                                } else if (whoseTurn.getFrogsOwned().get(i).canUseUtility(t)) {
//                                    whoseTurn.getFrogsOwned().get(i).useUtility(t);
//                                }
//                                else if (whoseTurn.getFrogsOwned().get(i).canMoveTo(t)) {
//                                    whoseTurn.getFrogsOwned().get(i).move(t);
                                }

                            }
                            else if (t.getIsOccupied() == 0){
                                whoseTurn.getFrogsOwned().get(i).onUnclicked();
                            }
                        }
                        if(whoseTurn.getFrogsOwned().get(i).isClickedByAfricanBullfrog()){
                            whoseTurn.getFrogsOwned().get(i).getBeingMovedBy().liftAlly(whoseTurn.getFrogsOwned().get(i).getOccupiedTile(), t);

                        }
=======
                            } else if (whoseTurn.getFrogsOwned().get(i).canMoveTo(t)) {
                                whoseTurn.getFrogsOwned().get(i).move(t);
                            } else if (t.getIsOccupied() == 0){
                                whoseTurn.getFrogsOwned().get(i).onUnclicked();
                            }
                        }
>>>>>>> 55e624b3ecb446602accd916017ce05688a1cf6a
                    }

                    for (int i = 0; i < whoseTurn.getTadpolesOwned().size(); i++) {
                        if (whoseTurn.getTadpolesOwned().get(i).isClicked()) {
                            if (whoseTurn.getTadpolesOwned().get(i).canMoveTo(t)) {
                                whoseTurn.getTadpolesOwned().get(i).move(t);
                            } else if (t.getIsOccupied() == 0){
                                whoseTurn.getTadpolesOwned().get(i).onUnclicked();
                            }
                        }
                    }
                } else {
                    for (Unit item : whoseTurn.getUnitsOwned()) {
                        item.onUnclicked();
                    }

//                    whoseTurn.getFrogsOwned().forEach(Frog -> Frog.onUnclicked());    //<- this one is cooler

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
    private final Player p1 = new Player(1, this);
    private final Player p2 = new Player(2, this);
    private final Board b = new Board(); // create instance of board <-- todo:stinks!
    private EndturnButton button;
    private Player whoseTurn = p2;
    private boolean isStarterFrogTurn = true;
    private Player whoseStarterFrogTurn = p1;
    private int starterFrogTurnCounter = 0;
    private String[] listOfChoosableFrogTypes = {"African Bullfrog", "Blue Poison Arrow Frog", "Goliath Frog", "Poison Dart Frog", "Purple Frog", "Sharp Nosed Rocket Frog", "Spring Peeper"};
    private BufferedImage zappedSprite;
    private MeanToadButton meanButton;
    private CloseButton close = new CloseButton(this);
    private BufferedImage background;
    private BufferedImage title;
<<<<<<< HEAD
    private TitleScreen ts = new TitleScreen(this);
    private int paintCount = 0;
=======
>>>>>>> 55e624b3ecb446602accd916017ce05688a1cf6a

    public Player getp1() {
        return p1;
    }
    public Player getp2() {
        return p2;
    }
    public void setWhoseStarterFrogTurn(Player p) {
        whoseStarterFrogTurn = p;
    }

    public Player getWhoseStarterFrogTurn() {
        return whoseStarterFrogTurn;
    }

    public MeanToadButton getMeanButton() {
        return this.meanButton;
    }



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


    public void setZappedSprite() {
        try {
            zappedSprite = ImageIO.read(new File("res\\PurpleFrogAttack.png"));
            if (zappedSprite != null) {
                System.out.println("found image!!*");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
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

<<<<<<< HEAD
    public void paintTitleScreen(Graphics2D g2d) throws InterruptedException {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        ts.paint(g2d);
    }

=======
>>>>>>> 55e624b3ecb446602accd916017ce05688a1cf6a
    public void endGame(){
        System.out.println("Game end");
        System.exit(1);
    }


    public void gameLoop() throws InterruptedException { //This is where the whole game runs from
        repaint();

        /*for (int i = 0; i < 3; i++){    //Loops the starter frog choice turn 3 times per player
            if (!p2.getIsStarterFrogTurn()){
                p1.setStarterFrogTurn(true);
            }

            System.out.println("Player 1 starter frog pick!");  //for testing, TODO: remove this later
            if (!p1.getIsStarterFrogTurn()){
                p2.setStarterFrogTurn(true);
            }

            System.out.println("Player 2 starter frog pick!");  //Ditto
            System.out.println("i " + i);
        }



        System.out.println("moving!");
        System.out.println("p1.getW: " + p1.getW());
                              //Okay why does this line work
         */
        //System.out.println("p1.getFrogsOwned.get(0).getW: " + p1.getFrogsOwned().get(0).getW());   //And this line does not??? <-- todo: someone figure this out please god i have spent so long and i do not understand -1am Ronan
        //System.out.println("p2.getFrogsOwned.get(0): " + p2.getFrogsOwned().get(0));
//        p1.getFrogsOwned().get(0).move(b.getBoard()[1][1]);                 //Because the previous line doesn't work, this one doesn't either
//        p2.getFrogsOwned().get(0).moveToTile(b.getBoard()[2][1]);
//        repaint();
//        System.out.println(p1.getFrogsOwned().get(0).boardX + "," + p1.getFrogsOwned().get(0).boardY);        //p1.getFrogsOwned().get(0).die; //This one does though...      but also why does it not repaint missing the dead frog be honest i can't figure that out
//        System.out.println("Player 1 Units: " + Arrays.deepToString(p1.getUnitsOwned().toArray()));
//        System.out.println("Player 1 Units Length" + p1.getUnitsOwned().size());
//        System.out.println("Player 2 Units: " + Arrays.deepToString(p2.getUnitsOwned().toArray()));
//        System.out.println("Player 2 Units Length" + p2.getUnitsOwned().size());
//        System.out.println("Player 1 Frogs: " + Arrays.deepToString(p1.getFrogsOwned().toArray()));
//        System.out.println("Player 1 Frogs Length" + p1.getFrogsOwned().size());
//        System.out.println("Player 2 Frogs: " + Arrays.deepToString(p2.getFrogsOwned().toArray()));
//        System.out.println("Player 2 Frogs Length" + p2.getFrogsOwned().size());
//        new Egg(5,5,p1, p1.getFrogsOwned().get(0),this);
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
<<<<<<< HEAD

        if (paintCount <= 1){
            try {
                paintTitleScreen(g2d);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            paintCount++;
            repaint();
            if (paintCount == 2){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } else {
            g2d.drawImage(background, 0, 0,null);

            g2d.drawImage(title, 1920/2 - 150, 1080 - 175, null);
            b.paint(g2d); // paint board
            button.paint(g2d);
            meanButton.paint(g2d);
            close.paint(g2d);
            Font default1 = g2d.getFont();
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            if (p1.getStarterFrogTurnCounter() < 3 && p2.getStarterFrogTurnCounter() < 3) {
                g2d.drawString("Player " + String.valueOf(whoseStarterFrogTurn.getPlayerNumber()) + " Starter Frog Turn", 1920/2 - 150, 60 );
                System.out.println("Working");
            } else {
                g2d.drawString("Player " + String.valueOf(whoseTurn.getPlayerNumber()) + " Turn", 1920/2 - 75, 60);
            }
            System.out.println(b.avgTile(b.getBoard()[7][1], b.getBoard()[7][3]));
            g2d.setFont(default1);
            p1.getpIS().paint(g2d);
            p2.getpIS().paint(g2d);
            p1.paint(g2d);
            p2.paint(g2d);
        }

=======
        g2d.drawImage(background, 0, 0,null);

        g2d.drawImage(title, 1920/2 - 150, 1080 - 175, null);
        b.paint(g2d); // paint board
        button.paint(g2d);
        meanButton.paint(g2d);
        close.paint(g2d);
        Font default1 = g2d.getFont();
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        if (p1.getStarterFrogTurnCounter() < 3 && p2.getStarterFrogTurnCounter() < 3) {
                g2d.drawString("Player " + String.valueOf(whoseStarterFrogTurn.getPlayerNumber()) + " Starter Frog Turn", 1920/2 - 150, 60 );
                System.out.println("Working");
        } else {
            g2d.drawString("Player " + String.valueOf(whoseTurn.getPlayerNumber()) + " Turn", 1920/2 - 75, 60);
        }
        System.out.println(b.avgTile(b.getBoard()[7][1], b.getBoard()[7][3]));
        g2d.setFont(default1);
        p1.getpIS().paint(g2d);
        p2.getpIS().paint(g2d);
        p1.paint(g2d);
        p2.paint(g2d);
>>>>>>> 55e624b3ecb446602accd916017ce05688a1cf6a


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
