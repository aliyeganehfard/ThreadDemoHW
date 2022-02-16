package answer_5;

public class Subtraction implements Calculate {
    private Integer first;

    public Subtraction(Integer first) {
        this.first = first;
    }

    @Override
    public Integer getNumber() {
        return first;
    }

    @Override
    public Integer getResult(Integer value) {
        return first - value;
    }
}
