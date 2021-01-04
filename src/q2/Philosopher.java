package q2;

// Philosopher class; all based on -5- philosophers sitting around a table
public class Philosopher extends Thread
{
    // left neighbor
    private int left;
    // right neighbor
    private int right;
    private PhilosophersTable myTable;

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

    // handles the sitting arrangement around the table per philosopher.
    private void setLocation(int location)
    {
        if (location == 0)
        {
            this.left = 4;
            this.right = 1;
        }
        if (location == 4)
        {
            this.right = 0;
            this.left = 3;
        }
        else
        {
            this.right = location + 1;
            this.left = location - 1;
        }
    }

    private synchronized void tryToEat()
    {
        while (true)
        {
            try
            {
                if (this.myTable.takeStick(this.left))
                    if (this.myTable.takeStick(this.right))
                        break;
                    else
                    {
                        wait();
                        if (this.myTable.takeStick(this.right))
                            break;
                        else
                            this.myTable.returnStick(this.left);
                    }
                wait();
            }
            catch (InterruptedException e) {}
        }
    }

    private void eat()
    {
        try {sleep((long)(Math.random() * 100));}
        catch (InterruptedException e) {}
    }

    private void think()
    {
        this.eat();
    }

    private void doneEating()
    {
        this.myTable.returnStick(this.left);
        this.myTable.returnStick(this.right);
    }
}

