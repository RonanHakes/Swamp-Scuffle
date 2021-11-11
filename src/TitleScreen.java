import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TitleScreen {

    private BufferedImage gameTitle;
    private BufferedImage GAGLogo;
    private BufferedImage background;
    private Window w;

    public TitleScreen(Window w){
        this.w = w;
        try {
            gameTitle = ImageIO.read(new File("res\\SwampScuffleGameTitleBig.png"));
            if (gameTitle != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
        try {
            GAGLogo = ImageIO.read(new File("res\\GoldenAgeGamesLogo.png"));
            if (GAGLogo != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
        try {
            background = ImageIO.read(new File("res\\MAINTITLESCREEN.png"));
            if (background != null) {
                System.out.println("found image");
            }
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
    }

    public void paint(Graphics g2d) throws InterruptedException {
        g2d.drawImage(background,0, 0,null);
        g2d.drawImage(gameTitle,(1920 / 2) - gameTitle.getWidth() / 2, 350,null);
        g2d.drawImage(GAGLogo,(1920 / 2) - GAGLogo.getWidth() / 2,625,null);
//        Thread.sleep(5000);
    }
}
