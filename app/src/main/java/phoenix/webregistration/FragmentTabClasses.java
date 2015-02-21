package phoenix.webregistration;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by zion on 2/15/2015.
 */
public class FragmentTabClasses extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentclasses, container, false);

        String[] items = new String[] {"Viterbi School of Engineering", "Marshall School of Business", "Dornsife School", "School of Cinematic Arts"};
        ListView listClasses = (ListView)rootView.findViewById(R.id.listClasses);
        //ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,items );
       ArrayAdapter classAdapter = new CustomAdapter(getActivity(), android.R.layout.simple_list_item_1,items );
        listClasses.setAdapter(classAdapter);
        return rootView;
    }

}
