package answer_7;

import java.sql.Connection;

public class Service<E ,R extends RepositoryInterface<Entity>> implements RepositoryInterface<Entity>{
    R r ;

    public Service(R r) {
        this.r = r;
    }


    @Override
    public void add(Entity entity, Connection connection) {
            r.add(entity, connection);
    }
}
