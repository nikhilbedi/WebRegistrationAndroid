package phoenix.webregistration.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import phoenix.webregistration.beans.Department;
import phoenix.webregistration.controller.ExpandableListAdapter;
import phoenix.webregistration.R;
import phoenix.webregistration.network.NetworkListener;
import phoenix.webregistration.network.NetworkManager;
import phoenix.webregistration.network.USCApiHelper;

/**
 * Created by zion on 2/15/2015.
 */
public class FragmentTabSchool extends Fragment {

    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;
    private int lastExpandedGroupPosition = 0;
    private final String LOG_TAG = "FragmentTabClasses";

    private List<String> listHeaderData;
    private HashMap<String, List<Department>> listChildData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentschool, container, false);

        listHeaderData = new ArrayList<String>();
        listChildData = new HashMap<String, List<Department>>();
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.listviewSchoolDept);
        expandableListAdapter = new ExpandableListAdapter(getActivity(), this.getClass(), listHeaderData, listChildData);
        expandableListView.setAdapter(expandableListAdapter);

        NetworkManager.requestData(USCApiHelper.getSchoolsUrl(), new NetworkListener() {
            @Override
            public void onDataArrival(JSONArray jsonArray) {
                getSchoolData(jsonArray);
                expandableListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDataObjectArrival(JSONObject jsonObject) {
                // do nothing
            }
        });

        setListeners();

        return rootView;
    }

    private void setListeners() {


        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(getActivity(), "Group Clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                log("child clicked");

                Department dept = (Department)expandableListAdapter.getChild(groupPosition, childPosition);

                Bundle bundle = new Bundle();
                bundle.putString("DepartmentCode", dept.getmCode());

                FragmentTabClass nextFragment = new FragmentTabClass();
                nextFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, nextFragment, null).addToBackStack(null).commit();
                return true;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                if(groupPosition != lastExpandedGroupPosition)
                {
                  //  expandableListView.collapseGroup(lastExpandedGroupPosition);
                    log("new position = " + groupPosition + "previous group position = " + lastExpandedGroupPosition);
                }
                lastExpandedGroupPosition = groupPosition;

            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                expandableListView.collapseGroup(groupPosition);
            }
        });

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
                final String schoolDescription = object.getString("SOC_SCHOOL_DESCRIPTION");
                listHeaderData.add(schoolDescription);
                NetworkManager.requestData(USCApiHelper.buildDepartmentsURL(schoolCode),
                new NetworkListener() {
                    @Override
                    public void onDataArrival(JSONArray jsonArray) {
                        try {
                            jsonArray = jsonArray.getJSONObject(0).
                                    getJSONArray("SOC_DEPARTMENT_CODE");
                            getDepartmentData(schoolDescription, jsonArray);
                            expandableListAdapter.notifyDataSetChanged();
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onDataObjectArrival(JSONObject jsonObject) {
                        // do nothing
                    }
                });
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void getDepartmentData(String schoolDesc, JSONArray jsonArray){
        List<Department> depts = new ArrayList<Department>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                Department dept = new Department();
                dept.setmCode(jsonArray.getJSONObject(i).getString("SOC_DEPARTMENT_CODE"));
                dept.setmDescription(jsonArray.getJSONObject(i).getString("SOC_DEPARTMENT_DESCRIPTION"));

                depts.add(dept);
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        listChildData.put(schoolDesc, depts);
    }

    private void log(String msg)
    {
        Log.i(LOG_TAG, msg);
    }

}
