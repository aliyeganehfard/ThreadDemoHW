package plus_7;

import answer_7.Entity;
import answer_7.Repository;
import answer_7.SemaphoreConnection;
import answer_7.Service;

import java.sql.Connection;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPlus extends Thread{
    private final LinkedBlockingQueue<String> queue ;
    private Service<Entity, Repository> service;
    private SemaphoreConnection semaphoreConnection = new SemaphoreConnection();
    private Connection connection;

    public ThreadPlus(LinkedBlockingQueue<String> word , Service<Entity,Repository> service) {
        this.queue = word;
        this.service = service;
    }

    @Override
    public void run() {
        while (!queue.isEmpty()) {
            try {
                this.connection = semaphoreConnection.acquireConnection();
                service.add(new Entity(queue.take()), connection);
                semaphoreConnection.releaseConnection(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
