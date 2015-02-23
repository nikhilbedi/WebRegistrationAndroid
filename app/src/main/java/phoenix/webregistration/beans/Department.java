package phoenix.webregistration.beans;

/**
 * Created by zion on 2/21/2015.
 */
public class Department {

    @Override
    public String toString() {
        return "Department{" +
                "mCode='" + mCode + '\'' +
                ", mDescription='" + mDescription + '\'' +
                '}';
    }

    public Department(String mCode, String mDescription) {
        this.mCode = mCode;
        this.mDescription = mDescription;
    }

    public Department(){}

    private String mCode;
    private String mDescription;

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}




