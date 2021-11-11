import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.Arrays;

public class MeanToadEgg extends Egg{
    protected BufferedImage img = null;
    private int turnsAfterLaid;

    public MeanToadEgg(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, null, w);
    }

//    public void hatch(){  TODO
//        Tadpole t = new Tadpole(boardX, boardY, belongsTo, frogType,w){
//
//        }
//        die();
//
//    }


    public void paint(Graphics2D g2d) {
        try {
            img = ImageIO.read(new File("res\\MeanToadEgg.png"));
            if (img != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }

        if(img != null){
            if (widthMultiplier == -1){     //There's gotta be a better way of doing this (changing the x co-ordinate based on which way it should be facing)
                g2d.drawImage(img, graphicsX + 50, graphicsY, 50 * widthMultiplier,50, null);
            } else {
                g2d.drawImage(img, graphicsX , graphicsY, 50 * widthMultiplier,50, null);
            }
        }

    }

    public void move() {
        int fourty = 40;
        //this doesn't do anything. Eggs can't move dummy
    }

    @Override
    public boolean canMoveTo(Tile t) {
        return false;
    }

    @Override
    public void onClicked() {

    }

    @Override
    public void onUnclicked() {

    }


    public void hatch() {
        die();
        MeanToadTadpole t = new MeanToadTadpole(boardX, boardY, belongsTo, w);
        System.out.println("Hatched!!!!!");
    }


}
