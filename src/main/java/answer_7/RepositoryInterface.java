package answer_7;

import java.sql.Connection;

public interface RepositoryInterface<E extends Entity> {
    void add(E e , Connection connection);
}
