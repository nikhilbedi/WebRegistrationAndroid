package phoenix.webregistration;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        expandableListView = (ExpandableListView) rootView.findViewById(R.id.listviewSchoolDept);

        prepareListData();

        expandableListAdapter = new ExpandableListAdapter(getActivity(), listHeaderData, listChildData);

        expandableListView.setAdapter(expandableListAdapter);

             //   String[] items = new String[] {"Viterbi School of Engineering", "Marshall School of Business", "Dornsife School", "School of Cinematic Arts"};
     //   ListView listClasses = (ListView)rootView.findViewById(R.id.listClasses);
        //ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,items );
     //  ArrayAdapter classAdapter = new CustomAdapter(getActivity(), android.R.layout.simple_list_item_1,items );
     //   listClasses.setAdapter(classAdapter);
        return rootView;
    }

    private void prepareListData() {

        listHeaderData = new ArrayList<String>();
        listChildData = new HashMap<String, List<String>>();

        // Adding child data
        listHeaderData.add("Viterbi School");
        listHeaderData.add("Annenburg");
        listHeaderData.add("Cinematic Arts");

        // Adding child data
        List<String> viterbiDept = new ArrayList<String>();
        viterbiDept.add("Computer Science");
        viterbiDept.add("Electrical Engineering");
        viterbiDept.add("Petrochemicals");

        List<String> annenburgDept = new ArrayList<String>();
        annenburgDept.add("Communication");
        annenburgDept.add("Media");
        annenburgDept.add("Arts");

        List<String> cinemaDept = new ArrayList<String>();
        cinemaDept.add("production");
        cinemaDept.add("editing");



        listChildData.put(listHeaderData.get(0), viterbiDept);
        listChildData.put(listHeaderData.get(1), annenburgDept);
        listChildData.put(listHeaderData.get(2), cinemaDept);
    }

}
