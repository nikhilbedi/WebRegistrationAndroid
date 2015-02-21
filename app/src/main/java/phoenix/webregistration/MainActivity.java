package phoenix.webregistration;



import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;



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
        setupFragments();
        setupTabs();



        // Testing commit via Android Studio
    }

    private void setupFragments() {
        log("setupFragments");

        fragmentTabClasses = new FragmentTabClasses();
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
        ActionBar.Tab Tab1 = actionBar.newTab().setText("CLASSES").setTabListener(new SupportFragTabListener<FragmentTabClasses>(fragmentTabClasses));
        ActionBar.Tab Tab2 = actionBar.newTab().setText("COURSE BIN").setTabListener(new SupportFragTabListener<FragmentTabBin>(fragmentTabBin));
        ActionBar.Tab Tab3 = actionBar.newTab().setText("SCHEDULE").setTabListener(new SupportFragTabListener<FragmentTabSchedule>(fragmentTabSchedule));

        // Set Tab Listeners
        //Tab1.setTabListener(new SupportFragTabListener<FragmentTabClasses>(this,fragmentTabClasses,FragmentTabClasses.class, "classes"));
        //Tab2.setTabListener(new SupportFragTabListener<FragmentTabBin>(this, fragmentTabBin,FragmentTabBin.class, "mybin"));
        //Tab3.setTabListener(new SupportFragTabListener<FragmentTabSchedule>(this, fragmentTabSchedule,FragmentTabSchedule.class, "schedule"));

        // Add tabs to actionbar
        actionBar.addTab(Tab1);
        actionBar.addTab(Tab2);
        actionBar.addTab(Tab3);


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
