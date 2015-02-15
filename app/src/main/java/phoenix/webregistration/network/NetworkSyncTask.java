package phoenix.webregistration.network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;

import phoenix.webregistration.MainActivity;
import phoenix.webregistration.WebRegistrationApplication;

/**
 * Created by Nikhil on 2/13/2015.
 * Networking must be done on separate threads. This class is for that purpose.
 */
public class NetworkSyncTask extends AsyncTask<String, Void, JSONArray> {

    private NetworkListener listener;

    public void setListener(NetworkListener listener) {
        this.listener = listener;
    }

    // onPreExecute -- Set up progress dialogs

    @Override
    protected JSONArray doInBackground(String... params) {
        JSONArray json;
        try {
            json = NetworkManager.readJSONFromURL(params[0]);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(JSONArray result) {
        listener.onDataArrival(result);
    }
}
