package phoenix.webregistration.beans;

/**
 * A container for common identifiers for a term/semester
 */
public class Term {

    private String code;
    private String description;
    private String normalRegDate;   // TODO consider datetime objects
    // TODO pre reg, early reg, and commencement dates are available


    public Term(String code, String description, String normalRegDate) {
        this.code = code;
        this.description = description;
        this.normalRegDate = normalRegDate;
    }

    public String getNormalRegDate() {
        return normalRegDate;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}