package phoenix.webregistration.fragments;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import phoenix.webregistration.R;
import phoenix.webregistration.RegisteredSection;
import phoenix.webregistration.beans.CourseBinData;
import phoenix.webregistration.beans.ScheduleData;
import phoenix.webregistration.beans.Section;

/**
 * Created by zion on 2/15/2015.
 */
public class FragmentTabSchedule extends Fragment {

    // Variables to help load items into a calendar
    private ArrayList<RegisteredSection> registeredSections;
    private RelativeLayout relativeLayoutSunday;
    private RelativeLayout relativeLayoutMonDay;
    private RelativeLayout relativeLayoutTueDay;
    private RelativeLayout relativeLayoutWedDay;
    private RelativeLayout relativeLayoutThuDay;
    private RelativeLayout relativeLayoutFriDay;
    private RelativeLayout relativeLayoutSatDay;

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
            // TODO obtain data and add to calendar
            populateCalendar();
        }
        else{
            /**
             * Portrait mode of the device
             */
            rootView = inflater.inflate(R.layout.fragmentschedule, null);
        }

        return rootView;
    }

    private void initializeCalendarViews(){
        relativeLayoutSunday = (RelativeLayout) getActivity().findViewById(R.id.relativeLayoutSunday);
        relativeLayoutMonDay = (RelativeLayout) getActivity().findViewById(R.id.relativeLayoutMonDay);
        relativeLayoutTueDay = (RelativeLayout) getActivity().findViewById(R.id.relativeLayoutTueDay);
        relativeLayoutWedDay = (RelativeLayout) getActivity().findViewById(R.id.relativeLayoutWedDay);
        relativeLayoutThuDay = (RelativeLayout) getActivity().findViewById(R.id.relativeLayoutThuDay);
        relativeLayoutFriDay = (RelativeLayout) getActivity().findViewById(R.id.relativeLayoutFriDay);
        relativeLayoutSatDay = (RelativeLayout) getActivity().findViewById(R.id.relativeLayoutSatDay);
    }

    private void populateCalendar(){
        try
        {
            new LoadViewsInToWeekView().execute("");
        } catch (Exception e)
        {
            Log.getStackTraceString(e);
        }
    }

    public Button getButtonToLayout(int height, int marginTop, String buttonText) {
        @SuppressWarnings("deprecation")
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, height);
        Button button = new Button(getActivity().getApplicationContext());
        button.setLayoutParams(params);
        button.setBackgroundColor(getResources().getColor(R.color.brickred));
        button.setText(buttonText);
        button.setOnClickListener(RegisteredSectionListener);
        button.setTextSize(9);
        params.setMargins(0, marginTop, 0, 0);

        return button;

    }
    public OnClickListener RegisteredSectionListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Button b = (Button) v;

            String buttonText = b.getText().toString();

            Toast.makeText(getActivity().getApplicationContext(),
                    buttonText, Toast.LENGTH_SHORT).show();
        }
    };

    public int getTapMargin(int startTime) {

        int time;
        int size = 0;
        try {
            time = startTime;

            switch (time) {
                case 0:
                    size = 0;
                    break;
                case 1:
                    size = 60;

                    break;
                case 2:
                    size = 120;

                    break;
                case 3:
                    size = 180;

                    break;
                case 4:
                    size = 240;

                    break;
                case 5:
                    size = 300;

                    break;
                case 6:
                    size = 360;

                    break;
                case 7:
                    size = 420;

                    break;
                case 8:
                    size = 480;

                    break;
                case 9:
                    size = 540;

                    break;
                case 10:
                    size = 600;

                    break;
                case 11:
                    size = 660;

                    break;
                case 12:
                    size = 720;

                    break;
                case 13:
                    size = 780;

                    break;
                case 14:
                    size = 840;

                    break;
                case 15:
                    size = 900;

                    break;
                case 16:
                    size = 960;

                    break;
                case 17:
                    size = 1020;

                    break;
                case 18:
                    size = 1080;

                    break;
                case 19:
                    size = 1140;

                    break;
                case 20:
                    size = 1200;

                    break;
                case 21:
                    size = 1260;

                    break;
                case 22:
                    size = 1320;

                    break;
                case 23:
                    size = 1380;
                    break;

                default:
                    break;
            }

        } catch (Exception e) {
            Log.getStackTraceString(e);
        }
        return size;
    }

    public int getHeightOfButton(int startTime, int endTime) {
        int subHeight = endTime - startTime;
        return subHeight;
    }


