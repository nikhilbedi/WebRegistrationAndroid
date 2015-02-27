package phoenix.webregistration.fragments;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phoenix.webregistration.R;
import phoenix.webregistration.WebRegistrationApplication;

/**
 * Created by zion on 2/15/2015.
 */
public class FragmentTabSchedule extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView;
        Configuration config = getResources().getConfiguration();
        /**
         * Check the device orientation and act accordingly
         */
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            /**
             * Landscape mode of the device
             */
            ((ActionBarActivity) getActivity()).getSupportActionBar().hide();
            rootView = inflater.inflate(R.layout.schedule_calendar_fragment, null);
        }
        else{
            /**
             * Portrait mode of the device
             */
            rootView = inflater.inflate(R.layout.fragmentschedule, null);
        }

        return rootView;
    }


}
