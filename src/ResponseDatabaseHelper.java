import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseDatabaseHelper {
    static final String DATABASE_NAME = "databaseResponses.db";
    static final String CONNECTION_URL = "jdbc:sqlite:databases/" + DATABASE_NAME;
    static final String TABLE_NAME = "Response";
    static final String ID = "id";
    static final String SURVEY_NAME = "name";
    static final String QUESTION = "question";
    static final String RESPONSE = "response";

    Connection connection;
    String title;

    public ResponseDatabaseHelper(String title) {
        this.title = title;
        getConnection();
        createResponseTable();
    }

    public ResponseDatabaseHelper() {
        getConnection();
        createResponseTable();
    }

    public void createResponseTable() {
        String sqlCreate = "CREATE TABLE " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SURVEY_NAME + " TEXT, " +
                QUESTION + " TEXT, " +
                RESPONSE + " TEXT)";
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

    public List<String> getAvailableSurveys() {
        String sqlSelect = "SELECT DISTINCT " + SURVEY_NAME + " FROM " + TABLE_NAME;
        List<String> surveyList = new ArrayList<>();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlSelect);
                while (resultSet.next()) {
                    String surveyTitle = resultSet.getString(SURVEY_NAME);
                    surveyList.add(surveyTitle);
                }
                return surveyList;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return surveyList;
    }

    public void insertResponse(List<Question> responseList) {
        if (connection != null) {
            try {
                for (Question q : responseList) {
                    String sqlInsert = "INSERT INTO " + TABLE_NAME + " VALUES (null, '" +
                            title + "', '" +
                            q.getText() + "', '" +
                            q.getUserResponse() + "')";
                    Statement statement = connection.createStatement();
                    statement.execute(sqlInsert);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, Integer> getResponsesForQuestion(Question question) {
        Map<String, Integer> resultMap = new HashMap<>();
        String sqlSelect = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                SURVEY_NAME + " = '" + title + "' AND " +
                QUESTION + " = '" + question.getText() + "'";
        System.out.println(sqlSelect);

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlSelect);
                while (resultSet.next()) {
                    String key = resultSet.getString(RESPONSE);
                    System.out.println(key);
                    if (resultMap.containsKey(key)) {
                        resultMap.put(key, resultMap.get(key) + 1);
                    } else {
                        resultMap.put(key, 1);
                    }
                }
                return resultMap;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void deleteSurvey(String title) {
        String sqlDelete = "DELETE FROM " + TABLE_NAME + " WHERE " +
                SURVEY_NAME  + " = '" + title + "'";
        System.out.println(sqlDelete);

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(sqlDelete);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearTable() {
        String sqlDelete = "DELETE FROM " + TABLE_NAME;
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(sqlDelete);
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
            ResultSet rs = md.getTables(null, null, TABLE_NAME, null);
            hasNext = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasNext;
    }
}
