package q2;

import javax.swing.*;

// Philosophers Frame class.
public class PhilosophersFrame extends JFrame
{
    JFrame frame;
    private PhilosophersPanel panel;

    public PhilosophersFrame()
    {
        frame = new JFrame("Philosophers Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        panel = new PhilosophersPanel();
        frame.add(panel);
        frame.setVisible(true);
    }


}
