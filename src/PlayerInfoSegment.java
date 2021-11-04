import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlayerInfoSegment {
    private Player player;

    public PlayerInfoSegment(Player p) {
        player = p;
    }

    public void paint(Graphics2D g2d) {
        if (player.getPlayerNumber() == 1) {
            g2d.drawString("Turn Number: " + String.valueOf(player.getTurnNumber()), 50, 50);
            g2d.drawString("Energy: " + String.valueOf(player.getEnergyNum()), 50, 75);
            g2d.drawString("Energy per turn: " + String.valueOf(player.getEnergyPerTurn()), 50, 100);

        } else {
            g2d.drawString("Turn Number: " + String.valueOf(player.getTurnNumber()), 1920 - 100, 50);
            g2d.drawString("Energy: " + String.valueOf(player.getEnergyNum()), 1920 - 150, 75);
            g2d.drawString("Energy per turn: " + String.valueOf(player.getEnergyPerTurn()), 1920-150, 100);
        }

    }

}
