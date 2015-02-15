package phoenix.webregistration;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.parse.ParseUser;

import org.json.JSONArray;

import phoenix.webregistration.network.NetworkListener;
import phoenix.webregistration.network.NetworkManager;
import phoenix.webregistration.network.USCAPIHelper;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // log out button
        Button logoutButton = (Button) findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);
            }
        });

        // Proper way to obtain info from network - Provided as example.
        NetworkManager.requestData(USCAPIHelper.SCHOOLS_URL,
            new NetworkListener() {
                @Override
                public void onDataArrival(JSONArray jsonArray){
                    onSchoolInfoReturn(jsonArray);
                }
            });

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

    /**
     * OnClick method for logging out.
     */
    public void logout(View v) {
        ParseUser.logOut();
        /*This session getting and clearing eliminates an issue:
         User is logged in to facebook, logs in to App
         User logs out of app, logs out of Facebook (in either order)
         User logs back into app and bypasses facebook authentication because the session
            wasn't cleared (this is the uss)
            */
        com.facebook.Session fbs = com.facebook.Session.getActiveSession();
        if(fbs == null) {
            fbs = new com.facebook.Session(this);
            com.facebook.Session.setActiveSession(fbs);
        }
        fbs.closeAndClearTokenInformation();

        Intent intent = new Intent(this, SplashActivity.class);

        startActivity(intent);
    }

    public void onSchoolInfoReturn(JSONArray jsonArray){
        if(jsonArray == null){
            Log.e(WebRegistrationApplication.getTag(), "Network returned a null object!");
            return;
        }
        Log.d(WebRegistrationApplication.getTag(), jsonArray.toString());
    }
}
