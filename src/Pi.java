/**
 * @author BitJetKit
 * @since July 2021
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Pi{
    private AtomicInteger atomicSuccess;
    private AtomicInteger stepCounter;
    
    private int totalThrows;
    private int counter;
    
    private int totalSides;

    double piSimulation, pi;
    
    // Utilize the Monte Carlo simulation: it is a pseudo-arbitrary reference point.
    class MonteCarlo implements Runnable{
        @Override
        public void run(){
            double x = Math.random();
            double y = Math.random();
            if(x * x + y * y <= 1){
                atomicSuccess.incrementAndGet();
                stepCounter.incrementAndGet();
            }
        }
    }
    public Pi(){
    }

    // Call the class' custom constructor: it is requiring this.
    public Pi(int i)
    {
        this.atomicSuccess = new AtomicInteger(0);
        this.stepCounter = new AtomicInteger(0);
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
            piSimulation = 4.0 * atomicSuccess.get() / totalThrows;
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
    // Use a custom toString function.
    public String toString(){
        return "Here is the total increments of Monte Carlo method: " + atomicSuccess + 
               ". Here is the measurement counter to test loop unrolling: " + stepCounter +
               ". These are identical, so these run in constant time: it is that the runtime"
               + " complexity is Bit Omicron (n).";
    }
}