package answer_4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class FileSize {
    static Buffer sharedLength = new LengthBuffer();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter URL : ");
        String url = scanner.nextLine();
        System.out.print("Enter Thread Number : ");
        int threadNumber = scanner.nextInt();
        File file = new File(url);
        long before = System.currentTimeMillis();
        getFolderSize(file, threadNumber);
        long after = System.currentTimeMillis();
        System.out.println("Folder size : " + sharedLength.getSum());
        System.out.print("Duration : ");
        System.out.println(after - before);
    }

    public static void getFolderSize(File folder, int threadNumber) {
        File[] files = folder.listFiles();
        LinkedBlockingQueue<File> queue = new LinkedBlockingQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
        Calculator calculator = new Calculator(queue, sharedLength);

        assert files != null;
        for (File file : files) {
            queue.offer(file);
        }

        for (int j = 0; j < threadNumber; j++) {
            executorService.execute(calculator);
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
