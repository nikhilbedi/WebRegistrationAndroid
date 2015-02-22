package phoenix.webregistration.beans;

/**
 * A container for common identifiers for a department
 */
public class Department {

    private School school;
    private String description;
    private String code;

    public Department(String School, String description, String code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public School getSchool() {
        return school;
    }

    @Override
    public String toString() {
        return "Department{" +
                "school=" + school.toString() +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}


