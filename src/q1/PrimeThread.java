package q1;

// Thread class; this class checks if the number provided is Prime by creating new thread to check upon.
public class PrimeThread extends Thread
{
    private int numberToCheck;
    private PrimeManager manager;

    // Constructor.
    public PrimeThread(int numberToCheck, PrimeManager manager)
    {
        this.manager = manager;
        this.numberToCheck = numberToCheck;
    }

    @Override
    public void run()
    {
        int i;
        for (i=2; i < this.numberToCheck; i++)
        {
            if (this.numberToCheck % i == 0)
                break;
        }
        if (i == this.numberToCheck)
            this.manager.setValueOnNumberArray(this.numberToCheck, true);
        else
            this.manager.setValueOnNumberArray(this.numberToCheck, false);

        this.manager.returnThread();
    }
}
