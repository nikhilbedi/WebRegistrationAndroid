package phoenix.webregistration;



import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import phoenix.webregistration.fragments.FragmentTabBin;
import phoenix.webregistration.fragments.FragmentTabSchool;
import phoenix.webregistration.fragments.FragmentTabSchedule;
import phoenix.webregistration.controller.SupportFragTabListener;
import phoenix.webregistration.network.NetworkManager;


public class MainActivity extends ActionBarActivity {

    ActionBar actionBar;
    private Fragment fragmentTabClasses;
    private Fragment fragmentTabBin;
    private Fragment fragmentTabSchedule;
    private final String LOG_TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        log("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkManager.makeMyFacebookFriendsRequest();
        setupFragments();
        setupTabs();
    }

    private void setupFragments() {
        log("setupFragments");

        fragmentTabClasses = new FragmentTabSchool();
        fragmentTabBin = new FragmentTabBin();
        fragmentTabSchedule = new FragmentTabSchedule();
    }

    private void setupTabs()
    {
        log("setupTabs");
        actionBar = getSupportActionBar();

        // Hide Actionbar Icon
        actionBar.setDisplayShowHomeEnabled(false);

        // Show Actionbar Title
        actionBar.setDisplayShowTitleEnabled(true);

        // Create Actionbar Tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set Tab Icon and Titles
        ActionBar.Tab tabClasses = actionBar.newTab().setCustomView(R.layout.customtab_classes).setTabListener(new SupportFragTabListener<FragmentTabSchool>(fragmentTabClasses));
        ActionBar.Tab tabCourseBin = actionBar.newTab().setCustomView(R.layout.customtab_coursebin).setTabListener(new SupportFragTabListener<FragmentTabBin>(fragmentTabBin));
        ActionBar.Tab tabSchedule= actionBar.newTab().setCustomView(R.layout.customtab_schedule).setTabListener(new SupportFragTabListener<FragmentTabSchedule>(fragmentTabSchedule));

       // tabClasses.setIcon(R.drawable.tabiconclasses);
       // tabCourseBin.setIcon(R.drawable.tabiconcoursebin);
       // tabSchedule.setIcon(R.drawable.tabiconschedule);

        // Add tabs to actionbar
        actionBar.addTab(tabClasses);
        actionBar.addTab(tabCourseBin);
        actionBar.addTab(tabSchedule, true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void log(String msg)
    {
        Log.i(LOG_TAG, msg);
    }
}
