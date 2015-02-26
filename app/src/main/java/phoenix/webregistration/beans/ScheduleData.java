package phoenix.webregistration.beans;

import java.util.ArrayList;

/**
 * Created by Nikhil on 2/26/2015.
 */
public class ScheduleData {
    private static ArrayList<Section> sections = new ArrayList<Section>();

    public static void addSection(Section s) {
        sections.add(s);
    }
}
