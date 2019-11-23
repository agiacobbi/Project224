import java.sql.*;
import java.util.List;

public class DatabaseHelper {
    String title;
    List<Question> questionList;

    static final String DATABASE_NAME = "databaseQuestions.db";
    static final String CONNECTION_URL = "jdbc:sqlite:databases/" + DATABASE_NAME;
    static final String ID = "id";


    Connection connection;

    public DatabaseHelper(String title, List<Question> questionList) {
        this.title = title;
        this.questionList = questionList;
        getConnection();
        createSurveyTable();
    }

    public DatabaseHelper() {
        getConnection();
        createSurveyTable();
    }

    public void insertResponse(List<Question> responseList) {
        // INSERT INTO tableContacts VALUES(null, 'Spike the Bulldog',
        // '509-509-5095', '')
        String sqlInsert = "INSERT INTO " + title + " VALUES(null, " ;
        String responses = "";

        for (int i = 0; i < responseList.size() - 1; i++) {
            responses += "'" + responseList.get(i).getUserResponse() + "', " ;
        }

        responses += "'" + responseList.get(responseList.size() - 1).getUserResponse() + "')";
        sqlInsert += responses;

        System.out.println(sqlInsert);
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(sqlInsert);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createSurveyTable() {
        // to interact with a SQLite database
        // we construct SQL statements
        // these are strings that we try to get SQLite to execute
        // CREATE TABLE tableContacts(id INTEGER PRIMARY KEY AUTOINCREMENT,
        // name TEXT,
        // phoneNumber TEXT,
        // imagePath TEXT)

        String sqlCreate = "CREATE TABLE " + title + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        String cols = "";
        for (int i = 0; i < questionList.size() - 1; i++) {
            cols += "Q" + (i + 1) + " TEXT, ";
        }
        cols += "Q" + questionList.size() + " TEXT)";
        sqlCreate += cols;
        System.out.println(sqlCreate);

        if (connection != null && !tableExists()) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(sqlCreate);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getConnection() {
        try {
            connection = DriverManager.getConnection(CONNECTION_URL);
            System.out.println("Successfully connected to the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private boolean tableExists() {
        // http://www.java2s.com/Code/Java/Database-SQL-JDBC/Detectifatableexists.htm
        DatabaseMetaData md = null;
        boolean hasNext = false;
        try {
            md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, title, null);
            hasNext = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasNext;
    }
}
