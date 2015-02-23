package phoenix.webregistration.beans;

/**
 * Created by zion on 2/22/2015.
 */



public class Section {

    private String ID;
    private String title;
    private String session;
    private String type;
    private String day;
    private String beginTime;
    private String endTime;
    private String location;
    private String instructor;
    private String seats;

    public Section(){}
    public Section(String ID, String title, String session, String type, String day, String beginTime, String endTime, String location, String instructor, String seats) {
        this.ID = ID;
        this.title = title;
        this.session = session;
        this.type = type;
        this.day = day;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.location = location;
        this.instructor = instructor;
        this.seats = seats;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Section{" +
                "ID='" + ID + '\'' +
                ", title='" + title + '\'' +
                ", session='" + session + '\'' +
                ", type='" + type + '\'' +
                ", day='" + day + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", location='" + location + '\'' +
                ", instructor='" + instructor + '\'' +
                ", seats='" + seats + '\'' +
                '}';
    }
}
