package q1;

public class PrimeManager
{
    private boolean[] numberToCheckArray;
    private int numberToCheck;
    private int maxThreads;
    private int activeThreads;

    public PrimeManager(int numberToCheck, int maxThreads)
    {
        this.numberToCheck = numberToCheck;

        //boolean array; index number sets to 'true' if index number is Prime, 'false' if not.
        this.numberToCheckArray = new boolean[numberToCheck+1];

        // holds the number of active threads.
        this.activeThreads = 0;

        // sets the limit of threads pool.
        this.maxThreads = maxThreads;

        for (int i=2; i<numberToCheck; i++)
        {
            this.waitForAvailableThreads();
            (new PrimeThread(i, this)).start();
        }

        this.waitForAll();
        System.out.println(this.toString());
    }

    // checks for available thread, if so a thread is been taken from the thread pool, if not waits for one.
    private synchronized void waitForAvailableThreads()
    {
        while (this.maxThreads == this.activeThreads)
        {
            try {wait();}
            catch (InterruptedException e) {}
        }
        this.takeThread();
    }

    // waits for all threads to finish.
    private synchronized void waitForAll()
    {
        while (this.activeThreads > 0);
    }

    // upon every thread that been taken for a job, this function is been called and the thread pool is been updated.
    private void takeThread()
    {
        this.activeThreads++;
    }

    // upon every thread that finished his job, this function is been called and the threads pool is been updated.
    protected void returnThread()
    {
        this.activeThreads--;
    }

    // sets the Prime value for a given number.
    protected void setValueOnNumberArray(int index, boolean isPrime)
    {
        this.numberToCheckArray[index] = isPrime;
    }

    @Override
    public String toString()
    {
        String strToReturn = "Primes until " + this.numberToCheck + ":\n";
        for (int i=2; i<this.numberToCheckArray.length; i++)
        {
            if (this.numberToCheckArray[i])
                strToReturn += "  " + i;
        }
        return strToReturn;
    }
}
