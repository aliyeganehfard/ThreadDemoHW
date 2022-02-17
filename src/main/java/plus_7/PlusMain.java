package plus_7;

import answer_7.Entity;
import answer_7.Repository;
import answer_7.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PlusMain {
    public static void main(String[] args) throws FileNotFoundException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        File file = new File("C:\\Users\\Pc\\Downloads\\sample-2mb-text-file.txt");
        Scanner scanner = new Scanner(file);
        Long before = System.currentTimeMillis();
        while (scanner.hasNextLine()) {
            String[] word = scanner.nextLine().split(" ");
            for (String str: word) {
                queue.offer(str);
            }
        }
        System.out.println("word : "+ queue.size());
        Long after = System.currentTimeMillis();
        System.out.print("save to queue : ");
        System.out.println(after-before);

        long a1 = System.currentTimeMillis();
        insert(queue);
        long a2 = System.currentTimeMillis();
        System.out.print("save to db : ");
        System.out.println(a2-a1);

    }
    private static void insert(LinkedBlockingQueue<String> queue ){
        Service<Entity, Repository> service = new Service<>(new Repository());
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 1; i++) {
            executorService.execute(new ThreadPlus(queue,service));
        }
        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
