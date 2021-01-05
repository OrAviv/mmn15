package q2;
import javax.swing.*;

// Philosopher class; all based on -5- philosophers sitting around a table
public class Philosopher extends Thread
{
    // left neighbor
    private int left;
    // right neighbor
    private int right;
    // Philosopher location around the table.
    private int location;
    private PhilosophersTable myTable;
    private JPanel myPanel;

    // Constructor.
    public Philosopher(int location, PhilosophersTable table, JPanel panel)
    {
        this.location = location;
        this.myTable = table;
        this.setLocation(location);
        this.myPanel = panel;
    }

    // Printing for personal use.
    @Override
    public void run()
    {
        while (true)
        {
            System.out.println("Philosopher "+this.location+" is trying to eat");
            this.tryToEat();
            System.out.println("Philosopher "+this.location+" is eating");
            this.eat();
            myPanel.repaint();
            System.out.println("Philosopher "+this.location+" is DONE to eat");
            this.doneEating();
            System.out.println("Philosopher "+this.location+" is thinking");
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

    // tryToEat() tries to catch 2 sticks, by asking for the appropriate stick(left or right) and for which philosopher
    //      to catch it for.
    //      takeStick() is 'synchronized' so only one philosopher thread work upon it.
    //      wait() is inside 'takeStick' function.
    private void tryToEat()
    {
        while (true)
        {
            if (this.myTable.takeStick(this.left, this.location))
                if (this.myTable.takeStick(this.right, this.location))
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