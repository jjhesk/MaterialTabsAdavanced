package net.giancarlofusiello.materialtabslibrary;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment implements MaterialTabListener {
    private TextView tt;
    private MaterialTabHost tabHost;

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment2, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        //buffer testing
        final String ht = "==0======0====";
        tt = (TextView) view.findViewById(R.id.contentbuffer);
        tt.setText("=== new text in finefoinei nse ===");


        tabHost = (MaterialTabHost) view.findViewById(R.id.materialTabHost);
        tabHost.setBorderReferenceColor(1, R.color.red_a100);
        tabHost.setCustomBackground(R.drawable.tab_host_bottom_line);
        tabHost.setOnlySetectOneTab(true);
        // insert all tabs from pagerAdapter data
        for (int i = 0; i < 8; i++) {
            final MaterialTab t = tabHost.createCustomTextTab(R.layout.item_tab, "XOS" + i, false).setTabListener(this);
            tabHost.addTab(t);
         /*   .createInteractiveTab(mMyAdapter.getPageTitle(i))
                            .setTabListener(this)*/

        }

        e.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        for (int r = 0; r < 3000; r++) {
            final int g = (int) Math.round(Math.random() * 11f);
            String l1 = ht.substring(0, g);
            String l2 = ht.substring(g, ht.length());
            addLine(l1 + "o" + l2);
        }
        // showD();
        //   hideD();
    }

    final Handler e = new Handler();
    ProgressDialog pd;

    private void showD() {
        pd = new ProgressDialog(getActivity());
        pd.setMessage("building content...");
        pd.setIndeterminate(true);
        pd.setInverseBackgroundForced(true);
        pd.show();
    }

    private void hideD() {
        if (pd != null) {
            if (pd.isShowing()) pd.dismiss();
        }
    }

    private void addLine(String e) {
        tt.setText(tt.getText().toString() + "\n" + e);
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
}
