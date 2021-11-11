import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BluePoisonArrowFrog extends Frog {

    public BluePoisonArrowFrog(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);

        if (p.getPlayerNumber() == 1){
            widthMultiplier = 1;
        } else {
            widthMultiplier = -1;
        }

        buffSurroundingTiles();

        try {
            img = ImageIO.read(new File("res\\BluePoisonArrowSprite.png"));
            if (img != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
        try {
            zappedSprite = ImageIO.read(new File("res\\BluePoisonArrowLightning.png"));
            if (zappedSprite != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
    }

    @Override
    public void moveToTile(Tile t){
        super.moveToTile(t);
        buffSurroundingTiles();
    }

    @Override
    public void setDisabled(boolean b){
        super.setDisabled(b);
        if (b){
            unbuffSurroundingTiles();
        } else {
            buffSurroundingTiles();
        }

    }

    public void unbuffSurroundingTiles(){
        //This is terribly inefficient, but it should work for now
        //This method just buffs all the tiles in a one tile radius around the blue poison arrow frog
        Tile[][] tileArr = w.getBoard().getBoard();
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (isValidTwoTileRadius(tileArr[i][j])){
                    tileArr[i][j].setBuffed(false);
                }
            }
        }
    }

    @Override
    public boolean canAttack(Tile t){
        return false;
    }

    private void buffSurroundingTiles(){

        //This is terribly inefficient, but it should work for now
        //This method just buffs all the tiles in a one tile radius around the blue poison arrow frog
        Tile[][] tileArr = w.getBoard().getBoard();
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (isValidTwoTileRadius(tileArr[i][j])){
                    tileArr[i][j].setBuffed(true);
                }
            }
        }

    }


}