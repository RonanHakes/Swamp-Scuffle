import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class Window extends JPanel{
    private int width;
    private int height;


    public static void main(String[] args) {
        JFrame frame = new JFrame("BattleFrogs");
        Window w = new Window();
        frame.add(w);
        frame.setSize(1920,1080);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,50,50);
    }
}
