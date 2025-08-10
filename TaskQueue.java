import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {
    private final Queue<String> tasks = new LinkedList<>();

    public synchronized void addTask(String task) {
        tasks.add(task);
        notify(); // Wake up waiting threads
    }

    public synchronized String getTask() throws InterruptedException {
        while (tasks.isEmpty()) {
            wait(); // Wait for a task
        }
        return tasks.poll();
    }

    public synchronized boolean isEmpty() {
        return tasks.isEmpty();
    }
}
