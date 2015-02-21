package phoenix.webregistration.beans;

/**
 * Created by zion on 2/21/2015.
 */
public class Department {

private String mName;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "mName='" + mName + '\'' +
                '}';
    }
}




