import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.Arrays;

public class Egg extends Unit{
    protected BufferedImage img = null;
    private int turnsAfterLaid = 0;
    private Frog parent;

    public void increaseTurnsAfterLaid() {
        turnsAfterLaid++;
    }
    public int getTurnsAfterLaid() {
        return turnsAfterLaid;
    }
    public Egg(int boardX, int boardY, Player p, Frog parent, Window w){
        super(boardX, boardY, p, w);
        this.parent = parent;
        belongsTo.getEggsOwned().add(this);
        System.out.println("Player " + belongsTo.getPlayerNumber() + " Eggs Owned: " + belongsTo.getEggsOwned().size());
    }

    public void die() {
        belongsTo.getEggsOwned().removeIf(Egg -> (Egg == this));
        super.die();
    }

    public void hatch() {
        this.die();
        Tadpole t = new Tadpole(boardX, boardY, belongsTo, parent, w);
    }


    public void paint(Graphics2D g2d) {
        if (parent instanceof AfricanBullFrog ) {
            try {
                img = ImageIO.read(new File("res\\AfricanBFrogEgg.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        } else if (parent instanceof BluePoisonArrowFrog) {
            try {
                img = ImageIO.read(new File("res\\BluePoisonEgg.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        } else if (parent instanceof GoliathFrog) {
            try {
                img = ImageIO.read(new File("res\\GoliathEgg.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        } else if (parent instanceof PoisonDartFrog) {
            try {
                img = ImageIO.read(new File("res\\PoisonDartEgg.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        } else if (parent instanceof PurpleFrog) {
            try {
                img = ImageIO.read(new File("res\\PurpleEgg.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        } else if (parent instanceof SharpNosedRocketFrog) {
            try {
                img = ImageIO.read(new File("res\\SharpNosedEgg.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        } else if (parent instanceof SpringPeeper) {
            try {
                img = ImageIO.read(new File("res\\SpringPeeperEgg.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
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


}
