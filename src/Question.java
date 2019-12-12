/**
 * DESCRIPTION
 * CPSC 224-01, Fall 2019
 * Final Project -- Poll-A-Bear
 * CITATIONS
 *
 * @author Alex Giacobbi, Ghar Pautz, Win Todd
 * @version v1.0 12/12/19
 */

public class Question {
    private String text;
    private String aResponse;
    private String bResponse;
    private String cResponse;
    private String dResponse;
    private String eResponse;
    private String userResponse;

    public Question(String text, String aResponse, String bResponse) {
        this.text = text;
        this.aResponse = aResponse;
        this.bResponse = bResponse;
        this.cResponse = null;
        this.dResponse = null;
        this.eResponse = null;
        this.userResponse = null;
    }

    public Question(String text, String aResponse, String bResponse, String cResponse) {
        this.text = text;
        this.aResponse = aResponse;
        this.bResponse = bResponse;
        this.cResponse = cResponse;
        this.dResponse = null;
        this.eResponse = null;
        this.userResponse = null;
    }

    public Question(String text, String aResponse, String bResponse, String cResponse, String dResponse) {
        this.text = text;
        this.aResponse = aResponse;
        this.bResponse = bResponse;
        this.cResponse = cResponse;
        this.dResponse = dResponse;
        this.eResponse = null;
        this.userResponse = null;
    }

    public Question(String text, String aResponse, String bResponse, String cResponse, String dResponse, String eResponse) {
        this.text = text;
        this.aResponse = aResponse;
        this.bResponse = bResponse;
        this.cResponse = cResponse;
        this.dResponse = dResponse;
        this.eResponse = eResponse;
        this.userResponse = null;
    }

    public Question() {
        text = "How many dogs do you have?";
        this.aResponse = "none";
        this.bResponse = "1";
        this.cResponse = "2";
        this.dResponse = "3";
        this.eResponse = "4 or more";
        this.userResponse = null;
    }

    public void setUserResponse(String userResponse) {
        this.userResponse = userResponse;
    }

    public String getText() {
        return text;
    }

    public String getaResponse() {
        return aResponse;
    }

    public String getbResponse() {
        return bResponse;
    }

    public String getcResponse() {
        return cResponse;
    }

    public String getdResponse() {
        return dResponse;
    }

    public String geteResponse() {
        return eResponse;
    }

    public String getUserResponse() {
        return userResponse;
    }

    @Override
    public String toString() {
        return "Question: " + text;
    }
}
