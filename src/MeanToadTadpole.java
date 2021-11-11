import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class MeanToadTadpole extends Tadpole{
    private int turnsAfterHatch = 0;
    private boolean isDisabled;
    private BufferedImage img;

    public MeanToadTadpole(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, null, w);
    }

    public void metamorphose() {
        die();
        onUnclicked();
        MeanToad f = new MeanToad(boardX, boardY, belongsTo, w);
        f.isDisabled = true;
    }

    public void paint(Graphics2D g2d) {
        try {
            img = ImageIO.read(new File("res\\TPMeanToad.png"));
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
