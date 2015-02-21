package phoenix.webregistration.beans;

/**
 * Created by zion on 2/21/2015.
 */
public class Classes {

    private String mTitle;
    private String mID;
    private String mCredits;

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmCredits() {
        return mCredits;
    }

    public void setmCredits(String mCredits) {
        this.mCredits = mCredits;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "mTitle='" + mTitle + '\'' +
                ", mID='" + mID + '\'' +
                ", mCredits='" + mCredits + '\'' +
                '}';
    }
}
