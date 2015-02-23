package phoenix.webregistration.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.util.Log;

import phoenix.webregistration.R;

/**
 * Created by zion on 2/15/2015.
 */
public class SupportFragTabListener<T extends Fragment> implements ActionBar.TabListener {

    private FragmentActivity mFragmentActivity;
    private Fragment mFragment;
    private Class<T> mClass;
    private String tag;
    private String logTag = "SupportFragTabListener";

    public SupportFragTabListener(Fragment fragment)
    {
        mFragment = fragment;
    }

    public SupportFragTabListener(FragmentActivity fragmentActivity, Fragment fragment, Class <T> claz, String tag) {

        mFragmentActivity = fragmentActivity;
        mFragment = fragment;
        mClass = claz;
        tag = tag;
        Log.i(logTag, "Constructor with mClass = " + mClass.getName());

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        Log.i(logTag, "onTabSelected");
       /* if(mFragment == null)
        {
            Log.i(logTag, "Fragment is null");
            mFragment = Fragment.instantiate(mFragmentActivity, mClass.getName());
            fragmentTransaction.add(mFragment, tag);
        }
        else {
         Log.i(logTag, "fragment is not null");
            fragmentTransaction.attach(mFragment);
        }
        */
       fragmentTransaction.replace(R.id.fragment_container, mFragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        Log.i(logTag, "onTabUnselected");
        /*if(mFragment != null)
            fragmentTransaction.detach(mFragment);
*/
        fragmentTransaction.remove(mFragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
