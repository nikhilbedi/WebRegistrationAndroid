package phoenix.webregistration.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Nikhil on 2/13/2015.
 */
public class NetworkManager {
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
}
