package phoenix.webregistration.beans;

/**
 * A container for common identifiers for a Course
 */
public class Course {

    private Department department;
    private String id;  // e.g. 29629
    private String sisId;   // e.g. ACAD-174
    private String title;   // e.g. Innovators Forum
    private String description;
    private String maxUnits;
    private String minUnits;

    public Course(Department department, String id, String sisId, String title, String description,
                  String maxUnits, String minUnits) {
        this.department = department;
        this.id = id;
        this.sisId = sisId;
        this.title = title;
        this.description = description;
        this.maxUnits = maxUnits;
        this.minUnits = minUnits;
    }

    public String getId() {
        return id;
    }

    public String getSisId() {
        return sisId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMaxUnits() {
        return maxUnits;
    }

    public String getMinUnits() {
        return minUnits;
    }

    public Department getDepartment() {
        return department;
    }
}
