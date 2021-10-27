import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Pi{
    AtomicInteger AtomicSuccess;
    
    protected int totalThrows;
    protected int counter;

    private int totalSides;

    double piSimulation, pi;

    // Utilize the Monte Carlo simulation: it is a pseudo-arbitrary reference point.
    class MonteCarlo implements Runnable{
        @Override
        public void run(){
            double x = Math.random();
            double y = Math.random();
            if(x * x + y * y <= 1){
                AtomicSuccess.incrementAndGet();
            }
        }
    }

    // Call the class' custom constructor: it is requiring this.
    public Pi(int i)
    {
        this.AtomicSuccess = new AtomicInteger(0);
        this.totalThrows = i;
        this.pi = 0;
    }

    // Get Pi using multiple threads.
    public double getPiSimulation() 
    {
        int totalProcessors = Runtime.getRuntime().availableProcessors();
        
        ExecutorService executor = Executors.newWorkStealingPool(totalProcessors);
        for(int i = 1; i <= totalThrows; i++)
        {
            Runnable worker = new MonteCarlo();

            executor.execute(worker);
        }
        executor.shutdown();
        while(!executor.isTerminated())
        {
            piSimulation = 4.0 * AtomicSuccess.get() / totalThrows;
        }
            return piSimulation;
    }
    // Use Archimedes' method to get a more accurate Pi.
    public double getPi(int i){                    
        double pi = 0;
        this.totalSides = i;
        pi = totalSides * Math.sin(piSimulation / totalSides);
        return pi;
    }
}

