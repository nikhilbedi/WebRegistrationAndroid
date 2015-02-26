package phoenix.webregistration.beans;

/**
 * Created by zion on 2/21/2015.
 */
public class Course {

    private String mCID;
    private String mTitle;
    private String mID;
    private String mCredits;
    private String mDescription;
    private String mSchedule;

    public Course(String mTitle, String mID, String mCID, String mCredits, String mDescription, String mSchedule) {
        this.mTitle = mTitle;
        this.mID = mID;
        this.mCID = mCID;
        this.mCredits = mCredits;
        this.mDescription = mDescription;
        this.mSchedule = mSchedule;
    }

    public String getmCID() {
        return mCID;
    }

    public void setmCID(String mCID) {
        this.mCID = mCID;
    }

    public String getmSchedule() {
        return mSchedule;
    }

    public void setmSchedule(String mSchedule) {
        this.mSchedule = mSchedule;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

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
                ", mDescription='" + mDescription + '\'' +
                ", mSchedule='" + mSchedule + '\'' +
                '}';
    }
}
