package phoenix.webregistration;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * This class will be used to load additional data on
 * start up if the user has not been on in a while.
 * @author Nikhil
 *
 */
public class SplashActivity extends Activity
{
    private static final int LOGIN_REQUEST = 0;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ParseAnalytics.trackAppOpened(getIntent());
        try {
            if(ParseUser.getCurrentUser() != null) {
                Thread.sleep(1000);
                showHomeActivity(false);
            }
            else {
                ParseLoginBuilder builder = new ParseLoginBuilder(this);
                Intent parseLoginIntent = builder.setFacebookLoginButtonText("Just login with Facebook")
                        .build();
                startActivityForResult(parseLoginIntent, LOGIN_REQUEST);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // Simulate a loading process on app startup.
    }

    private void showHomeActivity(boolean isNewUser) {
        Intent intent = new Intent(this, MainActivity.class);

        // Pass data along
        intent.putExtra("isNewUser", isNewUser);

        startActivity(intent);
        //overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode){
            case RESULT_OK:
                ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
                try {
                    ParseUser.getCurrentUser().fetch();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                showHomeActivity(false);
                break;
            case RESULT_FIRST_USER:
                ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
                showHomeActivity(true);
                break;
            case RESULT_CANCELED:
            default:

        }
    }
}