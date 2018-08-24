package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @autor Kunakbaev Artem
 */
public class MyConnection {

    private static Connection conn;

    static Connection getConnection(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:dbSociometry");
        } catch (SQLException e) {
            throw new RuntimeException( "Драйвер не зарегистрирован" );
        }
        return conn;
    }
}
