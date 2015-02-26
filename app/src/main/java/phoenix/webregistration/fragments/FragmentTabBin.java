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

import phoenix.webregistration.beans.Course;
import phoenix.webregistration.beans.Section;
import phoenix.webregistration.controller.ExpandableListAdapterCourseBin;
import phoenix.webregistration.R;
import phoenix.webregistration.network.NetworkListener;
import phoenix.webregistration.network.NetworkManager;
import phoenix.webregistration.network.USCApiHelper;

/**
 * Created by zion on 2/15/2015.
 */
public class FragmentTabBin extends Fragment {

    private ExpandableListAdapterCourseBin expandableListAdapter;
    private ExpandableListView expandableListView;
    private int lastExpandedGroupPosition = 0;

    private final String LOG_TAG = "FragmentTabBin";

    private final String term = "20151";
    private String departmentCode;

    private List<Course> listHeaderData;
    private HashMap<Course, List<Section>> listChildData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentbin, container, false);
        log("onCreate");

        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            departmentCode = bundle.getString("DepartmentCode");
        }
        departmentCode = "ENGR";

        log("DC is = " + departmentCode);

        listHeaderData = new ArrayList<Course>();
        listChildData = new HashMap<Course, List<Section>>();
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.listviewCourseBin);
        expandableListAdapter = new ExpandableListAdapterCourseBin(getActivity(),listHeaderData, listChildData);
        expandableListView.setAdapter(expandableListAdapter);

        // change below
        NetworkManager.requestData(USCApiHelper.buildCoursesURL(term, departmentCode), new NetworkListener() {
            @Override
            public void onDataArrival(JSONArray jsonArray) {
                log("NetworkManager");
                getClassesData(jsonArray);
                // expandableListAdapter.notifyDataSetChanged();
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
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                if(groupPosition != lastExpandedGroupPosition)
                {
                    expandableListView.collapseGroup(lastExpandedGroupPosition);
                }
                lastExpandedGroupPosition = groupPosition;

            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                //expandableListView.collapseGroup(groupPosition);
            }
        });

    }


    private void getClassesData(JSONArray jsonArray) {
        // For each school
        // add it to listHeaderData
        // create a new List<String>
        // For each department in this school
        // add department name to list
        // assign school as key, list as value, in listChildData

        log("getClassesData");
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String cID = object.getString("COURSE_ID");
                String courseID = object.getString("SIS_COURSE_ID");
                String courseTitle = object.getString("TITLE");
                String courseCredits = object.getString("MAX_UNITS");
                String courseDescription = object.getString("DESCRIPTION");

                final Course classObj = new Course(courseTitle, courseID, cID, courseCredits, courseDescription, null);

                log("class object is " + classObj);
                listHeaderData.add(classObj);
                log("Section URI = " +USCApiHelper.buildSectionsURL(term, cID));

                NetworkManager.requestObjectData(USCApiHelper.buildSectionsURL(term, cID),
                        new NetworkListener() {
                            @Override
                            public void onDataArrival(JSONArray jsonArray) {
                                //do nothing
                            }

                            @Override
                            public void onDataObjectArrival(JSONObject jsonObject) {
                                try {
                                    JSONArray jsonArray;
                                    if (jsonObject != null) {
                                        jsonArray = jsonObject.getJSONArray("V_SOC_SECTION");
                                        //  log("jsonObject is = " + jsonObject.toString());
                                        getSectionData(classObj, jsonArray);
                                        expandableListAdapter.notifyDataSetChanged();

                                    } else
                                        log("json array is null from Netwrok listener");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                // List<Classes> classList = new ArrayList<Classes>();

            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void getSectionData(Course classObj, JSONArray jsonArray){

        List<Section> sections = new ArrayList<Section>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                Section section = new Section();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                section.setID(jsonObject.getString("SECTION_ID"));
                section.setTitle(jsonObject.getString("SECTION"));
                section.setSession(jsonObject.getString("SESSION"));
                section.setType(jsonObject.getString("TYPE"));
                section.setBeginTime(jsonObject.getString("BEGIN_TIME"));
                section.setEndTime(jsonObject.getString("END_TIME"));
                section.setDay(jsonObject.getString("DAY"));
                section.setLocation(jsonObject.getString("LOCATION"));
                section.setInstructor(jsonObject.getString("INSTRUCTOR"));
                section.setSeats(jsonObject.getString("SEATS"));
                log("Section is " + section.toString());


                sections.add(section);
            }
            listChildData.put(classObj, sections);

        }
        catch(JSONException e){
            e.printStackTrace();
        }

    }

    private void log(String msg)
    {
        Log.i(LOG_TAG,msg);
    }
}
