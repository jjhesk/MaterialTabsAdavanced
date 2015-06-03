package net.giancarlofusiello.materialtabslibrary;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


/**
 * A simple {@link Fragment} subclass.
 */

public class Fragment1 extends Fragment implements MaterialTabListener {


    private MaterialTabHost tabHost;

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public void onTabSelected(MaterialTab materialTab) {
        //viewPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tabframgnet, container, false);


        return view;
    }





    @SuppressLint("ResourceAsColor")
    public void onViewCreated(View view, Bundle savedInstanceState) {
        tabHost = (MaterialTabHost) view.findViewById(R.id.materialTabHost);
        tabHost.setBorderReferenceColor(1, R.color.red_a100);
        tabHost.setCustomBackground(R.drawable.tab_host_bottom_line);
        // insert all tabs from pagerAdapter data
        for (int i = 0; i < 4; i++) {
            final MaterialTab t = tabHost.createCustomTextTab(R.layout.item_tab, "XOS" + i, false).setTabListener(this);
            tabHost.addTab(t);
         /*   .createInteractiveTab(mMyAdapter.getPageTitle(i))
                            .setTabListener(this)*/

        }

    }
}
