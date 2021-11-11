import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MeanToadButton {
    private Window w;
    protected BufferedImage img = null;
    public MeanToadButton(Window w) {
        this.w = w;
    }
    private final int COST = 30;
    private int numClicked = 0;
    private boolean isClicked = false;

    public boolean isClicked() {
        return isClicked;
    }
    public void setIsClicked(Boolean b){
         isClicked = b;
    }

    public int getNumClicked() {
        return numClicked;
    }
    public void setNumClicked(int n) {
        numClicked = n;
    }

    
    
    public void paint(Graphics2D g2d) {
        try {
            if ((w.getp1().getStarterFrogTurnCounter() > 3)) {
                img = ImageIO.read(new File("res\\MeanToadButton.png"));
                g2d.drawImage(img, 50, 1080 - 250, null);
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mousePressed(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();

        isClicked = true;

        for (int i = 0; i < 8; i++) {
            if (w.getWhoseTurn().getEnergyNum() >= 30 && w.getp1().getStarterFrogTurnCounter() > 3) {
                if (w.getBoard().getBoard()[w.getWhoseTurn().getHomeColumn()][i].getIsOccupied() == 0) {
                    System.out.println("MEAN TOAD YELLOW");
                    w.getBoard().getBoard()[w.getWhoseTurn().getHomeColumn()][i].setAltColor(Color.YELLOW);
                    w.repaint();
                }
            }
        }

    }

    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
}
