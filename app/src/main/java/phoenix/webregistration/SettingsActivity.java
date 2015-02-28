package phoenix.webregistration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.facebook.widget.ProfilePictureView;
import com.parse.ParseUser;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Nikhil on 2/27/2015.
 */
public class SettingsActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Profile Picture
        ProfilePictureView profilePic = (ProfilePictureView) findViewById(R.id.profilePicture);
        profilePic.setDrawingCacheEnabled(true);
        profilePic.setProfileId("1404986386");
        Bitmap bitmap = profilePic.getDrawingCache();

        // imageview
       /* ImageView mImg;
        mImg = (ImageView) findViewById(R.id.imageView);
        mImg.setImageBitmap(bitmap);
        */
        // Spinner
        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        String[] items = new String[]{"20151", "20143", "20152"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);

        // log out button
        Button logoutButton = (Button) findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);
            }
        });

    }

    /**
     * OnClick method for logging out.
     */
    public void logout(View v) {
        ParseUser.logOut();
        /*This session getting and clearing eliminates an issue:
         User is logged in to facebook, logs in to App
         User logs out of Sublime, logs out of Facebook (in either order)
         User logs back into Sublime and bypasses facebook authentication because the session
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

    /**
     * Function loads the users facebook profile pic
     *
     * @param userID
     */
    public Bitmap getUserPic(String userID) {
        String imageURL;
        Bitmap bitmap = null;
        imageURL = "http://graph.facebook.com/"+userID+"/picture?type=small";
        try {
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(imageURL).getContent());
        } catch (Exception e) {
            Log.d("TAG", "Loading Picture FAILED");
            e.printStackTrace();
        }
        return bitmap;
    }
}
