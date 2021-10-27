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
        w.gameLoop();
    }


    public void gameLoop() {


        for (int i = 0; i < 3; i++){    //Loops the starter frog choice turn 3 times per player
            p1.starterFrogTurn();
            System.out.println("Player 1 starter frog pick!");  //for testing, TODO: remove this later
            p2.starterFrogTurn();
            System.out.println("Player 2 starter frog pick!");  //Ditto
        }

        while(p1.getUnitsOwned().length > 0 || p2.getUnitsOwned().length > 0){  //This loop may have to be adjusted later but this works for now
            p1.turn();
            p2.turn();
        }
    }

@Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Board b = new Board(); // create instance of board
        b.paint(g2d); // paint board
        for (int i = 0; i < p1.getUnitsOwned().length; i++){
            p1.getUnitsOwned()[i].paint(g2d);
        }
        for (int i = 0; i < p2.getUnitsOwned().length; i++){
            p2.getUnitsOwned()[i].paint(g2d);
        }
    }

}
