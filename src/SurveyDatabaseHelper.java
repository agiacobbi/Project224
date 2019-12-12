/**
 * DESCRIPTION
 * CPSC 224-01, Fall 2019
 * Final Project -- Poll-A-Bear
 * CITATIONS
 *
 * @author Alex Giacobbi, Ghar Pautz, Win Todd
 * @version v1.0 12/12/19
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SurveyDatabaseHelper {
    private static final String DATABASE_NAME = "databaseQuestions.db";
    private static final String CONNECTION_URL = "jdbc:sqlite:databases/" + DATABASE_NAME;
    private static final String ID = "id";
    private static final String QUESTION = "question";
    private static final String RE_A = "response_a";
    private static final String RE_B = "response_b";
    private static final String RE_C = "response_c";
    private static final String RE_D = "response_d";
    private static final String RE_E = "response_e";
    private static final String TABLE_NAME = "Survey";
    private static final String SURVEY_NAME = "name";

    private String surveyTitle;
    private Connection connection;

    public SurveyDatabaseHelper (String surveyTitle) {
        this.surveyTitle = surveyTitle;

        getConnection();
        createSurveyTable();
    }

    private void createSurveyTable() {
        String sqlCreate = "CREATE TABLE " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SURVEY_NAME + " TEXT, " +
                QUESTION + " TEXT, " +
                RE_A + " TEXT, " +
                RE_B + " TEXT, " +
                RE_C + " TEXT, " +
                RE_D + " TEXT, " +
                RE_E + " TEXT)";
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

    public void insertQuestion(Question question) {
        String sqlInsert = "INSERT INTO " + TABLE_NAME + " VALUES (null, '" +
                surveyTitle + "', '" +
                question.getText() + "', ";
        sqlInsert += ((question.getaResponse() == null) ? "null, " : ("'" + question.getaResponse() + "', "));
        sqlInsert += ((question.getbResponse() == null) ? "null, " : ("'" + question.getbResponse() + "', "));
        sqlInsert += ((question.getcResponse() == null) ? "null, " : ("'" + question.getcResponse() + "', "));
        sqlInsert += ((question.getdResponse() == null) ? "null, " : ("'" + question.getdResponse() + "', "));
        sqlInsert += ((question.geteResponse() == null) ? "null)" : ("'" + question.geteResponse() + "')"));
        System.out.println(sqlInsert);

        if (connection != null) {
            try {
                System.out.println("INSERTING ROWS!");
                Statement statement = connection.createStatement();
                statement.execute(sqlInsert);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Survey getSurvey() {
        Survey survey = new Survey(surveyTitle);
        String sqlSelect = "SELECT * FROM " + TABLE_NAME + " WHERE " + SURVEY_NAME + " = '" +
                surveyTitle + "'";
        System.out.println(sqlSelect);

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlSelect);
                while (resultSet.next()) {
                    String question = resultSet.getString(QUESTION);
                    String aResp = resultSet.getString(RE_A);
                    String bResp = resultSet.getString(RE_B);
                    String cResp = resultSet.getString(RE_C);
                    String dResp = resultSet.getString(RE_D);
                    String eResp = resultSet.getString(RE_E);
                    Question newQuestion = new Question(question, aResp, bResp, cResp, dResp, eResp);
                    survey.addQuestion(newQuestion);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return survey;
    }

    public List<String> getAvailableSurveys() {
        String sqlSelect = "SELECT DISTINCT " + SURVEY_NAME + " FROM " + TABLE_NAME;
        System.out.println(sqlSelect);
        List<String> surveyList = new ArrayList<>();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlSelect);
                while (resultSet.next()) {
                    String surveyTitle = resultSet.getString(SURVEY_NAME);
                    surveyList.add(surveyTitle);
                    System.out.println(surveyTitle);
                }
                return surveyList;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return surveyList;
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
