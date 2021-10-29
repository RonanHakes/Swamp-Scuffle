import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.event.MouseEvent;

public class EndturnButton implements MouseListener {
    private boolean isClicked = false;
    private BufferedImage img = null;

    public EndturnButton() {
        try {
            img = ImageIO.read(new File("res\\NoBGEndButton.png"));
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        isClicked = true;
        System.out.println("Click!");
    }
    public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }


    public void paint(Graphics2D g2d) {
        g2d.drawImage(img, 1920-200, 1080-300, null);
    }

    public boolean getIsClicked(){
        return isClicked;
    }

}

