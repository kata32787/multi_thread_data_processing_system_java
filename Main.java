import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TaskQueue taskQueue = new TaskQueue();
        List<String> results = new ArrayList<>();

        int numWorkers = 4;
        List<Worker> workers = new ArrayList<>();

        // Start workers
        for (int i = 1; i <= numWorkers; i++) {
            Worker w = new Worker(i, taskQueue, results);
            w.start();
            workers.add(w);
        }

        // Add tasks
        for (int i = 1; i <= 10; i++) {
            taskQueue.addTask("Task-" + i);
        }

        // Wait and interrupt workers after all tasks are processed
        Thread.sleep(6000);
        for (Worker w : workers) {
            w.interrupt();
        }

        // Output results
        System.out.println("\n=== Results ===");
        for (String result : results) {
            System.out.println(result);
        }
    }
}
