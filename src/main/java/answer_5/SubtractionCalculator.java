package answer_5;

public class SubtractionCalculator implements Runnable{
    private final Calculate sum;
    private final Calculate subtraction;

    public SubtractionCalculator(Calculate sum, Calculate subtraction) {
        this.sum = sum;
        this.subtraction = subtraction;
    }

    @Override
    public void run() {
        try {
            synchronized (subtraction) {
                System.out.println(subtraction.getResult(sum.getNumber()));
                Thread.sleep(100);
                System.out.println("sub cal subtract");
                synchronized (sum) {
                    System.out.println(sum.getResult(subtraction.getNumber()));
                    System.out.println("sub cal sum");
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
