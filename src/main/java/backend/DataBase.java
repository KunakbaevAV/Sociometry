package backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Kunakbaev Artem
 */
public class DataBase implements IBaseRespondents<Respondent>{
    private String log;
    private Connection conn;
    private PreparedStatement pst;
    private Statement st;

    public DataBase() {
        log = "Программа готова к работе\n";
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    @Override
    public void createNewBase() {
        try {
            conn = MyConnection.getConnection();
            st = conn.createStatement();
            st.execute(SQLcommands.CREATE_TABLE);
            log = "База создана";
        } catch (Exception e) {
            log = "Ошибка создания базы данных";
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteTable() {
        try {
            conn = MyConnection.getConnection();
            st = conn.createStatement();
            st.execute(SQLcommands.DELETE_TABLE);
            log = "База данных удалена";
        } catch (Exception e) {
            log = "База данных уже была удалена";
        } finally {
            try {
                conn.close();
//                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insert(Respondent respondent) {
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(SQLcommands.ADD_RESPONDENT);
            pst.setString(1, respondent.getDateResearch());
            pst.setString(2, respondent.getGroupName());
            pst.setString(3, respondent.getMotiv());
            pst.setString(4, respondent.getName());
            pst.setString(5, respondent.getLikes());
            pst.setString(6, respondent.getDislikes());
            pst.executeUpdate();
            respondent.setId(getLastIndex(respondent.getName()));
            log = "Респондент добавлен:\n" + respondent.toString();
        } catch (Exception e) {
            log = "База данных отсутствует";
        } finally {
            try {
                conn.close();
//                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Respondent respondent) {
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(SQLcommands.UPDATE_RESPONDENT_TO_ID);
            pst.setString(1, respondent.getDateResearch());
            pst.setString(2, respondent.getGroupName());
            pst.setString(3, respondent.getMotiv());
            pst.setString(4, respondent.getName());
            pst.setString(5, respondent.getLikes());
            pst.setString(6, respondent.getDislikes());
            pst.executeUpdate();
            log = "Информация о респонденте обновлена:\n" + respondent;
        } catch (Exception e) {
            log = "Такой респондент отсутствует";
        } finally {
            try {
//                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Integer> getIdToName(String name) {
        List<Integer> id = new ArrayList<>();
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(SQLcommands.SELECT_ID_BY_NAME);
            pst.setString(1,name);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                id.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            log = "Респонденты с таким именем отсутствуют";
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    private int getLastIndex(String name){
        List<Integer> list = getIdToName(name);
        return list.get(list.size()-1);
    }

    @Override
    public void delete(int id) {
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(SQLcommands.DELETE_RESPONDENT_TO_ID);
            pst.setInt(1, id);
            pst.executeUpdate();
            log = "Респондент с id " + id + " удален";
        } catch (SQLException | NullPointerException e) {
            log = "Такой респондент отсутствует";
        } finally {
            try {
                conn.close();
//                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insertBatch(List<Respondent> list) {
        try {
            conn = MyConnection.getConnection();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(SQLcommands.INSERT_BATCH);
            Respondent respondent = null;

            for (Respondent respondent1 : list) {
                assert false;
                pst.setString(1, respondent.getDateResearch());
                pst.setString(2, respondent.getGroupName());
                pst.setString(3, respondent.getMotiv());
                pst.setString(4, respondent.getName());
                pst.setString(5, respondent.getLikes());
                pst.setString(6, respondent.getDislikes());
                pst.addBatch();
            }
            pst.executeBatch();
            log = "Добавлено " + list.size() + " респондентов";
        } catch (SQLException | NullPointerException e) {
            log = "База данных отсутствует";
        } finally {
            try {
                conn.commit();
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Respondent get(int id) {
        Respondent respondent = null;
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(SQLcommands.SHOW_RESPONDENT_TO_ID);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                respondent = new Respondent(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
            }
            assert respondent != null;
            log = respondent.toString();
        } catch (SQLException | NullPointerException e) {
            log = "Такой респондент осутствует";
            return null;
        } finally {
            try {
                conn.close();
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return respondent;
    }

    @Override
    public List<Respondent> getAll() {
        List<Respondent> list = new ArrayList<>();
        conn = MyConnection.getConnection();
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(SQLcommands.SELECT_ALL);
            while (rs.next()) {
                list.add(new Respondent(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)));
            }
            log = list.toString();
        } catch (SQLException e) {
            log = "Данные отсутствуют";
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private static class SQLcommands {
        static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Sociometry" +
                "(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "DateResearch TEXT NOT NULL," +
                "GroupName TEXT NOT NULL," +
                "Motiv TEXT NOT NULL," +
                "Name TEXT NOT NULL," +
                "Likes TEXT," +
                "Dislakes TEXT" +
                ")";
        static final String ADD_RESPONDENT = "INSERT INTO Sociometry " +
                "(DateResearch, GroupName, Motiv, Name, Likes, Dislakes) " +
                "VALUES " +
                "(?, ?, ?, ?, ?, ?)";
        static final String UPDATE_RESPONDENT_TO_ID = "UPDATE Sociometry SET " +
                "DateResearch = ?, " +
                "GroupName = ?, " +
                "Motiv = ?, " +
                "Name = ?, " +
                "Likes = ?, " +
                "Dislikes = ?" +
                " WHERE Id = ?";
        static final String SHOW_RESPONDENT_TO_ID = "SELECT Id, DateResearch, GroupName, Motiv, Name, Likes, Dislikes " +
                "FROM Sociometry WHERE Id=?";
        static final String DELETE_RESPONDENT_TO_ID = "DELETE FROM Sociometry WHERE Id=?";
        static final String SELECT_ID_BY_NAME = "SELECT Id FROM Sociometry WHERE Name=?";
        static final String INSERT_BATCH = "INSERT INTO Sociometry (DateResearch, GroupName, Motiv, Name, Likes, Dislikes) " +
                "VALUES(?,?,?,?,?,?)";
        static final String SELECT_ALL = "SELECT * FROM Sociometry";
        static final String DELETE_TABLE = "DROP TABLE Sociometry";
    }
}


