package answer_7;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreConnection {
    private Semaphore semaphore = new Semaphore(5);
    private List<Connection> availableConnection = new ArrayList<>();
    public SemaphoreConnection() {
        for (int i = 0; i < 5; i++) {
            this.availableConnection.add(PostgresConnection.getConnection());
        }
    }

    public Connection acquireConnection() throws InterruptedException{
        semaphore.acquire();
        return availableConnection.remove(0);
    }

    public void releaseConnection(Connection connection){
        availableConnection.add(connection);
        semaphore.release();
    }

}
