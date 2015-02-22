package phoenix.webregistration;


import android.net.Network;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import phoenix.webregistration.beans.School;
import phoenix.webregistration.network.NetworkListener;
import phoenix.webregistration.network.NetworkManager;
import phoenix.webregistration.network.USCApiHelper;

/**
 * Created by zion on 2/15/2015.
 */
public class FragmentTabClasses extends Fragment {

    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;

    private List<String> listHeaderData;
    private HashMap<String, List<String>> listChildData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentclasses, container, false);

        listHeaderData = new ArrayList<String>();
        listChildData = new HashMap<String, List<String>>();
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.listviewSchoolDept);
        expandableListAdapter = new ExpandableListAdapter(getActivity(), listHeaderData, listChildData);
        expandableListView.setAdapter(expandableListAdapter);

        NetworkManager.requestData(USCApiHelper.getSchoolsUrl(), new NetworkListener() {
            @Override
            public void onDataArrival(JSONArray jsonArray) {
                getSchoolData(jsonArray);
                expandableListAdapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }

    private void getSchoolData(JSONArray jsonArray) {
        // For each school
            // add it to listHeaderData
            // create a new List<String>
            // For each department in this school
                // add department name to list
            // assign school as key, list as value, in listChildData
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String schoolCode = object.getString("SOC_SCHOOL_CODE");
                String schoolDescription = object.getString("SOC_SCHOOL_DESCRIPTION");
                final School school = new School(schoolDescription, schoolCode);
                listHeaderData.add(schoolDescription);
                List<String> dept = new ArrayList<String>();
                NetworkManager.requestData(USCApiHelper.buildDepartmentsURL(schoolCode),
                new NetworkListener() {
                    @Override
                    public void onDataArrival(JSONArray jsonArray) {
                        try {
                            jsonArray = jsonArray.getJSONObject(0).
                                    getJSONArray("SOC_DEPARTMENT_CODE");
                            getDepartmentData(school, jsonArray);
                            expandableListAdapter.notifyDataSetChanged();
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void getDepartmentData(School school,JSONArray jsonArray){
        List<String> depts = new ArrayList<String>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                depts.add(jsonArray.getJSONObject(i).getString("SOC_DEPARTMENT_DESCRIPTION"));
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        listChildData.put(school.getDescription(), depts);
    }

}
