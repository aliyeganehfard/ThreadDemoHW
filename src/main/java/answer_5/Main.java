package answer_5;

public class Main {
    public static void main(String[] args) {
        Calculate sum = new Sum(10);
        Calculate subtraction = new Subtraction(5);
        Thread sumThread=new Thread(new SumCalculator(sum,subtraction));
        Thread subtractionThread = new Thread(new SubtractionCalculator(sum,subtraction));
        sumThread.start();
        subtractionThread.start();


    }
}
