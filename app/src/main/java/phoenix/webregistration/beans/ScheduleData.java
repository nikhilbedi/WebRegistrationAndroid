package phoenix.webregistration.beans;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import phoenix.webregistration.RegisteredSection;

/**
 * Created by Nikhil on 2/26/2015.
 */
public class ScheduleData {
    private static ArrayList<RegisteredSection> sections = new ArrayList<RegisteredSection>();

    public static void addSection(Section s) {
        RegisteredSection rs = new RegisteredSection();
        rs.section = s;

        // random for now
        Random rand = new Random();
        int max = 6; int min = 0;
        rs.day = rand.nextInt((max - min) + 1) + min;
        max = 23; min = 0;
        rs.tapMargin = rand.nextInt((max - min) + 1) + min;
        min = rs.tapMargin;
        rs.buttonHeight = rand.nextInt((max - min) + 1) + min;

        // TODO determine day, topMargin (begin time), and buttonheight (duration)
        rs.day = 0;
        rs.tapMargin = 8;
        rs.buttonHeight = 4;

        sections.add(rs); // TODO
    }

    public static ArrayList<RegisteredSection> getSections(){
        return sections;
    }
}
