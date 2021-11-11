import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpringPeeper extends Frog {

    public SpringPeeper(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);

        if (p.getPlayerNumber() == 1){
            widthMultiplier = 1;
        } else {
            widthMultiplier = -1;
        }

        try {
            img = ImageIO.read(new File("res\\SpringPeeperSprite.png"));
            if (img != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
        turnAfterAttack = 2;
    }

    public void move(){
        //TODO: move
    }


}