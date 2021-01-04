package q2;

// Philosopher class; all based on -5- philosophers sitting around a table
public class Philosopher extends Thread
{
    // left neighbor
    private int left;
    // right neighbor
    private int right;
    private PhilosophersTable myTable;

    // Constructor.
    public Philosopher(int location, PhilosophersTable table)
    {
        this.myTable = table;
        this.setLocation(location);
    }

    @Override
    public void run()
    {
        while (true)
        {
            this.tryToEat();
            this.eat();
            this.doneEating();
            this.think();
        }
    }

    // Handles the sitting arrangement around the table per philosopher.
    //      for convenience - left neighbor is set to be the location itself, and right is location+1
    //      to create a round table, philosopher num.4 right neighbor is set to '0'.
    private void setLocation(int location)
    {
        if (location == 4)
        {
            this.left = location;
            this.right = 0;
        }
        else
        {
            this.left = location;
            this.right = location+1;

        }
    }

    // wait() is inside 'takeStick' function.
    private void tryToEat()
    {
        while (true)
        {
            if (this.myTable.takeStick(this.left))
                if (this.myTable.takeStick(this.right))
                    break;
                else
                    this.myTable.returnStick(this.left);
        }
    }

    // random time for eating.
    private void eat()
    {
        try {sleep((long)(Math.random() * 10000));}
        catch (InterruptedException e) {}
    }

    // uses eat() to avoid code duplications; as this function job is the same as eat().
    private void think()
    {
        this.eat();
    }

    // releases occupied sticks. notifyAll() is inside returnStick().
    private void doneEating()
    {
        this.myTable.returnStick(this.left);
        this.myTable.returnStick(this.right);
    }
}

