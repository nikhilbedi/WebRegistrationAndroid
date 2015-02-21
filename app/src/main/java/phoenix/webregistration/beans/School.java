package phoenix.webregistration.beans;

/**
 * Created by zion on 2/21/2015.
 */
public class School {

    private String mName;

    public School() {
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    @Override
    public String toString() {
        return "School{" +
                "mName='" + mName + '\'' +
                '}';
    }
}
