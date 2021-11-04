import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GoliathFrog extends Frog {

    public GoliathFrog(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);
        isHeavy = true;
        setHitPoints(2);

        try {
            img = ImageIO.read(new File("res\\GoliathFrogSprite.png"));
            if (img != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
        try {
            zappedSprite = ImageIO.read(new File("res\\GoliathFrogLightning.png"));
            if (zappedSprite != null) {
                System.out.println("found image");
            }
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




}
