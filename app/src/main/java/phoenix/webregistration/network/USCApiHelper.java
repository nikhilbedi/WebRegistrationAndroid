package phoenix.webregistration.network;

/**
 * Created by Nikhil on 2/14/2015.
 * A helper class used to build urls for queries.
 */
public class USCAPIHelper {

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
}
