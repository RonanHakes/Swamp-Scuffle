import javax.swing.*;
import java.awt.*;

public class Window extends JPanel{
    public Window w;
    private int width;
    private int height;
    Player p1 = new Player(1, this);
    Player p2 = new Player(2, this);
    Board b = new Board(); // create instance of board
    EndturnButton button = new EndturnButton();


    public static void main(String[] args) {
        JFrame frame = new JFrame("BattleFrogs");
        Window w = new Window();
        frame.add(w);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void gameLoop(Graphics2D g2d) { // changed it so gameLoop can paint things now
        b.paint(g2d);   //repaints the board at the start of each gameloop

        //This fixes the issue where the starter frogs move down when the window is resized, however there could still be a potential issue where units move around when the window is resized <-- todo:look into this issue later
        p1.setStarterFrogTurnCounter(0);
        p2.setStarterFrogTurnCounter(0);

        for (int i = 0; i < 3; i++){    //Loops the starter frog choice turn 3 times per player
            p1.starterFrogTurn(g2d);
            System.out.println("Player 1 starter frog pick!");  //for testing, TODO: remove this later
            p2.starterFrogTurn(g2d);
            System.out.println("Player 2 starter frog pick!");  //Ditto
            System.out.println("i " + i);
        }

//        System.out.println("moving!");
        System.out.println(p1.getW());                          //Okay why does this line work
        System.out.println(p1.getFrogsOwned().get(0).getW());   //And this line does not??? <-- todo: someone figure this out please god i have spent so long and i do not understand -1am Ronan
//        p1.getFrogsOwned().get(0).move(1,1);                  //Because the previous line doesn't work, this one doesn't either
        p1.getFrogsOwned().get(0).die();                        //This one does though...      but also why does it not repaint missing the dead frog be honest i can't figure that out


//        while (true) { // changed it so it checks if a player has no units at the end of each turn in the turn method
//            p1.turn();
//            p2.turn();
//        }


    }

@Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        b.paint(g2d); // paint board
        button.paint(g2d);
        gameLoop(g2d);

    }

    public Board getBoard(){
        return b;
    }

    public EndturnButton getButton(){
        return button;
    }

}
