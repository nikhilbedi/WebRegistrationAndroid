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

    public static final String SCHOOLS_URL = "http://petri.esd.usc.edu/socAPI/Schools/";

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONArray readJSONFromURL(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static void requestData(String url, NetworkListener listener) {
        NetworkSyncTask schoolsTask = new NetworkSyncTask();
        schoolsTask.setListener(listener);
        schoolsTask.execute(url);
    }
}
