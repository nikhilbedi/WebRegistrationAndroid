package phoenix.webregistration;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.PushService;
import com.parse.ParseUser;

/**
 * Created by Nikhil on 2/9/2015.
 */
public class WebRegistrationApplication extends Application {

    private static final String TAG = "WEB-REG-APP";
    @Override
    public void onCreate() {
        // PARSE CLIENT SETUP
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, getResources().getString(R.string.parse_app_id), getResources().getString(R.string.parse_client_key));
        ParseFacebookUtils.initialize(getResources().getString(R.string.facebook_app_id));
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        // Set up which activity notifications will be sent to
       // PushService.setDefaultPushCallback(this, FragmentChangeActivity.class, R.drawable.wolf1);

        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    public static String getTag() {
        return TAG;
    }
}
