package answer_6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SharedArrayTest {
    public static void main(String[] args) {

        SimpleArray simpleArray = new SimpleArray(9);

        ArrayWriter writer1 = new ArrayWriter(1,simpleArray);
        ArrayWriter writer2 = new ArrayWriter(11,simpleArray);
        ArrayWriter writer3 = new ArrayWriter(5,simpleArray);

        writer1.setPriority(9);
        writer2.setPriority(1);
        writer3.setPriority(5);

        writer1.start();
        writer2.start();
        writer3.start();

    }
}
