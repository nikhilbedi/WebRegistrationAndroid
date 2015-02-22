package phoenix.webregistration.network;

import phoenix.webregistration.beans.Term;

/**
 * Created by Nikhil on 2/14/2015.
 * A helper class used to build urls for queries.
 */
public class USCApiHelper {

    public static final String TERMS_URL = "http://petri.esd.usc.edu/socAPI/Terms/";
    public static final String SCHOOLS_URL = "http://petri.esd.usc.edu/socAPI/Schools/";

    // This url requires additional parameters to obtain any data
    private static final String COURSES_URL ="http://petri.esd.usc.edu/socAPI/Courses/";

    private static Term defaultTerm = new Term("20151", "Spring 2015", "2015-01-09T00:00:00");


    // Verified to work
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

    public static String getCoursesUrl() {
        return COURSES_URL;
    }

    public static Term getDefaultTerm() {
        return defaultTerm;
    }

    public static void setDefaultTerm(Term defaultTerm) {
        USCApiHelper.defaultTerm = defaultTerm;
    }
}
