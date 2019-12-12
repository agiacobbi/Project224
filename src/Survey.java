/**
 * This an implementation of the Survey class
 * CPSC 224-01, Fall 2019
 * Final Project -- Poll-A-Bear
 * CITATIONS
 *
 * @author Alex Giacobbi, Ghar Pautz, Win Todd
 * @version v1.0 12/12/19
 */

import java.util.ArrayList;
import java.util.List;

/**
 * This class wraps a list of questions into a survey it stores a list of Question
 *  objects and a title. There are functions to add and return the questions as well
 * as get and set the title
 */
public class Survey {
    List<Question> questions;
    String title;

    /**
     * Initializes a survey with a specified title
     * @param title String title of the survey
     */
    public Survey(String title) {
        this.title = title;
        questions = new ArrayList<>();
    }


    /**
     * Returns the title of the survey
     * @return String the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the survey to a specified string
     * @param title String title to set survey title to
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Adds a question to the list of questions in the survey
     * @param question Question to be added
     */
    public void addQuestion(Question question) {
        questions.add(question);
    }


    /**
     * Returns the list of questions in a survey
     * @return an ArrayList of questions
     */
    public List<Question> getQuestions() {
        return questions;
    }
}
