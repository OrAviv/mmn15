package q2;

// Philosophers Table holds & manages the 5 philosophers around the table.
public class PhilosophersTable
{
    // theres only -5- philosophers around a table.
    public static final int tableSize = 5;
    private Philosopher[] philosophers;
    // chopsticks to eat; 'false' - stick is not occupied, 'true' - stick is occupied.
    private boolean[] chopsticks;

    // Constructor.
    public PhilosophersTable()
    {
        this.philosophers = new Philosopher[tableSize];
        this.chopsticks = new boolean[tableSize];
        for (int i=0; i<tableSize; i++)
        {
            this.philosophers[i] = new Philosopher(i, this);
            this.philosophers[i].start();
        }
    }

    protected synchronized boolean takeStick(int stickLocation)
    {
        try
        {
            if (!this.chopsticks[stickLocation])
            {
                this.chopsticks[stickLocation] = true;
                return true;
            }
            wait();
        }
        catch (InterruptedException e) {}
        return false;
    }

    protected synchronized void returnStick(int stickLocation)
    {
        this.chopsticks[stickLocation] = false;
        notifyAll();
    }
}

