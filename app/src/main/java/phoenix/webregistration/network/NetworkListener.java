package phoenix.webregistration.network;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Nikhil on 2/14/2015. - Valentines Day! :D
 * A class meant for use as lambda function to handle data response.
 */
public abstract class NetworkListener {
    public abstract void onDataArrival(JSONArray jsonArray);
    public abstract void onDataObjectArrival(JSONObject jsonObject);
}


