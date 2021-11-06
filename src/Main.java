public class Main {
    public static void main(String[] args) throws Exception {
ac
        // These are PI values.
        double piSimulationRes;
        double piEgypt = 22.0/7.0;

        // Get the test start time.
        long startTime = System.currentTimeMillis();
        
        // Run the simulation.
        Pi testPi = new Pi(9000000);
        piSimulationRes = testPi.PiSimulation();

        // Get the test stop time.
        long stopTime = System.currentTimeMillis();

        // These print statements are the end-user dialogue.
        System.out.println("Here is the test result.");
        System.out.println("Total threads: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Pi Simulation: " + piSimulationRes);
        System.out.println("Egypt's 2nd Pi record: " + piEgypt);
        System.out.println("Error difference: " + (piSimulationRes - piEgypt));
        System.out.println("Error rate: " + (piSimulationRes - piEgypt) / piEgypt * 100 + "%");
        System.out.println("Pi library entry: " + Math.PI);
        System.out.println("Time Duration: " + (stopTime - startTime) + "ms");
    }
}
