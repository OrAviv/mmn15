package q2;

// Philosophers Table holds & manages the 5 philosophers around the table.
public class PhilosophersTable
{
    // theres only -5- philosophers around a table.
    public static final int tableSize = 5;
    private Philosopher[] philosophers;
    // chopsticks to eat; 'false' - stick is not occupied, 'true' - stick is occupied.
    private boolean[] chopsticks;
    public PhilosophersTable()
    {
        this.philosophers = new Philosopher[tableSize];
        this.chopsticks = new boolean[tableSize-1];
        for (int i=0; i<tableSize; i++)
        {
            this.philosophers[i] = new Philosopher(i, this);
            this.philosophers[i].start();
        }
    }

    protected boolean takeStick(int stickLocation)
    {
        if (!this.chopsticks[stickLocation])
            this.chopsticks[stickLocation] = true;

        return this.chopsticks[stickLocation];
    }

    protected void returnStick(int stickLocation)
    {
        this.chopsticks[stickLocation] = false;
        notifyAll();
    }
}

