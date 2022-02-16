package answer_4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class LengthBuffer implements Buffer{
    private final List<Long> length ;

    public LengthBuffer() {
//        this.length = new ArrayList<>();
        this.length = new CopyOnWriteArrayList<>();
    }

    @Override
    public void add(Long value) {
        length.add(value);
    }

    @Override
    public Long getSum() {
        return length.stream().mapToLong(Long::longValue).sum();
    }
}
