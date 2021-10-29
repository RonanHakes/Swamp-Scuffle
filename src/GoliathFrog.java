import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GoliathFrog extends Frog{
    private BufferedImage img = null;

    public GoliathFrog(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);
        setHitPoints(2);
        try {
            img = ImageIO.read(new File("res\\BaseFrogSprite.png"));
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
    }

    public void move(){
        //TODO: move
    }

    public void attack(){
        //TODO: Attack
    }



    public void paint(Graphics2D g2d){
        g2d.drawImage(img, graphicsX, graphicsY, 50,50, null);
    }
}
