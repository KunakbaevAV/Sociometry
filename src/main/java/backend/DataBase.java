package backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @autor Kunakbaev Artem
 */
public class DataBase {
    private Connection conn;
    private PreparedStatement pst;
    private Statement st;

    public void createTable(){
        try {
            conn = MyConnection.getConnection();
            String sql = SQLcommands.CREATE;
            st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Таблица создана");
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteTable(){
        try {
            conn = MyConnection.getConnection();
            String sql = SQLcommands.DELETE;
            st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Таблица удалена");
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addRow(String str){
        try {
            conn = MyConnection.getConnection();
            String sql = SQLcommands.ADD_ROW;
            pst = conn.prepareStatement(sql);
            pst.setString(1, "Дата " + str);
            pst.setString(2, "Группа " + str);
            pst.setString(3, "Имя " + str);
            pst.setString(4, "Мотив " + str);
            pst.setString(5, "Плюсы " + str);
            pst.setString(6, "Минусы " + str);
            pst.addBatch();
            pst.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Строка добавлена");
            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void readAll(){
        List<Respondent> list = new ArrayList<>();
        try {
            conn = MyConnection.getConnection();
            String sql = SQLcommands.READ_ALL;
            st = conn.createStatement();
            st.execute(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                list.add(new Respondent(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                ));
            }
            System.out.println(list.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}


