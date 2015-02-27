 package phoenix.webregistration.network;

 import android.util.Log;

 import java.util.ArrayList;

 /**
 * Created by Nikhil on 2/14/2015.
 * A helper class used to build urls for queries.
 */
public class USCApiHelper {

    public static final String TERMS_URL = "http://petri.esd.usc.edu/socAPI/Terms/";
    public static final String SCHOOLS_URL = "http://petri.esd.usc.edu/socAPI/Schools/";

    // This url requires additional parameters to obtain any data
    private static final String COURSES_URL ="http://petri.esd.usc.edu/socAPI/Courses/";

    // have yet to verify if this url will return data
    public static String buildDepartmentsURL(String schoolCode){
        return SCHOOLS_URL + schoolCode;
    }

    // have yet to verify if this url will return data
    public static String buildCoursesURL(String term, String departmentCode){
        return COURSES_URL + term + "/" + departmentCode;
    }

    // have yet to verify if this url will return data
    public static String buildSectionsURL(String term, String courseId){
        return COURSES_URL + term + "/" + courseId;
    }

    // Verified to work
    public static String getTermsUrl() {
        return TERMS_URL;
    }

    // Verified to work
    public static String getSchoolsUrl() {
        return SCHOOLS_URL;
    }

    /*
     * Sunday represents 0, Saturday represents 6
    */
    public static ArrayList<Integer> parseDaysFromLetterCodes(String days){
        ArrayList<Integer> arr = new ArrayList<Integer>();

        for(int i = 0; i < days.length(); i++){
            switch(days.charAt(i)){
                case 'M':
                    arr.add(1);
                    break;
                case 'T':
                    arr.add(2);
                    break;
                case 'W':
                    arr.add(3);
                    break;
                case 'H':
                    arr.add(4);
                    break;
                case 'F':
                    arr.add(5);
                    break;
            }
        }
        return arr;
    }

    // returns a number from 0 to 23
    public static int parseTime(String time) {
        String substr = time.substring(0, 2);
        return Integer.parseInt(substr);
    }
}


