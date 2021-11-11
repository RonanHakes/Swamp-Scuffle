import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class EndturnButton implements MouseListener {
    private boolean isClicked = false;
    private int numCLicked = 0;
    private BufferedImage img = null;
    private int graphicsX = 1920-200;
    private int graphicsY = 1080-300;
    private Window w;

    public EndturnButton(Window w) {
        this.w = w;

        try {
            img = ImageIO.read(new File("res\\NoBGEndButton.png"));
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
    }

    public BufferedImage getImg(){
        return img;
    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }
    public void mousePressed(MouseEvent e) {
        System.out.println("stinky butt");
        w.getWhoseTurn().getFrogsOwned().forEach(Unit::onUnclicked);    //makes all units unclicked
        isClicked = true;
        w.repaint();
        System.out.println("click");


    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }


    public void paint(Graphics2D g2d) {
        try {
            if ((w.getp1().getStarterFrogTurnCounter() > 3 || w.getp1().getTurnNumber() >= 1)) {
                img = ImageIO.read(new File("res\\EndTurnButton.png"));
                g2d.drawImage(img, graphicsX, graphicsY, null);
            } else {
                img = ImageIO.read(new File("res\\StartTurnButton.png"));
                g2d.drawImage(img, graphicsX, graphicsY, null);
            }

        } catch (IOException e) {
            System.out.println("Can't find image.");
        }

    }

    public boolean getIsClicked(){
        return isClicked;
    }

    public void setIsClicked(boolean b){
        isClicked = b;
    }

    public int getGraphicsY() {
        return graphicsY;
    }

    public void setGraphicsY(int graphicsY) {
        this.graphicsY = graphicsY;
    }

    public int getGraphicsX() {
        return graphicsX;
    }

    public void setGraphicsX(int graphicsX) {
        this.graphicsX = graphicsX;
    }
}

