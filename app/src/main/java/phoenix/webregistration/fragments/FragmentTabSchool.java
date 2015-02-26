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
import phoenix.webregistration.beans.School;
import phoenix.webregistration.controller.ExpandableListAdapterSchool;
import phoenix.webregistration.R;
import phoenix.webregistration.network.NetworkListener;
import phoenix.webregistration.network.NetworkManager;
import phoenix.webregistration.network.USCApiHelper;

/**
 * Created by zion on 2/15/2015.
 */
public class FragmentTabSchool extends Fragment {

    private ExpandableListAdapterSchool expandableListAdapterSchool;
    private ExpandableListView expandableListView;
    private int lastExpandedGroupPosition = 0;
    private final String LOG_TAG = "FragmentTabClasses";

    private List<School> listHeaderData;
    private HashMap<School, List<Department>> listChildData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentschool, container, false);

        listHeaderData = new ArrayList<School>();
        listChildData = new HashMap<School, List<Department>>();
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.listviewSchoolDept);
        expandableListAdapterSchool = new ExpandableListAdapterSchool(getActivity(), this.getClass(), listHeaderData, listChildData);
        expandableListView.setAdapter(expandableListAdapterSchool);

        //fetchDummyData();

        NetworkManager.requestData(USCApiHelper.getSchoolsUrl(), new NetworkListener() {
            @Override
            public void onDataArrival(JSONArray jsonArray) {
                getSchoolData(jsonArray);
                expandableListAdapterSchool.notifyDataSetChanged();
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

                Department dept = (Department) expandableListAdapterSchool.getChild(groupPosition, childPosition);

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
                  expandableListView.collapseGroup(lastExpandedGroupPosition);
                    log("new position = " + groupPosition + "previous group position = " + lastExpandedGroupPosition);
                }
                lastExpandedGroupPosition = groupPosition;

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
            for (int i = 0; i < jsonArray.length() && jsonArray != null ; i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String schoolCode = object.getString("SOC_SCHOOL_CODE");
                final String schoolDescription = object.getString("SOC_SCHOOL_DESCRIPTION");
                final School school = new School(schoolCode, schoolDescription);

                listHeaderData.add(school);
                NetworkManager.requestData(USCApiHelper.buildDepartmentsURL(schoolCode),
                new NetworkListener() {
                    @Override
                    public void onDataArrival(JSONArray jsonArray) {
                        try {
                            jsonArray = jsonArray.getJSONObject(0).
                                    getJSONArray("SOC_DEPARTMENT_CODE");
                            getDepartmentData(school, jsonArray);
                            expandableListAdapterSchool.notifyDataSetChanged();
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

    private void getDepartmentData(School school, JSONArray jsonArray){
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
        listChildData.put(school, depts);
    }

    private void fetchDummyData()
    {
        listHeaderData.add(new School("ENGR", "Viterbi School of Engineering"));
        listHeaderData.add(new School("MSBC","MArshall school of business"));
        listHeaderData.add(new School ("ANNB","Annenburg school of comm"));

        // Adding child data
        List<Department> top250 = new ArrayList<Department>();
        top250.add(new Department("ABCD", "The Shawshank Redemption"));
        top250.add(new Department("BCDE", "The Godfather"));


        List<Department> nowShowing = new ArrayList<Department>();
        nowShowing.add(new Department("ABCD", "The Shawshank Redemption"));
        nowShowing.add(new Department("BCDE", "The Godfather"));

        List<Department> comingSoon = new ArrayList<Department>();
        comingSoon.add(new Department("ABCD", "The Shawshank Redemption"));
        comingSoon.add(new Department("BCDE", "The Godfather"));

        listChildData.put(listHeaderData.get(0), top250); // Header, Child data
        listChildData.put(listHeaderData.get(1), nowShowing);
        listChildData.put(listHeaderData.get(2), comingSoon);
    }
    private void log(String msg)
    {
        Log.i(LOG_TAG, msg);
    }

}
