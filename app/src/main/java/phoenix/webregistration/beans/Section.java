package phoenix.webregistration.beans;

import java.util.Date;

/**
 * Created by Nikhil on 2/22/2015.
 */

public class Section {

    public enum SectionType {   // json key "TYPE" returns "Lecture" or null
        LECTURE, DISCUSSION
    }

    private Course course;
    private String id;
    private String sectionNumber;
    private String beginTime; // TODO may be changed to a different format to support datetime
    private String endTime; // TODO may be changed to a different format to support datetime
    private int seats;
    private String instructor;
    private SectionType type;
    private String days;    // TODO may be changed. JSON returns in format "MTWHF"
    private String location;    // e.g. THH101

    public Section(Course course, String id, String sectionNumber, String beginTime, String endTime,
                   int seats, String instructor, SectionType type, String days, String location) {
        this.course = course;
        this.id = id;
        this.sectionNumber = sectionNumber;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.seats = seats;
        this.instructor = instructor;
        this.type = type;
        this.days = days;
        this.location = location;
    }

    public Course getCourse() {
        return course;
    }

    public String getId() {
        return id;
    }

    public String getSectionNumber() {
        return sectionNumber;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getSeats() {
        return seats;
    }

    public String getInstructor() {
        return instructor;
    }

    public SectionType getType() {
        return type;
    }

    public String getDays() {
        return days;
    }

    public String getLocation() {
        return location;
    }
}
