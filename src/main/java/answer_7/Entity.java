package answer_7;

public class Entity {
    private Integer id;
    private String word;

    public Entity() {
    }

    public Entity(String word) {
        this.word = word;
    }

    public Entity(Integer id, String word) {
        this.id = id;
        this.word = word;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", word='" + word + '\'' +
                '}';
    }
}
