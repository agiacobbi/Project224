/**
 * Connects the program to a response database so responses can be recorded
 * and queried across runs of the program.
 * CPSC 224-01, Fall 2019
 * Final Project -- Poll-A-Bear
 * CITATIONS
 *
 * @author Alex Giacobbi, Ghar Pautz, Win Todd
 * @version v1.0 12/12/19
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class connects to a database to persist response data between runs of the app.
 * There are string constants for all database attributes as well as a connection
 * field and a title field. There are methods to create the table for the database,
 * insert new responses, retrieve responses for a question, retrieve a list of all
 * survey titles with recorded responses, delete a survey's response data, clear the table,
 * establish a connection, and close the connection.
 *
 * @see Question
 */
public class ResponseDatabaseHelper {
    private static final String DATABASE_NAME = "databaseResponses.db";
    private static final String CONNECTION_URL = "jdbc:sqlite:databases/" + DATABASE_NAME;
    private static final String TABLE_NAME = "Response";
    private static final String ID = "id";
    private static final String SURVEY_NAME = "name";
    private static final String QUESTION = "question";
    private static final String RESPONSE = "response";

    private Connection connection;
    private String title;

    /**
     * Initializes a connection to the database and creates the response table if
     * it has not yet been created. Initializes title to the specified title
     * @param title String survey title
     */
    public ResponseDatabaseHelper(String title) {
        this.title = title;
        getConnection();
        createResponseTable();
    }

    /**
     * Initializes a connection to the database and creates the response table if
     * it has not yet been created.
     */
    public ResponseDatabaseHelper() {
        getConnection();
        createResponseTable();
    }

    /**
     * Creates the response table if it does not yet exist
     */
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

    /**
     * Gets a list of all surveys that have results recorded in the database
     * @return a list of string survey titles
     */
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

    /**
     * Inserts responses for a survey into the response table
     * @param responseList a list of questions to insert responses for
     */
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

    /**
     * Retrieves responses for a specified question. Counts responses for each response option
     * and returns a Map of response option to number of recorded responses
     * @param question Question to find responses for
     * @return a Map of response option, num responses for the option
     */
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


    /**
     * Deletes all response data for a given survey title from the table
     * @param title string title of the survey to delete responses for
     */
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

    /**
     * Clears all data from the Response table
     */
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

    /**
     * Establishes a connection to the database
     */
    public void getConnection() {
        try {
            connection = DriverManager.getConnection(CONNECTION_URL);
            System.out.println("Successfully connected to the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Closes the connection to the database
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Checks if the Response table has been created
     * @return true if the Response table exists, false otherwise
     */
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
