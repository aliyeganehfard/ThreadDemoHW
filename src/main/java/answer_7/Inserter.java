package answer_7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.LinkedBlockingQueue;

public class Inserter implements Runnable{
    private LinkedBlockingQueue<String> queue;
    private SemaphoreConnection semaphoreConnection = new SemaphoreConnection();
    private  Service<Entity,Repository> entityService;
    private Connection connection;

    public Inserter(LinkedBlockingQueue<String> queue,
                    Service<Entity,Repository> entityService) {
        this.queue = queue;
        this.entityService = entityService;
    }

    @Override
    public void run() {
        while (!queue.isEmpty()) {
            try {
                this.connection = semaphoreConnection.acquireConnection();
                entityService.add(new Entity(queue.take()), connection);
                semaphoreConnection.releaseConnection(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
