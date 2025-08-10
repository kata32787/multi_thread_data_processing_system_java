import java.util.List;

public class Worker extends Thread {
    private final int workerId;
    private final TaskQueue queue;
    private final List<String> results;

    public Worker(int id, TaskQueue queue, List<String> results) {
        this.workerId = id;
        this.queue = queue;
        this.results = results;
    }

    public void run() {
        try {
            while (true) {
                String task = queue.getTask();
                System.out.println("Worker " + workerId + " started task: " + task);

                // Simulate processing delay
                Thread.sleep(500);

                String result = "Processed by worker " + workerId + ": " + task;

                synchronized (results) {
                    results.add(result);
                }

                System.out.println("Worker " + workerId + " finished task: " + task);
            }
        } catch (InterruptedException e) {
            System.out.println("Worker " + workerId + " interrupted.");
        } catch (Exception e) {
            System.out.println("Worker " + workerId + " error: " + e.getMessage());
        }
    }
}
