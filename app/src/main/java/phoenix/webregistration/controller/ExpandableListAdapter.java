package phoenix.webregistration.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import phoenix.webregistration.R;
import phoenix.webregistration.beans.Classes;
import phoenix.webregistration.beans.Department;
import phoenix.webregistration.beans.Section;

/**
 * Created by zion on 2/21/2015.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {


    private Context mContext;
    private Class mClass;
    private List<String> mListHeader;
    private HashMap <String, List<Department>> mListChild;

    private List<Classes> mClassListHeader;
    private HashMap<Classes, List<Section>> mClassListChild;

    private final String LOG_TAG = "ExpandableListAdapter";


    public ExpandableListAdapter(Context context, List<Classes> classListHeader, HashMap<Classes, List<Section>> classListChild)
    {
        mContext = context;
        mClassListHeader = classListHeader;
        mClassListChild = classListChild;
    }

    public ExpandableListAdapter(Context context, Class classname, List<String> listHeader, HashMap<String, List<Department>> listChild)
    {
    mContext = context;
    mClass = classname;
    mListHeader = listHeader;
    mListChild = listChild;
    }


    @Override
    public int getGroupCount() {


        if(mClass != null && mClass.getName().contains("FragmentTabSchool"))
        return mListHeader.size();
        else
            return mClassListHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(mClass != null && mClass.getName().contains("FragmentTabSchool"))
        return mListChild.get(mListHeader.get(groupPosition)).size();
        else
            return mClassListChild.get(mClassListHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        if(mClass != null && mClass.getName().contains("FragmentTabSchool"))
        return mListHeader.get(groupPosition);
        else
            return mClassListHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if(mClass != null && mClass.getName().contains("FragmentTabSchool"))
    return mListChild.get(mListHeader.get(groupPosition)).get(childPosition);
        else
            return mClassListChild.get(mClassListHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        log("getGroupView");


        if(mClass != null && mClass.getName().contains("FragmentTabSchool")) {
            log("FragmentTabSchool");
            String headerText = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.header_school, null);
            }
            TextView viewSchoolHeader = (TextView) convertView.findViewById(R.id.textViewSchool);
            viewSchoolHeader.setText(headerText);



        }

        else
        {
            log("not FragmentTabSchool");
            Classes classObj = (Classes) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.header_class, null);
            }
            TextView viewClassID = (TextView) convertView.findViewById(R.id.textViewCourseID);
            TextView viewClassCredits = (TextView) convertView.findViewById(R.id.textViewCourseCredit);
            TextView viewClassTitle = (TextView) convertView.findViewById(R.id.textViewCourseTitle);

            viewClassID.setText(classObj.getmID());
            viewClassCredits.setText(classObj.getmCredits());
            viewClassTitle.setText(classObj.getmTitle());

        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

    log("getChildView");

        if(mClass != null && mClass.getName().contains("FragmentTabSchool")) {
            log("getChildView::FragmentTabSchool");

            final Department department = (Department)getChild(groupPosition, childPosition);

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.child_department, null);

            }

            TextView viewDeptChild = (TextView) convertView.findViewById(R.id.textViewDept);
            viewDeptChild.setText(department.getmDescription());
        }

        else
        {
            log("getChildView::FragmentTabSchool");
            Section section = (Section) getChild(groupPosition, childPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.child_section, null);

            }

            TextView viewSectionType = (TextView) convertView.findViewById(R.id.textViewSectionType);
            TextView viewSectionSchedule = (TextView) convertView.findViewById(R.id.textViewSectionSchedule);

            String sectionType = section.getType() + " " + section.getInstructor();
            String sectionSchedule = section.getDay() + " " + section.getBeginTime() + " - " + section.getEndTime();

            viewSectionType.setText(sectionType);
            viewSectionSchedule.setText(sectionSchedule);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private void log(String msg)
    {
        Log.i(LOG_TAG, msg);
    }
}
