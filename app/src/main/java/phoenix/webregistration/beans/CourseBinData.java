package phoenix.webregistration.beans;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nikhil on 2/26/2015.
 */
public class CourseBinData {
    private static ArrayList<Course> courses = new ArrayList<Course>();
    private static HashMap<Course, List<Section>> sections = new HashMap<Course, List<Section>>();

    private static String LOG_TAG = "CourseBinData";


    public static void addCourseAndSection(Course c, Section s) {


        log("addCourseAndSection course = " + c.toString() + "section = " + s.toString());
        boolean found = false;
        Iterator<Course> i = courses.iterator();
        while(i.hasNext())
        {
            if ((i.next()).equals(c)) {
                found = true;
                break;
            }
        }

        log("found = " + found);
        if (!found)
        {
            log("Adding course = " + c.toString() );
            courses.add(c);

            log("Addin section to new list = " + s.toString());
            List<Section> sectionList = new ArrayList<Section>();
            sectionList.add(s);

            sections.put(c, sectionList);
          }
        else
        {
            boolean foundSection = false;
           List<Section> sectionList = sections.get(c);

            Iterator<Section> iSection = sectionList.iterator();
            while(iSection.hasNext())
            {
                if ((iSection.next()).equals(s)) {
                    foundSection = true;
                    break;
                }
            }

            log("Addin section to existing list = " + s.toString());
            if(!foundSection) {
                sectionList.add(s);
                sections.put(c, sectionList);
            }
        }

    }

    public static ArrayList<Course> getCourses() {return courses;}
    public static HashMap<Course, List<Section>> getSections() {return sections;}

    private static void log(String msg)
    {
        Log.i(LOG_TAG, msg);
    }

}


