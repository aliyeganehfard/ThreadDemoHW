package answer_7;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Repository implements RepositoryInterface<Entity>{
//    SemaphoreConnection
    @Override
    public synchronized void add(Entity entity , Connection connection) {
          try {
              String query = "insert into Thread_word(word) values (?)";
              PreparedStatement preparedStatement = connection.prepareStatement(query);
              preparedStatement.setString(1,entity.getWord());
              preparedStatement.executeUpdate();
          }catch (Exception e){
              e.getStackTrace();
          }
    }
}
