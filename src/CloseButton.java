import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class CloseButton implements MouseListener {
    private BufferedImage img = null;
    private Window w;

    public CloseButton(Window w) {
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
        System.exit(0);
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }

    public void paint(Graphics2D g2d) {
        try {
            img = ImageIO.read(new File("res\\close.png"));
            g2d.drawImage(img, 1920-50, 0, null);
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
    }

}
