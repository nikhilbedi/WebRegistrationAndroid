package phoenix.webregistration.network;

import android.os.AsyncTask;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nikhil on 2/13/2015.
 * Networking must be done on separate threads. This class is for that purpose.
 */
public class NetworkJsonObjectSyncTask extends AsyncTask<String, Void, JSONObject> {

    private NetworkListener listener;

    public void setListener(NetworkListener listener) {
        this.listener = listener;
    }

    // onPreExecute -- Set up progress dialogs

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject json;
        try {
            json = NetworkManager.readJSONObjectFromURL(params[0]);
            return json;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(JSONObject result) {
        listener.onDataObjectArrival(result);
    }
}
