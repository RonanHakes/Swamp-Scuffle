import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class PurpleFrog extends Frog {

    public PurpleFrog(int boardX, int boardY, Player p, Window w){
        super(boardX, boardY, p, w);


        if (p.getPlayerNumber() == 1){
            widthMultiplier = 1;
        } else {
            widthMultiplier = -1;
        }

        try {
            img = ImageIO.read(new File("res\\PurpleFrogSprite.png"));
            if (img != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }

        try {
            zappedSprite = ImageIO.read(new File("res\\PurpleFrogLightning.png"));
            if (zappedSprite != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
    }

    @Override
    public void attack(Tile attackedTile){
        Tile[][] tileArr = getW().getBoard().getBoard();
        int xDiff = attackedTile.getBoardX() - occupiedTile.getBoardX();
        int yDiff = attackedTile.getBoardY() - occupiedTile.getBoardY();
        Tile toCheck = tileArr[attackedTile.getBoardX() + xDiff][attackedTile.getBoardY() + yDiff];     //gets the tile behind the attacking tile so that it can be checked

        //this is for the attack animation TODO
//        attackedTile.getOccupiedBy().setAltSprite(zappedSprite);
//        w.repaint();
//        try{
//            Thread.sleep(200);
//            System.out.println("weweweweWA!!");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        attackedTile.getOccupiedBy().setAltSprite(null);
//        w.repaint();

        super.attack(attackedTile); //Attacks the attacked tile
        //altSprite = zappedSprite;

        if (attackedTile.getOccupiedBy().getHitPoints() <= 0){
            if(isBuffed){
                rewardKill(attackedTile.getOccupiedBy());
            }
            attackedTile.getOccupiedBy().die();
            moveToTile(attackedTile);
        }
        if (toCheck.getIsOccupied() != 0 && toCheck.getIsOccupied() != belongsTo.getPlayerNumber()){
            //belongsTo.giveEnergy(2);   //This sucks
//            toCheck.getOccupiedBy().setAltSprite(zappedSprite);
//            w.repaint();
//            try{
//                Thread.sleep(20);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            toCheck.getOccupiedBy().setAltSprite(null);
//            w.repaint();
            toCheck.getOccupiedBy().takeDamage(1);
            if (toCheck.getOccupiedBy().getHitPoints() <= 0) {
                toCheck.getOccupiedBy().die();
                if (belongsTo.getPlayerNumber() == 1) {
                    if (w.getp2().getUnitsOwned().size() == 0) {
                        System.out.println(Arrays.deepToString(belongsTo.getUnitsOwned().toArray()));
                        w.endGame();
                        System.out.println("WRONG WRONG WRONG");
                    }
                } else {
                    if (w.getp1().getUnitsOwned().size() == 0) {
                        System.out.println(Arrays.deepToString(belongsTo.getUnitsOwned().toArray()));
                        w.endGame();
                        System.out.println("WRONG WRONG WRONG");
                    }
                }
            }
            //super.attackNoCheck(toCheck);
            belongsTo.tileWipe();
        }
    }
}