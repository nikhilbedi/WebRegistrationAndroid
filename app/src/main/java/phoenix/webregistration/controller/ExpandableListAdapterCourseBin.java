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
public class ExpandableListAdapterCourseBin extends BaseExpandableListAdapter {


    private Context mContext;
    private Class mClass;

    private List<Classes> mClassListHeader;
    private HashMap<Classes, List<Section>> mClassListChild;

    private final String LOG_TAG = "ExpandableListAdapter";


    public ExpandableListAdapterCourseBin(Context context, List<Classes> classListHeader, HashMap<Classes, List<Section>> classListChild)
    {
        mContext = context;
        mClassListHeader = classListHeader;
        mClassListChild = classListChild;
    }


    @Override
    public int getGroupCount() {
        if(mClassListHeader != null)
          return mClassListHeader.size();

        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(mClassListChild.get(mClassListHeader.get(groupPosition)) != null)
            return mClassListChild.get(mClassListHeader.get(groupPosition)).size();

        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
            return mClassListHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
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


            Classes classObj = (Classes) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.header_class_coursebin, null);
            }
            TextView viewClassID = (TextView) convertView.findViewById(R.id.textViewBinCourseID);
            TextView viewClassCredits = (TextView) convertView.findViewById(R.id.textViewBinCourseCredit);
            TextView viewClassTitle = (TextView) convertView.findViewById(R.id.textViewBinCourseTitle);

            viewClassID.setText(classObj.getmID());
            viewClassCredits.setText(classObj.getmCredits());
            viewClassTitle.setText(classObj.getmTitle());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

    log("getChildView");

            Section section = (Section) getChild(groupPosition, childPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.child_section_coursebin, null);

            }

            TextView viewSectionType = (TextView) convertView.findViewById(R.id.textViewBinSectionType);
            TextView viewSectionSchedule = (TextView) convertView.findViewById(R.id.textViewBinSectionSchedule);

            String sectionType = section.getType() + " " + section.getInstructor();
            String sectionSchedule = section.getDay() + " " + section.getBeginTime() + " - " + section.getEndTime();

            viewSectionType.setText(sectionType);
            viewSectionSchedule.setText(sectionSchedule);

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
