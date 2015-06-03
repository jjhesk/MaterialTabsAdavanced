package net.giancarlofusiello.materialtabslibrary;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by c766847 on 28/01/2015.
 */

public class MyAdapter extends FragmentStatePagerAdapter {

    private static final int FRAGMENT_ONE = 0;
    private static final int FRAGMENT_TWO = 1;
    private static final int FRAGMENT_THREE = 2;

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      /*  switch (position) {
            case FRAGMENT_ONE:
                return new Fragment1();
            case FRAGMENT_TWO:
                return new Fragment2();
            case FRAGMENT_THREE:
                return new Fragment3();
        }*/
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case FRAGMENT_ONE:
                return "Frag1";
            case FRAGMENT_TWO:
                return "Frag2";
            case FRAGMENT_THREE:
                return "Frag3";
        }
        return super.getPageTitle(position);
    }
}
