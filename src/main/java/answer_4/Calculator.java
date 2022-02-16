package answer_4;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Calculator extends Thread {
    private LinkedBlockingQueue<File> queue;
    private Buffer sharedLength;
    private Long length = 0L;
    private boolean state = true;


    public Calculator(LinkedBlockingQueue<File> queue, Buffer sharedLength) {
        this.queue = queue;
        this.sharedLength = sharedLength;
    }

    @Override
    public void run() {

        while (!queue.isEmpty()) {

            File file = null;
            try {
                file = queue.poll(5, TimeUnit.SECONDS);
                if (file != null) {
                    if (file.isFile()) {
                        length += file.length();
                        sharedLength.add(file.length());
                    } else {
                        for (File file1 : Objects.requireNonNull(file.listFiles())) {
                            queue.offer(file1);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void finish() {
        state = false;
    }

    public long getLength() {
        System.out.println("length : " + length / 1024);
        return length / 1024;
    }
}
