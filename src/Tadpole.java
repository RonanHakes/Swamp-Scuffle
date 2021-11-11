import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Tadpole extends Unit{
    private Frog parent;
    private int turnsAfterHatch = 0;
    private boolean isDisabled;
    private BufferedImage img;

    public Tadpole(int boardX, int boardY, Player p, Frog parent, Window w){
        super(boardX, boardY, p, w);
        belongsTo.getTadpolesOwned().add(this);
        System.out.println("Player " + belongsTo.getPlayerNumber() + " Tadpoles Owned: " + belongsTo.getTadpolesOwned().size());
        this.parent = parent;
    }

    public void die() {
        belongsTo.getTadpolesOwned().removeIf(tadpole -> (tadpole == this));
        super.die();

    }
    public void increaseTurnsAfterHatch(){
        turnsAfterHatch++;
    }

    public int getTurnsAfterHatch(){
        return turnsAfterHatch;
    }


    public void metamorphose() {
        onUnclicked();
        this.die();

        System.out.println("Metamorphose!!!!!!");
        //TODO: create metamorphose method
        if (parent instanceof AfricanBullFrog ) {
            AfricanBullFrog f = new AfricanBullFrog(boardX,boardY,belongsTo,w);
            f.isDisabled = true;
        } else if (parent instanceof BluePoisonArrowFrog) {
            BluePoisonArrowFrog f = new BluePoisonArrowFrog(boardX,boardY,belongsTo,w);
            f.isDisabled = true;
        } else if (parent instanceof GoliathFrog) {
            GoliathFrog f = new GoliathFrog(boardX,boardY,belongsTo,w);
            f.isDisabled = true;
        } else if (parent instanceof MeanToad) {
            MeanToad f = new MeanToad(boardX, boardY,belongsTo,w);
            f.isDisabled = true;
        } else if (parent instanceof PoisonDartFrog) {
            PoisonDartFrog f = new PoisonDartFrog(boardX,boardY,belongsTo,w);
            f.isDisabled = true;
        } else if (parent instanceof PurpleFrog) {
            PurpleFrog f = new PurpleFrog(boardX,boardY,belongsTo,w);
            f.isDisabled = true;
        } else if (parent instanceof SharpNosedRocketFrog) {
            SharpNosedRocketFrog f = new SharpNosedRocketFrog(boardX,boardY,belongsTo,w);
            f.isDisabled = true;
        } else {
            SpringPeeper f = new SpringPeeper(boardX,boardY,belongsTo,w);
            f.isDisabled = true;
        }
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
        return (super.canMoveTo(t) || (t.getIsOccupied() == 0 && !isDisabled && belongsTo.getEnergyNum() >= 1 && !hasPerformedAction && isValidTwoTileRadius(t)));
    }


    @Override
    public void onClicked() {
        Tile[][] tileArr = getW().getBoard().getBoard();
        Tile current;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                current = tileArr[i][j];
                if (current.getBoardX() == boardX && current.getBoardY() == boardY) {
                    if (isDisabled) {
                        current.setAltColor(Color.GRAY);
                    } else {
                        current.setAltColor(Color.GREEN);
                        System.out.println("YOYOYOYO");
                    }
                } else if (canMoveTo(current)){
                    current.setAltColor(Color.YELLOW);
                    current.setCanMoveTo(true);
                }
            }
        }
        isClicked = true;
        belongsTo.setHasClickedUnit(true);
        w.repaint();
    }
}
