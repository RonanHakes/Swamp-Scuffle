import javax.swing.*;
import java.awt.*;

public class Window extends JPanel{
    private int width;
    private int height;
    Player p1 = new Player(1);
    Player p2 = new Player(2);


    public static void main(String[] args) {
        JFrame frame = new JFrame("BattleFrogs");
        Window w = new Window();
        frame.add(w);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void gameLoop(Graphics2D g2d) throws InterruptedException { // changed it so gameLoop can paint things now


        for (int i = 0; i < 3; i++){    //Loops the starter frog choice turn 3 times per player
            p1.starterFrogTurn(g2d);
            System.out.println("Player 1 starter frog pick!");  //for testing, TODO: remove this later
            p2.starterFrogTurn(g2d);
            System.out.println("Player 2 starter frog pick!");  //Ditto
        }


        /* while (true) { // changed it so it checks if a player has no units at the end of each turn in the turn method
            p1.turn();
            p2.turn();
            Thread.sleep(10);
        }

        */

    }

@Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Board b = new Board(); // create instance of board
        b.paint(g2d); // paint board
    try {
        gameLoop(g2d); // paints gameLoop
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

}
