import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlayerInfoSegment {
    private Player player;
    private Window w;

    public PlayerInfoSegment(Player p, Window w) {
        player = p;
        this.w = w;
    }

    public void paint(Graphics2D g2d) {
        if (w.getp1().getStarterFrogTurnCounter() > 3) {
            if (player.getPlayerNumber() == 1) {
                g2d.setColor(Color.BLUE);
                g2d.setFont(new Font("TimesRoman", Font.PLAIN, 18));
                g2d.drawString("Turn Number: " + String.valueOf(player.getTurnNumber()), 50, 50);
                g2d.drawString("Energy: " + String.valueOf(player.getEnergyNum()), 50, 75);
                g2d.drawString("Energy per turn: " + String.valueOf(player.getEnergyPerTurn()), 50, 100);
                System.out.println("PLayer 1 Energy per turn: " + String.valueOf(player.getEnergyPerTurn()));

            } else {
                g2d.setColor(Color.RED);
                g2d.setFont(new Font("TimesRoman", Font.PLAIN, 18));
                g2d.drawString("Turn Number: " + String.valueOf(player.getTurnNumber()), 1920 - 200, 50);
                g2d.drawString("Energy: " + String.valueOf(player.getEnergyNum()), 1920 - 200, 75);
                g2d.drawString("Energy per turn: " + String.valueOf(player.getEnergyPerTurn()), 1920-200, 100);
                System.out.println("Player 2 Energy per turn: " + String.valueOf(player.getEnergyPerTurn()));
            }

        }

    }

}
