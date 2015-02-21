package phoenix.webregistration;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zion on 2/21/2015.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {


    private Context mContext;
    private List<String> mListHeader;
    private HashMap <String, List<String>> mListChild;

    public ExpandableListAdapter(Context context, List<String> listHeader, HashMap<String, List<String>> listChild)
    {
    mContext = context;
    mListHeader = listHeader;
    mListChild = listChild;
    }


    @Override
    public int getGroupCount() {
        return mListHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mListChild.get(mListHeader.get(groupPosition)).size();
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
    String headerText = (String) getGroup(groupPosition);

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.header_school, null);
        }
        TextView viewSchoolHeader = (TextView)convertView.findViewById(R.id.textViewSchool);
        viewSchoolHeader.setText(headerText);
        viewSchoolHeader.setTypeface(null, Typeface.BOLD);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String)getChild(groupPosition, childPosition);

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_department, null);

        }

        TextView viewDeptChild = (TextView)convertView.findViewById(R.id.textViewDept);
        viewDeptChild.setText(childText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
