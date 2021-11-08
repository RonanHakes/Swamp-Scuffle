import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Tadpole extends Unit{
    private Frog parent;
    private int turnsAfterHatch;
    private boolean isDisabled;
    private BufferedImage img;

    public Tadpole(int boardX, int boardY, Player p, Frog parent, Window w){
        super(boardX, boardY, p, w);
        this.parent = parent;
    }
//    TODO:come back to this
//    public <Frog> void metamorphize(){
//        Frog ft = new Frog();
//    }

    public void move() {
        //TODO: create move method
    }

    public void metamorphose() {
        //TODO: create metamorphose method
    }

    public void paint(Graphics2D g2d) {
        if (parent instanceof AfricanBullFrog ) {
            try {
                img = ImageIO.read(new File("res\\TPAfricanBullfrog.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        } else if (parent instanceof BluePoisonArrowFrog) {
            try {
                img = ImageIO.read(new File("res\\TPBluePoison.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        } else if (parent instanceof GoliathFrog) {
            try {
                img = ImageIO.read(new File("res\\TPGoliathFrog.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        } else if (parent instanceof MeanToad) {
            try {
                img = ImageIO.read(new File("res\\TPMeanToad.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        } else if (parent instanceof PoisonDartFrog) {
            try {
                img = ImageIO.read(new File("res\\TPPoisonDart.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        } else if (parent instanceof PurpleFrog) {
            try {
                img = ImageIO.read(new File("res\\TPPurple.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        } else if (parent instanceof SharpNosedRocketFrog) {
            try {
                img = ImageIO.read(new File("res\\TPSharpNosed.png"));
                if (img != null) {
                    System.out.println("found image");
                }
            } catch (IOException e) {
                System.out.println("Can't find image.");
            }
        } else if (parent instanceof SpringPeeper) {
            try {
                img = ImageIO.read(new File("res\\TPSpringPeeper.png"));
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

    @Override
    public boolean canMoveTo(Tile t) {
        return false;
    }

    @Override
    public void onUnclicked() {

    }

    @Override
    public void onClicked() {

    }
}
