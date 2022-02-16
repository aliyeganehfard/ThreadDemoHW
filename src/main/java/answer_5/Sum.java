package answer_5;

public class Sum implements Calculate{
    private Integer first ;

    public Sum(Integer first) {
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
