package phoenix.webregistration.network;

import org.json.JSONArray;

/**
 * Created by Nikhil on 2/14/2015.
 */
public abstract class NetworkListener {
    public abstract void onDataArrival(JSONArray jsonArray);
}
