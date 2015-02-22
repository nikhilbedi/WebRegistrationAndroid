package phoenix.webregistration.beans;

/**
 * A container for common identifiers for a school
 */
public class School {

    private String description;
    private String code;

    public School(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "School{" +
                "description='" + description + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
