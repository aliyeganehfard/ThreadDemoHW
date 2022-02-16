package answer_1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InsSleep {
    public static void main(String[] args) throws InterruptedException {

        Thread background = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean state = true;
                while (state){
                    try {
                        System.out.println("hello");
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("operation finished!");
                        state = false;
                    }
                }
            }
        });
        background.start();
        Thread.sleep(2000);
        background.interrupt();
    }
}
