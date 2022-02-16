package answer_2_3;

import java.util.concurrent.*;

public class CheckingAccount {
/*
        join problem && UnSynchronized
        this class must be synchronized
        because two Thread in a same time (parallel) accessing to CheckingAccount

 */

    private int balance;

    public CheckingAccount(int initialBalance) {
        balance = initialBalance;
    }

    public boolean withdraw(int amount) {
        if (amount <= balance) {
            try {
                Thread.sleep((int) (Math.random() * 200));
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            balance -= amount;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        final CheckingAccount Aa = new CheckingAccount(50);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if (i % 2 == 0)
                        Thread.currentThread().setName("Husband");
                    else
                        Thread.currentThread().setName("Wife");
                    String name = Thread.currentThread().getName();
                    System.out.println(name + " withdraws $10: " + Aa.withdraw(10) + " i is : " + i + " balance is : " + Aa.balance);
                }

            }
        });
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}