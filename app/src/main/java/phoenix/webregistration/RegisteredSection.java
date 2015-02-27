package phoenix.webregistration;

import java.util.ArrayList;

import phoenix.webregistration.beans.Section;

/**
 * Created by Nikhil on 2/20/2015.
 */
public class RegisteredSection {
    public Section section;
    public ArrayList<Integer> days; // 0 = Sunday, 6 = Saturday
    public int tapMargin;    // how far from the top of screen (12 am) is it
    public int buttonHeight; // duration in terms of ints
}
