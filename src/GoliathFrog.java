import java.awt.*;

public class GoliathFrog extends Frog{

    public GoliathFrog(int boardX, int boardY, Player p){
        super(boardX, boardY, p);
    }

    public void move(){
        //TODO: move
    }

    public void attack(){
        //TODO: Attack
    }



    public void paint(Graphics2D g2d){
        g2d.setColor(Color.GREEN);
        g2d.fillOval(graphicsX, graphicsY, 50, 50);
    }
}
