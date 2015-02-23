package phoenix.webregistration;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import phoenix.webregistration.fragments.ScheduleCalendarFragment;
import phoenix.webregistration.fragments.ScheduleListFragment;

/**
 * Created by Nikhil on 2/20/2015.
 */
public class ScheduleActivity extends ActionBarActivity {

    /**
     * mContent is the main fragment to load on start up
     */
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_container);

        Configuration config = getResources().getConfiguration();
        /**
         * Check the device orientation and act accordingly
         */
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            /**
             * Landscape mode of the device
             */
            mContent = new ScheduleCalendarFragment();
            Log.d(WebRegistrationApplication.getTag(), "Landscape mode!");
        }
        else{
            /**
             * Portrait mode of the device
             */
            mContent = new ScheduleListFragment();
            Log.d(WebRegistrationApplication.getTag(), "Portrait mode!");
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.schedule_container, mContent);
        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in,
                android.R.animator.fade_out);
        fragmentTransaction.commit();
    }


}
