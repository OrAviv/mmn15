package q2;
import javax.swing.*;
import java.awt.*;

// Panel for Philosophers
public class PhilosophersPanel extends JPanel
{
    JPanel panel;
    PhilosophersTable table;

    public PhilosophersPanel()
    {
        panel = new JPanel();
        this.table = new PhilosophersTable(this);
    }

    // repaint() gets called from Philosopher class after each change of every philosopher.
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (table.isPhilosopherEating(0, 1,0))
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.LIGHT_GRAY);
        g.fillOval(230, 50, 40, 40);

        if (table.isPhilosopherEating(1, 2, 1))
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.LIGHT_GRAY);
        g.fillOval(350, 150,40, 40);

        if (table.isPhilosopherEating(2, 3, 2))
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.LIGHT_GRAY);
        g.fillOval(300, 270,40, 40);

        if (table.isPhilosopherEating(3, 4,3))
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.LIGHT_GRAY);
        g.fillOval(160, 270,40, 40);

        if (table.isPhilosopherEating(4, 0, 4))
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.LIGHT_GRAY);
        g.fillOval(110, 150,40, 40);
    }

}
