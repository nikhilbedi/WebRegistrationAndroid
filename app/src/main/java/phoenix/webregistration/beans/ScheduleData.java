package phoenix.webregistration.beans;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import phoenix.webregistration.network.USCApiHelper;

/**
 * Created by Nikhil on 2/26/2015.
 */
public class ScheduleData {
    private static ArrayList<RegisteredSection> sections = new ArrayList<RegisteredSection>();

    private static ArrayList<Course> courses = new ArrayList<Course>();
    private static HashMap<Course, List<Section>> sectionsP = new HashMap<Course, List<Section>>();


    public static void addSection(Section s) {
        RegisteredSection rs = new RegisteredSection();
        rs.section = s;

        // determine day, topMargin (begin time), and buttonheight (duration)
        rs.days = USCApiHelper.parseDaysFromLetterCodes(s.getDay());
        int beginTime = USCApiHelper.parseTime(s.getBeginTime());
        int endTime = USCApiHelper.parseTime(s.getEndTime());
        rs.tapMargin = beginTime;
        rs.buttonHeight = endTime - beginTime;
        Log.d("RegisteredSection", "days: " + rs.days.get(0) + " beginTime: " + beginTime + " endTime: " + endTime);

        sections.add(rs);
    }

    public static ArrayList<RegisteredSection> getSections(){
        return sections;
    }


    public static void addCourseAndSection(Course c, Section s) {

        boolean found = false;
        Iterator<Course> i = courses.iterator();
        while(i.hasNext())
        {
            if ((i.next()).equals(c)) {
                found = true;
                break;
            }
        }

        if (!found)
        {

            courses.add(c);

            List<Section> sectionList = new ArrayList<Section>();
            sectionList.add(s);

            sectionsP.put(c, sectionList);
        }
        else
        {
            boolean foundSection = false;
            List<Section> sectionList = sectionsP.get(c);

            Iterator<Section> iSection = sectionList.iterator();
            while(iSection.hasNext())
            {
                if ((iSection.next()).equals(s)) {
                    foundSection = true;
                    break;
                }
            }

            if(!foundSection) {
                sectionList.add(s);
                sectionsP.put(c, sectionList);
            }
        }

    }

    public static ArrayList<Course> getCourses() {return courses;}
    public static HashMap<Course, List<Section>> getSectionsP() {return sectionsP;}


}
