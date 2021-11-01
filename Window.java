import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Window extends JPanel{
    public Window() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.mousePressed(e);
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
    Player p1 = new Player(1, this);
    Player p2 = new Player(2, this);
    Board b = new Board(); // create instance of board <-- todo:stinks!
    EndturnButton button = new EndturnButton();


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


    public void gameLoop() throws InterruptedException { // changed it so gameLoop can paint things now
//        b.paint(g2d);   //repaints the board at the start of each gameloop    //Removing g2d stuff come back later and delete this if everything works <-- todo
        repaint();

        //This fixes the issue where the starter frogs move down when the window is resized, however there could still be a potential issue where units move around when the window is resized <-- todo:look into this issue later
        p1.setStarterFrogTurnCounter(0);
        p2.setStarterFrogTurnCounter(0);

        //These lines cause errors dont know why skull emoji todo <-- that (something to do with resizing wqindow prolly)
        //Not having these lines causes the never mind i figured it out ignore this bit moved down to line 56 57 go look i was doing this at the beginning of the turn when it should have been at the end i get it i get it
//        p1.wipeAll();
//        p2.wipeAll();

        for (int i = 0; i < 3; i++){    //Loops the starter frog choice turn 3 times per player
            p1.starterFrogTurn();
            System.out.println("Player 1 starter frog pick!");  //for testing, TODO: remove this later
            p2.starterFrogTurn();
            System.out.println("Player 2 starter frog pick!");  //Ditto
            System.out.println("i " + i);
        }

//        System.out.println("moving!");
        System.out.println("p1.getW: " + p1.getW());                          //Okay why does this line work
        System.out.println("p1.getFrogsOwned.get(0).getW: " + p1.getFrogsOwned().get(0).getW());   //And this line does not??? <-- todo: someone figure this out please god i have spent so long and i do not understand -1am Ronan
        System.out.println("p1.getFrogsOwned.get(0): " + p1.getFrogsOwned().get(0));
        p1.getFrogsOwned().get(0).move(1,1);                  //Because the previous line doesn't work, this one doesn't either
        repaint();
        System.out.println(p1.getFrogsOwned().get(0).boardX + "," + p1.getFrogsOwned().get(0).boardY);        //p1.getFrogsOwned().get(0).die; //This one does though...      but also why does it not repaint missing the dead frog be honest i can't figure that out


        //while (true) { // changed it so it checks if a player has no units at the end of each turn in the turn method
            p1.turn();
            p2.turn();

        //}

        //fixed that error im so smart the frogs don't multiply anymore
        p1.wipeAll();
        p2.wipeAll();



    }

@Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        b.paint(g2d); // paint board
        button.paint(g2d);


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
