/**
 * This class is a wrapper for a question on a survey that the user provides when making a new survey.
 *
 * CPSC 224-01, Fall 2019
 * Final Project -- Poll-A-Bear
 * CITATIONS
 *
 * @author Alex Giacobbi, Ghar Pautz, Win Todd
 * @version v1.0 12/12/19
 */

/**
 * Question class is a wrapper for a question on a survey. It contains a String representation of the question being
 * asked and String representation of every answer to the question. There are 5 possible answers, a-e response.
 */
public class Question {
    private String text;
    private String aResponse;
    private String bResponse;
    private String cResponse;
    private String dResponse;
    private String eResponse;
    private String userResponse;

    /**
     * Constructor for Question object with 2 possible answers
     *
     * @param text text is String representation of the question the user asks on the survey
     * @param aResponse aResponse is String representation of the first answer the user provides to the survey
     * @param bResponse bResponse is String representation of the second answer the user provides to the survey
     */
    public Question(String text, String aResponse, String bResponse) {
        this.text = text;
        this.aResponse = aResponse;
        this.bResponse = bResponse;
        this.cResponse = null;
        this.dResponse = null;
        this.eResponse = null;
        this.userResponse = null;
    }

    /**
     * Constructor for Question object with 3 possible answers
     *
     * @param text text is String representation of the question the user asks on the survey
     * @param aResponse aResponse is String representation of the first answer the user provides to the survey
     * @param bResponse bResponse is String representation of the second answer the user provides to the survey
     * @param cResponse cResponse is String representation of the third answer the user provides to the survey
     */
    public Question(String text, String aResponse, String bResponse, String cResponse) {
        this.text = text;
        this.aResponse = aResponse;
        this.bResponse = bResponse;
        this.cResponse = cResponse;
        this.dResponse = null;
        this.eResponse = null;
        this.userResponse = null;
    }

    /**
     * Constructor for Question object with 4 possible answers
     *
     * @param text text is String representation of the question the user asks on the survey
     * @param aResponse aResponse is String representation of the first answer the user provides to the survey
     * @param bResponse bResponse is String representation of the second answer the user provides to the survey
     * @param cResponse cResponse is String representation of the third answer the user provides to the survey
     * @param dResponse dResponse is String representation of the fourth answer the user provides to the survey
     */
    public Question(String text, String aResponse, String bResponse, String cResponse, String dResponse) {
        this.text = text;
        this.aResponse = aResponse;
        this.bResponse = bResponse;
        this.cResponse = cResponse;
        this.dResponse = dResponse;
        this.eResponse = null;
        this.userResponse = null;
    }

    /**
     * Constructor for Question object with 5 possible answers
     *
     * @param text text is String representation of the question the user asks on the survey
     * @param aResponse aResponse is String representation of the first answer the user provides to the survey
     * @param bResponse bResponse is String representation of the second answer the user provides to the survey
     * @param cResponse cResponse is String representation of the third answer the user provides to the survey
     * @param dResponse dResponse is String representation of the fourth answer the user provides to the survey
     * @param eResponse eResponse is String representation of the fifth answer the user provides to the survey
     */
    public Question(String text, String aResponse, String bResponse, String cResponse, String dResponse, String eResponse) {
        this.text = text;
        this.aResponse = aResponse;
        this.bResponse = bResponse;
        this.cResponse = cResponse;
        this.dResponse = dResponse;
        this.eResponse = eResponse;
        this.userResponse = null;
    }

    /**
     * Default value constructor for Question that loads in values for when an empty Question is created
     */
    public Question() {
        text = "How many dogs do you have?";
        this.aResponse = "none";
        this.bResponse = "1";
        this.cResponse = "2";
        this.dResponse = "3";
        this.eResponse = "4 or more";
        this.userResponse = null;
    }

    /**
     * Setter method for userResponse, which is answer that user selects
     *
     * @param userResponse userResponse is String representation of userResponse
     */
    public void setUserResponse(String userResponse) {
        this.userResponse = userResponse;
    }

    /**
     * Getter method for text
     *
     * @return String representation of text
     */
    public String getText() {
        return text;
    }

    /**
     * Getter method for aResponse
     *
     * @return String representation of aResponse
     */
    public String getaResponse() {
        return aResponse;
    }

    /**
     * Getter method for bResponse
     *
     * @return String representation of bResponse
     */
    public String getbResponse() {
        return bResponse;
    }

    /**
     * Getter method for cResponse
     *
     * @return String representation of cResponse
     */
    public String getcResponse() {
        return cResponse;
    }

    /**
     * Getter method for dResponse
     *
     * @return String representation of dResponse
     */
    public String getdResponse() {
        return dResponse;
    }

    /**
     * Getter method for eResponse
     *
     * @return String representation of eResponse
     */
    public String geteResponse() {
        return eResponse;
    }

    /**
     * Getter method for userResponse
     *
     * @return String representation of userResponse
     */
    public String getUserResponse() {
        return userResponse;
    }

    @Override
    /**
     * Creates string representation of Question object
     */
    public String toString() {
        return "Question: " + text;
    }
}
