public class Main {
    public static void main(String[] args) throws Exception {

        // Declare PI.
        double piSimulationRes, pi;
        // Initialize a PI reference value.
        double piEgypt = 22.0/7.0;
        // Run the simulation.
        Pi testPi = new Pi(10000000);
        piSimulationRes = testPi.getPiSimulation();
        
        
        

        // Find the most accurate side using Archimedes' method.
        pi = testPi.getPi(99999);

        // Print the end-user dialogue.
        System.out.println("Here is the test result.");
        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Pi Simulation: " + piSimulationRes);
        System.out.println("An Egyptian Pi record: " + piEgypt);
        System.out.println("Error difference: " + (piSimulationRes - piEgypt));
        System.out.println("Error rate: " + (piSimulationRes - piEgypt) / piEgypt * 100 + "%");
        System.out.println("Adjusted Pi result with Archimedes' method: " + pi);

        // Call the default constructor to run the custom return statement.
        Pi piRuntime = new Pi();
        piRuntime.toString();
    }
}
