package com.testproject.amit.snapchat_new;

/**
 * Created by bhavini on 30-07-2016.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        //Previous code
        //return MainActivity.PlaceholderFragment.newInstance(position + 1);

        switch(position)
        {
            case 0:
                new ListFragment();

            case 1:
                new FriendsFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.title_section1);
            case 1:
                return mContext.getString(R.string.title_section2);
        }
        return null;
    }

}
