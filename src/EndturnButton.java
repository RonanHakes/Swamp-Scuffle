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
        Player p = w.getWhoseTurn();
        ArrayList<Frog> fl = p.getFrogsOwned();
        p.setfrogsOwned(fl);
        isClicked = true;
        System.out.println("click");


    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }


    public void paint(Graphics2D g2d) {
        g2d.drawImage(img, graphicsX, graphicsY, null);
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

