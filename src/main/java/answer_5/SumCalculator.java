package answer_5;

public class SumCalculator implements Runnable{
    private final Calculate sum;
    private final Calculate subtraction;

    public SumCalculator(Calculate sum,Calculate subtraction) {
        this.sum = sum;
        this.subtraction = subtraction;
    }

    @Override
    public void run() {
        try {
            synchronized (sum){
                System.out.println(sum.getResult(subtraction.getNumber()));
                Thread.sleep(100);
                System.out.println("sum cal sum");
                synchronized (subtraction) {
                    System.out.println(subtraction.getResult(sum.getNumber()));
                    System.out.println("sum cal subtract");
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
