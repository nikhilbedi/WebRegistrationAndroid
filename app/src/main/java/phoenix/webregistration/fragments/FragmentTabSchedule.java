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

    public Button getButtonToLayout(int height, int marginTop,
                                    String buttonText, String jobID, int buttonID) {
        @SuppressWarnings("deprecation")
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, height);
        Button button = new Button(getActivity().getApplicationContext());
        button.setLayoutParams(params);
        button.setBackgroundColor(getResources().getColor(R.color.brickred));
        button.setText(buttonText);
        button.setOnClickListener(RegisteredSectionListener);
        button.setTextSize(9);
        button.setId(buttonID);
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

    public String getTapMargin(int startTime) {

        int time;
        String size = "0";
        try {
            time = startTime;

            switch (time) {
                case 0:
                    size = "0";
                    break;
                case 1:
                    size = "60";

                    break;
                case 2:
                    size = "120";

                    break;
                case 3:
                    size = "180";

                    break;
                case 4:
                    size = "240";

                    break;
                case 5:
                    size = "300";

                    break;
                case 6:
                    size = "360";

                    break;
                case 7:
                    size = "420";

                    break;
                case 8:
                    size = "480";

                    break;
                case 9:
                    size = "540";

                    break;
                case 10:
                    size = "600";

                    break;
                case 11:
                    size = "660";

                    break;
                case 12:
                    size = "720";

                    break;
                case 13:
                    size = "780";

                    break;
                case 14:
                    size = "840";

                    break;
                case 15:
                    size = "900";

                    break;
                case 16:
                    size = "960";

                    break;
                case 17:
                    size = "1020";

                    break;
                case 18:
                    size = "1080";

                    break;
                case 19:
                    size = "1140";

                    break;
                case 20:
                    size = "1200";

                    break;
                case 21:
                    size = "1260";

                    break;
                case 22:
                    size = "1320";

                    break;
                case 23:
                    size = "1380";
                    break;

                default:
                    break;
            }

        } catch (Exception e) {
            Log.getStackTraceString(e);
        }
        return size;
    }

    public String getHeightOfButton(int startTime, int endTime) {
        String height = "0";
        try {
            int subHeight = endTime - startTime;

            height = String.valueOf(subHeight * 60);

        } catch (Exception e) {
            Log.getStackTraceString(e);
        }

        return height;

    }

    public static RegisteredSection createRegisteredSection(String weekDay, String jobId,
                                         String jobRefId, String tapMargin, String buttonHeight) {
        RegisteredSection r = new RegisteredSection();
        r.day = weekDay;
        r.jobID = jobId;
        r.jobRefID = jobRefId;
        r.tapMargin = tapMargin;
        r.buttonHight = buttonHeight;

        return r;
    }


public class LoadViewsInToWeekView extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                registeredSections = new ArrayList<RegisteredSection>();

                //** for sun day
                String tapMargin = getTapMargin(4);
                String buttonHeight = getHeightOfButton(4, 9);
                registeredSections.add(createRegisteredSection(String.valueOf(0), "12", "ref",
                        tapMargin, buttonHeight));



                //** for tue day
                tapMargin = getTapMargin(8);
                buttonHeight = getHeightOfButton(8, 14);
                registeredSections.add(createRegisteredSection(String.valueOf(2), "12", "ref",
                        tapMargin, buttonHeight));

                //** for tue day
                tapMargin = getTapMargin(15);
                buttonHeight = getHeightOfButton(15, 19);
                registeredSections.add(createRegisteredSection(String.valueOf(2), "12", "ref",
                        tapMargin, buttonHeight));



                //** for fr day
                tapMargin = getTapMargin(2);
                buttonHeight = getHeightOfButton(2, 10);
                registeredSections.add(createRegisteredSection(String.valueOf(5), "12", "ref",
                        tapMargin, buttonHeight));
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String str) {
            try {
                RegisteredSection weekToDay;
                int length = registeredSections.size();
                Log.i("length===>", String.valueOf(length));

                if (length != 0) {
                    initializeCalendarViews();
                    for (int k = 0; k < registeredSections.size(); k++) {
                            weekToDay = registeredSections.get(k);
                            int day = Integer.parseInt(weekToDay.day);
                            switch (day) {
                                case 0:
                                    int sunday = 100;
                                    relativeLayoutSunday
                                            .addView(getButtonToLayout(
                                                    Integer.parseInt(weekToDay.buttonHight),
                                                    Integer.parseInt(weekToDay.tapMargin),
                                                    weekToDay.jobRefID,
                                                    weekToDay.jobID, sunday));
                                    sunday++;
                                    break;

                                case 1:
                                    int MonDay = 200;
                                    relativeLayoutMonDay
                                            .addView(getButtonToLayout(
                                                    Integer.parseInt(weekToDay.buttonHight),
                                                    Integer.parseInt(weekToDay.tapMargin),
                                                    weekToDay.jobRefID,
                                                    weekToDay.jobID, MonDay));
                                    MonDay++;
                                    break;
                                case 2:
                                    int TueDay = 200;
                                    relativeLayoutTueDay
                                            .addView(getButtonToLayout(
                                                    Integer.parseInt(weekToDay.buttonHight),
                                                    Integer.parseInt(weekToDay.tapMargin),
                                                    weekToDay.jobRefID,
                                                    weekToDay.jobID, TueDay));
                                    TueDay++;
                                    break;
                                case 3:
                                    int WedDay = 200;
                                    relativeLayoutWedDay
                                            .addView(getButtonToLayout(
                                                    Integer.parseInt(weekToDay.buttonHight),
                                                    Integer.parseInt(weekToDay.tapMargin),
                                                    weekToDay.jobRefID,
                                                    weekToDay.jobID, WedDay));
                                    WedDay++;
                                    break;
                                case 4:
                                    int ThuDay = 200;
                                    relativeLayoutThuDay
                                            .addView(getButtonToLayout(
                                                    Integer.parseInt(weekToDay.buttonHight),
                                                    Integer.parseInt(weekToDay.tapMargin),
                                                    weekToDay.jobRefID,
                                                    weekToDay.jobID, ThuDay));
                                    ThuDay++;
                                    break;
                                case 5:
                                    int FriDay = 200;
                                    relativeLayoutFriDay
                                            .addView(getButtonToLayout(
                                                    Integer.parseInt(weekToDay.buttonHight),
                                                    Integer.parseInt(weekToDay.tapMargin),
                                                    weekToDay.jobRefID,
                                                    weekToDay.jobID, FriDay));
                                    FriDay++;
                                    break;
                                case 6:
                                    int SatDay = 200;
                                    relativeLayoutSatDay
                                            .addView(getButtonToLayout(
                                                    Integer.parseInt(weekToDay.buttonHight),
                                                    Integer.parseInt(weekToDay.tapMargin),
                                                    weekToDay.jobRefID,
                                                    weekToDay.jobID, SatDay));
                                    SatDay++;
                                    break;

                                default:
                                    break;
                            }

                        }
                    }
                } catch (Exception e) {
                e.printStackTrace();
                }
            }
        }

}
