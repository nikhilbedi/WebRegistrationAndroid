package phoenix.webregistration.controller;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import phoenix.webregistration.R;
import phoenix.webregistration.beans.Course;

import phoenix.webregistration.beans.CourseBinData;
import phoenix.webregistration.beans.ScheduleData;
import phoenix.webregistration.beans.Section;

/**
 * Created by zion on 2/21/2015.
 */
public class ExpandableListAdapterCourseBin extends BaseExpandableListAdapter {


    private Context mContext;
    private Class mClass;

    private List<Course> mClassListHeader;
    private HashMap<Course, List<Section>> mClassListChild;

    private final String LOG_TAG = "ExpandableListAdapter";

    private int sdk = android.os.Build.VERSION.SDK_INT;
    public ExpandableListAdapterCourseBin(Context context, List<Course> classListHeader, HashMap<Course, List<Section>> classListChild)
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


        Course classObj = (Course) getGroup(groupPosition);
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


        Drawable drawable = null;

        if(0 == groupPosition%2)
        {
            drawable = mContext.getResources().getDrawable(R.color.lightblack);
        }
        else
        {
            drawable = mContext.getResources().getDrawable(R.color.lighterblack);
        }

        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            convertView.setBackgroundDrawable(drawable);
        } else {
            convertView.setBackground(drawable);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

    log("getChildView");

        final Section section = (Section) getChild(groupPosition, childPosition);
        final Course course = (Course)getGroup(groupPosition);

        if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.child_section_coursebin, null);

            }

            TextView viewSectionType = (TextView) convertView.findViewById(R.id.textViewBinSectionType);
            TextView viewSectionSchedule = (TextView) convertView.findViewById(R.id.textViewBinSectionSchedule);
        ImageView viewAddClass = (ImageView) convertView.findViewById(R.id.imageViewBinAddSection);

        String sectionType = section.getType().substring(0,3);
        String lecturerName = section.getInstructor();

        String names[] = lecturerName.split(" ");
        String sectionInfo = sectionType + " - " + names[1];

        DateFormat dfdefault = new SimpleDateFormat("h:mm");
        DateFormat df = new SimpleDateFormat("K:mma");

        String sectionSchedule = "";
        try {
            Date startObj = dfdefault.parse(section.getBeginTime());
            Date endObj = dfdefault.parse(section.getEndTime());

            String beginTime = df.format(startObj);
            String endTime = df.format(endObj);

            sectionSchedule = section.getDay() + " " + beginTime + " " + endTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        log("info is " + sectionInfo + "schedule is " + sectionSchedule);
        viewSectionType.setText(sectionInfo);
        viewSectionSchedule.setText(sectionSchedule);


        Drawable drawable = null;

        if(0 == childPosition%2)
        {
            drawable = mContext.getResources().getDrawable(R.color.lightergrey);
        }
        else
        {
            drawable = mContext.getResources().getDrawable(R.color.lightgrey);
        }

        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            convertView.setBackgroundDrawable(drawable);
        } else {
            convertView.setBackground(drawable);
        }

        // listener for click to add
        viewAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScheduleData.addCourseAndSection(course, section);
                Toast.makeText(mContext, "add class ", Toast.LENGTH_LONG).show();
            }
        });


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
