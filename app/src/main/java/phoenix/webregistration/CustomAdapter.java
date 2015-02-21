package phoenix.webregistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by zion on 2/18/2015.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private String[] mValues;

    public CustomAdapter(Context context, int resource, String[] values) {
        super(context, resource, values);
        mContext = context;
        mValues  = values;

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.child_department, parent, false);
        TextView textViewSchool = (TextView)rowView.findViewById(R.id.textViewSchool);
       // TextView textViewMajor = (TextView)rowView.findViewById(R.id.textViewMajor);

        textViewSchool.setText(mValues[position]);

        if (mValues[position].startsWith("Viterbi"))
        {
      //      textViewMajor.setText("Computer Science");
        }
        else if (mValues[position].startsWith("Marshall"))
        {
      //      textViewMajor.setText("Organizational Business");
        }
        else if (mValues[position].startsWith("Dornsife"))
        {
       //     textViewMajor.setText("Letters, Arts and Science");
        }

      //  textViewMajor.setText("Production");

        return rowView;
    }


}
