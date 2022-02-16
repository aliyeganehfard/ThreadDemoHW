package answer_7;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnection {
    public static Connection getConnection(){
        try{
        Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres","ali.1381");
        }catch (Exception e){
            e.getStackTrace();
        }
        return null;
    }
}
