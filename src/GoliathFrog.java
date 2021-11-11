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
        maxHitPoints = hitPoints;

        try {
            img = ImageIO.read(new File("res\\GoliathFrogSprite.png"));
            if (img != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }

    }

    @Override
    public void paint(Graphics2D g2d){
        if (hitPoints == 1){
            try {
                img = ImageIO.read(new File("res\\LowHPGoliathFrogSprite.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        }
        super.paint(g2d);
    }






}
