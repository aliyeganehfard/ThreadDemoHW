package answer_6;

public class ArrayWriter extends Thread{

    private final SimpleArray sharedSimpleArray;
    private final int startValue;

    public ArrayWriter( int startValue, SimpleArray sharedSimpleArray) {
        this.sharedSimpleArray = sharedSimpleArray;
        this.startValue = startValue;
    }

    @Override
    public void run() {

        for (int i = startValue; i < startValue+3; i++) {
            sharedSimpleArray.add(i);
        }

    }
}
