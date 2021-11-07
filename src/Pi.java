import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Pi{
    AtomicInteger AtomicSuccess;
    
    protected int totalThrows;
    protected int counter;

    double piSimulation, pi;
    
    // This is the Monte Carlo simulation: it is a pseudo-arbitrary reference point.
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

    // This is the class' custom constructor.
    public Pi(int i)
    {
        this.AtomicSuccess = new AtomicInteger(0);
        this.totalThrows = i;
    }

    // This is how we get Pi using multiple threads.
    public double PiSimulation() 
    {   
        ExecutorService executor = Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors());
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
}

