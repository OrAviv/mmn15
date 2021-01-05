package q2;

import javax.swing.*;

// Philosophers Table holds & manages the 5 philosophers around the table.
public class PhilosophersTable
{
    // theres only -5- philosophers around a table.
    public static final int tableSize = 5;
    public static final int NOT_OCCUPIED = -1;
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
        // initial settings for chopsticks array.
        for (int i=0; i<tableSize; i++)
            this.chopsticks[i] = NOT_OCCUPIED;

        for (int i=0; i<tableSize; i++)
        {
            this.philosophers[i] = new Philosopher(i,this, myPanel);
            this.philosophers[i].start();
        }
    }

    protected synchronized boolean takeStick(int stickLocation, int philosopherLocation)
    {
        try
        {
            if (this.chopsticks[stickLocation] == NOT_OCCUPIED)
            {
                this.chopsticks[stickLocation] = philosopherLocation;
                return true;
            }
            wait();
        }
        catch (InterruptedException e) {}
        return false;
    }

    protected synchronized void returnStick(int stickLocation)
    {
        this.chopsticks[stickLocation] = NOT_OCCUPIED;
        notifyAll();
    }

    // checks if philosopher sticks is occupied by him.
    public boolean isPhilosopherEating(int leftStick, int rightStick, int philosopherLocation)
    {
        return (this.chopsticks[leftStick] == philosopherLocation &&
                this.chopsticks[rightStick] == philosopherLocation);
    }
}

