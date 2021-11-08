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
    
    public void paint(Graphics2D g2d) {
        try {
            if ((w.getp1().getStarterFrogTurnCounter() > 3 && w.getp2().getStarterFrogTurnCounter() >= 3)) {
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
        System.out.println("stinky butt");
        Player p = w.getWhoseTurn();
        ArrayList<Frog> fl = p.getFrogsOwned();
        p.setfrogsOwned(fl);
        if (w.getWhoseTurn().getEnergyNum() >= COST) {
            w.getWhoseTurn().setEnergyNum(w.getWhoseTurn().getEnergyNum() - COST);

            new MeanToadEgg(w.getWhoseTurn().getHomeColumn(), 7, w.getWhoseTurn(), w);
        }
        System.out.println("click");


    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
}
