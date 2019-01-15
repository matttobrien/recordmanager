/**
 * Java Lab 9
 * Create Record Manager
 * @author mattobrien
 * @version 1
 */

public class info {
    private String firstName = "unknown";
    private String lastName = "unknown";
    private String date = "unknown";

    /**
     * constructor to define new info object
     * @param fn
     * @param ln
     * @param d
     */
    public info(String fn, String ln, String d){
        setFirstName(fn);
        setLastName(ln);
        setDate(d);
    }

    /**
     * getter for firstName
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setter for firstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName.toLowerCase();
    }

    /**
     * getter for lastName
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setter for lastName
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName.toLowerCase();
    }

    /**
     * setter for date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * edits the firstName
     * @param fn
     */
    public void editFirstName(String fn){
        setFirstName(fn);
    }

    public void editLastName(String ln){
        setLastName(ln);
    }

    public void editDate(String d){
        setDate(d);
    }

    /**
     * returns the text.date
     * @return
     */
    public String getDOB(){
        return date;
    }
}