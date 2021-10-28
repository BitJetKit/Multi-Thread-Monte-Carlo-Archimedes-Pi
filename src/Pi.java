/**
 * @author BitJetKit
 * @since July 2021
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Pi{
    private AtomicInteger atomicSuccess;
    
    private int totalThrows;
    private int counter;
    private int iterator;
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
            }
        }
    }
    // Utilize the class' default constructor for testing the runtime complexity.
    public Pi(){
    }

    // Call the class' custom constructor: it is requiring this.
    public Pi(int i)
    {
        resetSuccess();
        resetCounter();
        this.totalThrows = i;
    }
    // Implement the accessors.
    public int getIterator(){
        return iterator;
    }
    public void setIterator(){
        this.iterator = iterator++;
    }
    public int getCounter(){
        return counter;
    }
    public void setCounter(){
        this.counter = counter++;
    }
    // Reset the fields.
    public void resetIterator(){
        this.iterator = 0;
    }
    public void resetCounter(){
        this.counter = 0;
    }
    public void resetSuccess(){
        this.atomicSuccess = new AtomicInteger(0);
    }
    public void resetPi(){
        this.pi = 0;
    }
    // Get Pi using multiple threads.
    public double getPiSimulation() 
    {
        int totalProcessors = Runtime.getRuntime().availableProcessors();
        
        ExecutorService executor = Executors.newWorkStealingPool(totalProcessors);
        for(iterator = 0; iterator < totalThrows; iterator++)
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
        return "Here is the total increments of Monte Carlo method (i.e. total throws): " + i +
               ". Here is the measurement counter to test loop unrolling: " + counter +
               ". These are identical: it is that these are running in constant time. " +
               "So, the runtime complexity of this is iterative: it is a runtime complexity, Big Omicron (n).";
    }
}
