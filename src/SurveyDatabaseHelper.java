import javax.print.DocFlavor;
import java.sql.*;

public class SurveyDatabaseHelper {

    static final String DATABASE_NAME = "databaseQuestions.db";
    static final String CONNECTION_URL = "jdbc:sqlite:databases/" + DATABASE_NAME;
    static final String ID = "id";
    static final String QUESTION = "question";
    static final String Q_TYPE = "question_type";
    String surveyTitle;

    Connection connection;

    public SurveyDatabaseHelper (String surveyTitle) {
        this.surveyTitle = surveyTitle;

        getConnection();
        createSurveyTable();
    }

    private void createSurveyTable() {
        String sqlCreate = "CREATE TABLE " + surveyTitle + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QUESTION + " TEXT, " +
                Q_TYPE + " TEXT)";
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
        String sqlInsert = "INSERT INTO " + surveyTitle + "(null, '" +
                question.getText() + "', " +
                question.getQuestionType() + "')";
        System.out.println(sqlInsert);

        if (connection != null && !tableExists()) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(sqlInsert);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Survey getSurvey() {
        Survey survey = new Survey(surveyTitle);
        String sqlSelect = "SELECT * FROM " + surveyTitle;
        System.out.println(sqlSelect);

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlSelect);
                while (resultSet.next()) { // returns false when there are no more records
                    String question = resultSet.getString(QUESTION);
                    String type = resultSet.getString(Q_TYPE);
                    Question newQuestion = new Question(question, "Yes", "No", type);
                    survey.addQuestion(newQuestion);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return survey;
    }

    public void deleteSurvey() {
        String sqlDelete = "DROP TABLE " + surveyTitle;
        System.out.println(sqlDelete);

        if (connection != null && !tableExists()) {
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
            ResultSet rs = md.getTables(null, null, surveyTitle, null);
            hasNext = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasNext;
    }
}
