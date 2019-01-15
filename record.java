import java.util.Scanner;
import java.util.StringTokenizer;

public class record implements Comparable<record> {
    protected info text;
    String delims = ",";
    String firstNameToken;
    String lastNameToken;
    String dateToken;

    /**
     * constructor to create new record object
     * @param l
     */
    public record(String l){
        StringTokenizer st = new StringTokenizer(l,delims);
        firstNameToken = st.nextToken();
        lastNameToken = st.nextToken();
        dateToken = st.nextToken();
        text = new info(firstNameToken.toUpperCase(),lastNameToken.toUpperCase(),dateToken);
    }

    /**
     * checks if user entered date is proper
     */
    public void getDate(){
        int day, month, year;
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Please enter Day: (dd)");
            day = sc.nextInt();
            if(day > 0 && day < 31){
                break;
            }
            else{
                System.out.println("Invalid Day!");
            }
        }
        while(true){
            System.out.println("Please enter Month: (mm)");
            month = sc.nextInt();
            if(month > 0 && month < 13){
                break;
            }
            else{
                System.out.println("Invalid Month!");
            }
        }
        while(true){
            System.out.println("Please enter Year: (yyyy)");
            year = sc.nextInt();
            if(year > 1960){
                break;
            }
            else{
                System.out.println("Invalid Year!");
            }
        }
        String s = "";
        if(day < 10 || month < 10){
            if(day < 10 && month > 9){
                s = "0"+day+"/"+month+"/"+year;
            }
            else if(day > 10){
                s = day+"/0"+month+"/"+year;
            }
            else s = "0"+day+"/0"+month+"/"+year;
        }
        else{
            s = day+"/"+month+"/"+year;
        }
        text.setDate(s);
    }

    /**
     * getter for text in info
     * @return
     */
    public info getText(){
        return text;
    }

    /**
     * override Comparable interface to compare last names
     * @param c
     * @return
     */
    public int compareTo(record c){
        return text.getLastName().compareToIgnoreCase(c.getText().getLastName());
    }

    /**
     * override toString
     * @return
     */
    @Override
    public String toString() {
        return  text.getFirstName() + "," +
                text.getLastName() + "," +
                text.getDOB();
    }
}
