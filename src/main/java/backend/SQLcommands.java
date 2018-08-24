package backend;

/**
 * @autor Kunakbaev Artem
 */
class SQLcommands {
    static final String CREATE = "CREATE TABLE IF NOT EXISTS SOCIOMETRY" +
            "(" +
            "IDtest INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "Time TEXT NOT NULL," +
            "GroupName TEXT NOT NULL," +
            "Name TEXT NOT NULL," +
            "Motiv TEXT NOT NULL," +
            "Likes TEXT," +
            "Dislakes TEXT" +
            ")";

    static final String DELETE = "DROP TABLE SOCIOMETRY";
    static final String ADD_ROW = "INSERT INTO SOCIOMETRY " +
            "(Time, GroupName, Name, Motiv, Likes, Dislakes) " +
            "VALUES " +
            "(?, ?, ?, ?, ?, ?)";
    static final String READ_ALL = "SELECT * FROM SOCIOMETRY";
}
