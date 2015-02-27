package phoenix.webregistration.network;

import android.util.Log;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nikhil on 2/13/2015.
 */
public class NetworkManager {
    public static List<GraphUser> FriendsList;

    /*
     * Creates a string from a reader.
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /*
     * Takes a URL and retrieves its JSON response in the form of an JSONArray.
     */
    public static JSONArray readJSONFromURL(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONArray(jsonText);
        } finally {
            is.close();
        }
    }


    public static JSONObject readJSONObjectFromURL(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        } finally {
            is.close();
        }
    }

    /*
     * A wrapper function for retrieving data asynchronously. Example usage:
     * NetworkManager.requestData("url.com", new NetworkListener() {
            @Override
            public void onDataArrival(JSONArray jsonArray){
                // Do Something with Data
            }
        });
     */
    public static void requestData(String url, NetworkListener listener) {
        NetworkSyncTask task = new NetworkSyncTask();
        task.setListener(listener);
        task.execute(url);
    }

    public static void requestObjectData(String url, NetworkListener listener)
    {
        NetworkJsonObjectSyncTask task = new NetworkJsonObjectSyncTask();
        task.setListener(listener);
        task.execute(url);
    }

    /**
     * This grabs all friends of the current user
     */
    public static void makeMyFacebookFriendsRequest() {
        //ParseFacebookUtils.get
        if(ParseFacebookUtils.isLinked(ParseUser.getCurrentUser()))
            Log.d("Facebook", "Current user is linked");
        Log.d("Facebook", "Friends size: " + ParseFacebookUtils.getSession());
        Request friendListRequest = Request.newMyFriendsRequest(ParseFacebookUtils.getSession(),
                new Request.GraphUserListCallback() {
                    @Override
                    public void onCompleted(List<GraphUser> users, Response response) {
                        if (users != null) {
                            FriendsList = users;
                            Log.d("Facebook", "Friends size: " + FriendsList.size());
                            for(int i = 0; i < FriendsList.size(); i++){
                                Log.d("Facebook", "Friend: " + FriendsList.get(i).getFirstName());
                            }
                        }
                    }
                });
        friendListRequest.executeAsync();
    }
}
