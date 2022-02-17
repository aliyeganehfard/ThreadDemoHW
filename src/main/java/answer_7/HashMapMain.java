package answer_7;

import java.util.Collections;
import java.util.*;
import java.util.concurrent.*;

public class HashMapMain {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.print("input : ");
        String input = scn.nextLine();
        String[] words = input.trim().split(" ");
        List<Character> letters = new ArrayList<>();
        if (words.length == 1) {
            long before = System.currentTimeMillis();
//          save String to List<Character>
            saveToList(letters, words[0]);


            insert(fillInTheSet(letters, words[0]));
            long after = System.currentTimeMillis();
            System.out.println();
            System.out.print("duration : ");
            System.out.println(after-before);
        } else {
            System.out.println("wrong input!");
        }

    }

    //  save input to list of character
    private static void saveToList(List<Character> list, String word) {
        for (Character chr : word.toCharArray())
            list.add(chr);
    }


    // fill value in the set -- thread
    private static Set<String> fillInTheSet(List<Character> letters, String word) {

        Set<String> set = new HashSet<>();
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        set.add(word);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        while (set.size() < getLength(letters))
            for (int i = 0; i < 1; i++)
                executorService.execute(new ThreadPool(set,letters));
        executorService.shutdown();
        try {
            executorService.awaitTermination(1,TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return set;
    }

    private static void insert(Set<String> set){
        Service<Entity,Repository> entityService = new Service<>(new Repository());
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        for (var str : set) {
            queue.offer(str);
        }
        System.out.println("length : "+queue.size());
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        for (int i = 0; i <1; i++) {
            executorService.execute(new Inserter(queue,entityService));
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(1,TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    get length of possible word

//      word : 4 * 3 * 2 * 1 / 1 = 24
//      wood : 4 * 3 * 2 * 1 / 2 * 1 = 12
//      wooo : 4 * 3 * 2 * 1 / 3 * 2 * 1 = 4
//      wwww : 4 * 3 * 2 * 1 / 4 * 3 * 2 * 1 = 1

    private static int getLength(List<Character> letters){
        int length = 1;
        int divide = 1;
        List<Character> list =new ArrayList<>(letters);
        for (int i = 1; i <= letters.size(); i++) {
            length *=i;
            int r = 1;
            if (!list.get(i-1).equals('!')){
            r = Collections.frequency(list,list.get(i-1));
            }
            Collections.replaceAll(list,list.get(i-1),'!');
            for (int k = 1; k <= r; k++) {
                divide *=k;
            }
        }
        int count = length/divide;
        return count;
    }
}
