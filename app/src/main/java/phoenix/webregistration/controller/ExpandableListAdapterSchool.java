package phoenix.webregistration.controller;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
import phoenix.webregistration.beans.School;
import phoenix.webregistration.beans.Section;

/**
 * Created by zion on 2/21/2015.
 */
public class ExpandableListAdapterSchool extends BaseExpandableListAdapter {


    private Context mContext;
    private Class mClass;
    private List<School> mListHeader;
    private HashMap <School, List<Department>> mListChild;
    private int sdk = android.os.Build.VERSION.SDK_INT;


    private final String LOG_TAG = "ExpandableListAdapter";


    public ExpandableListAdapterSchool(Context context, Class classname, List<School> listHeader, HashMap<School, List<Department>> listChild)
    {
    mContext = context;
    mClass = classname;
    mListHeader = listHeader;
    mListChild = listChild;
    }


    @Override
    public int getGroupCount() {

        if(mListHeader != null)
        return mListHeader.size();

        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if(mListChild.get(mListHeader.get(groupPosition)) != null)
        return mListChild.get(mListHeader.get(groupPosition)).size();

        return 0;
        }

    @Override
    public Object getGroup(int groupPosition) {

        return mListHeader.get(groupPosition);

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

    return mListChild.get(mListHeader.get(groupPosition)).get(childPosition);

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

            School school = (School) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                 convertView = inflater.inflate(R.layout.header_school_new, null);
            }
            TextView viewSchoolHeader = (TextView) convertView.findViewById(R.id.textViewSchoolHeader);
            TextView viewSchoolTitle = (TextView) convertView.findViewById(R.id.textViewSchoolTitle);

            String code = school.getmCode();
            String description = school.getmDescription();

        String codeImage = code.toLowerCase();
        log("school code is " + codeImage);

        if(!(("CNTV".equals(code)) ||("DHRP".equals(code)) || ("GE".equals(code)) || ("GRAD".equals(code)) || ("SOWK".equals(code)) || ("THTR").equals(code))) {
            String[] text = description.split(" ");
            viewSchoolHeader.setText(text[0]);

            String title = " ";
            for (int i = 1; i < text.length; i++) {
                title = title.concat(text[i]) + " ";
            }
            log("title = " + title);
            viewSchoolTitle.setText(title);
        }
        else
        {
            viewSchoolHeader.setText(description);
            viewSchoolTitle.setText("");
        }


        if(isExpanded)
        {
            //viewSchoolHeader.setTextColor(context.getResources().getColor(R.color.grey));
        //    viewSchoolHeader.setTextAppearance(mContext, R.style.Base_TextAppearance_AppCompat_Large);

        }
        else
        {
            //tv.setTextColor(context.getResources().getColor(R.color.white));
           // viewSchoolHeader.setTextAppearance(mContext, R.style.Base_TextAppearance_AppCompat_Medium);
        }




        int imageIdentifier = mContext.getResources().getIdentifier(codeImage, "drawable", mContext.getPackageName());
        log("identifier is " + imageIdentifier);
        Drawable drawable = mContext.getResources().getDrawable(imageIdentifier);
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            convertView.setBackgroundDrawable( drawable );
        } else {
            convertView.setBackground(drawable);
        }


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

    log("getChildView");

            log("getChildView::FragmentTabSchool");

            final Department department = (Department)getChild(groupPosition, childPosition);

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.child_department, null);

            }

            TextView viewDeptChild = (TextView) convertView.findViewById(R.id.textViewDept);
            viewDeptChild.setText(department.getmDescription());

            TextView viewDeptCodeChild = (TextView) convertView.findViewById(R.id.textViewDeptCode);
            viewDeptCodeChild.setText(department.getmCode());

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
