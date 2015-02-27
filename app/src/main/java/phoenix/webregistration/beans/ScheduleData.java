package phoenix.webregistration.beans;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import phoenix.webregistration.RegisteredSection;
import phoenix.webregistration.network.USCApiHelper;

/**
 * Created by Nikhil on 2/26/2015.
 */
public class ScheduleData {
    private static ArrayList<RegisteredSection> sections = new ArrayList<RegisteredSection>();

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


}
