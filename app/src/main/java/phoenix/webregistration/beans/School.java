package phoenix.webregistration.beans;

/**
 * Created by zion on 2/21/2015.
 */
public class School {

    private String mCode;
    private String mDescription;

    public School(String mCode, String mDescription) {
        this.mCode = mCode;
        this.mDescription = mDescription;
    }

    public School() {
    }

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

    @Override
    public String toString() {
        return "School{" +
                "mCode='" + mCode + '\'' +
                ", mDescription='" + mDescription + '\'' +
                '}';
    }
}
