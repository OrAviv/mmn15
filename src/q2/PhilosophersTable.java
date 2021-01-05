package q2;

import javax.swing.*;

// Philosophers Table holds & manages the 5 philosophers around the table.
public class PhilosophersTable
{
    // theres only -5- philosophers around a table.
    public static final int tableSize = 5;
    private Philosopher[] philosophers;
    // chopsticks to eat; '-1' - stick is not occupied, 'location'(=philosopher number) - stick is occupied.
    private int[] chopsticks;
    private JPanel myPanel;
    private PhilosophersFrame myFrame;

    // Constructor.
    public PhilosophersTable(JPanel panel)
    {
        this.myPanel = panel;
        this.philosophers = new Philosopher[tableSize];
        this.chopsticks = new int[tableSize];
        // re-setting chopsticks array.
        for (int i=0; i<tableSize; i++)
            this.chopsticks[i] = -1;

        for (int i=0; i<tableSize; i++)
        {
            this.philosophers[i] = new Philosopher(i,this, myPanel);
            this.philosophers[i].start();
        }
    }

    protected synchronized boolean takeStick(int stickLocation, int philosopherNumber)
    {
        try
        {
            if (this.chopsticks[stickLocation] == -1)
            {
                this.chopsticks[stickLocation] = philosopherNumber;
                return true;
            }
            wait();
        }
        catch (InterruptedException e) {}
        return false;
    }

    protected synchronized void returnStick(int stickLocation)
    {
        this.chopsticks[stickLocation] = -1;
        notifyAll();
    }

    public boolean isPhilosopherEating(int leftNeighbor, int rightNeighbor, int philosopherNumber)
    {
        return (this.chopsticks[leftNeighbor] == philosopherNumber && this.chopsticks[rightNeighbor] == philosopherNumber);
    }
}