public class LoadViewsInToWeekView extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // Test adding courses
                Section s = new Section();
                s.setTitle("CSCI-101");
                s.setBeginTime("19:00");
                s.setEndTime("20:50");
                s.setDay("H");
                s.setLocation("SAL126");
                ScheduleData.addSection(s);

                registeredSections = ScheduleData.getSections();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String str) {
            try {
                RegisteredSection scheduledCourse;
                int length = registeredSections.size();


                if (length != 0) {
                    initializeCalendarViews();
                    for (int k = 0; k < registeredSections.size(); k++) {
                            scheduledCourse = registeredSections.get(k);
                            for(int i = 0; i < scheduledCourse.days.size(); i++) {
                                int day = scheduledCourse.days.get(i);
                                switch (day) {
                                    case 0:
                                        int sunday = 100;
                                        relativeLayoutSunday
                                                .addView(getButtonToLayout(
                                                        getHeightOfButton(scheduledCourse.tapMargin, scheduledCourse.buttonHeight),
                                                        getTapMargin(scheduledCourse.tapMargin),
                                                        scheduledCourse.section.getTitle()));
                                        sunday++;
                                        break;

                                    case 1:
                                        int MonDay = 200;
                                        relativeLayoutMonDay
                                                .addView(getButtonToLayout(
                                                        getHeightOfButton(scheduledCourse.tapMargin, scheduledCourse.buttonHeight),
                                                        getTapMargin(scheduledCourse.tapMargin),
                                                        scheduledCourse.section.getTitle()));
                                        MonDay++;
                                        break;
                                    case 2:
                                        int TueDay = 200;
                                        relativeLayoutTueDay
                                                .addView(getButtonToLayout(
                                                        getHeightOfButton(scheduledCourse.tapMargin, scheduledCourse.buttonHeight),
                                                        getTapMargin(scheduledCourse.tapMargin),
                                                        scheduledCourse.section.getTitle()));
                                        TueDay++;
                                        break;
                                    case 3:
                                        int WedDay = 200;
                                        relativeLayoutWedDay
                                                .addView(getButtonToLayout(
                                                        getHeightOfButton(scheduledCourse.tapMargin, scheduledCourse.buttonHeight),
                                                        getTapMargin(scheduledCourse.tapMargin),
                                                        scheduledCourse.section.getTitle()));
                                        WedDay++;
                                        break;
                                    case 4:
                                        int ThuDay = 200;
                                        relativeLayoutThuDay
                                                .addView(getButtonToLayout(
                                                        getHeightOfButton(scheduledCourse.tapMargin, scheduledCourse.buttonHeight),
                                                        getTapMargin(scheduledCourse.tapMargin),
                                                        scheduledCourse.section.getTitle()));
                                        ThuDay++;
                                        break;
                                    case 5:
                                        int FriDay = 200;
                                        relativeLayoutFriDay
                                                .addView(getButtonToLayout(
                                                        getHeightOfButton(scheduledCourse.tapMargin, scheduledCourse.buttonHeight),
                                                        getTapMargin(scheduledCourse.tapMargin),
                                                        scheduledCourse.section.getTitle()));
                                        FriDay++;
                                        break;
                                    case 6:
                                        int SatDay = 200;
                                        relativeLayoutSatDay
                                                .addView(getButtonToLayout(
                                                        getHeightOfButton(scheduledCourse.tapMargin, scheduledCourse.buttonHeight),
                                                        getTapMargin(scheduledCourse.tapMargin),
                                                        scheduledCourse.section.getTitle()));
                                        SatDay++;
                                        break;

                                    default:
                                        break;
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                e.printStackTrace();
                }
            }
        }

}
