package answer_6;

import java.security.SecureRandom;
import java.util.Arrays;

public class SimpleArray{

    private static final SecureRandom generator = new SecureRandom();
    private final int array[];
    private int writeIndex = 0;


    public SimpleArray(int index) {

        this.array = new int[index];
    }

    public synchronized void add(int value){

        int position = writeIndex;

        try{
            Thread.sleep(generator.nextInt(500));
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        array[position] = value;
        System.out.printf("%s wrote %2d to element %n",
                Thread.currentThread().getName(),value);

        ++writeIndex;
    }

    public String toString(){

        return Arrays.toString(array);
    }
}
