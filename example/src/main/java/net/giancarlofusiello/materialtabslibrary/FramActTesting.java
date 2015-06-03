package net.giancarlofusiello.materialtabslibrary;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.Objects;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by hesk on 6/3/2015.
 */
public class FramActTesting<T extends Fragment> extends AppCompatActivity {


    private FrameLayout frame;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;
    private FragmentManager mFragmentManager;
    private MyAdapter mMyAdapter;
    private Toolbar toolbar;
    private Fragment Now;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fraact);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        frame = (FrameLayout) findViewById(R.id.fragment_now_open);


        ff().replace(R.id.fragment_now_open, new Fragment1())
                .addToBackStack(null).commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainfragments, menu);
        return true;
    }

    private FragmentTransaction ff() {
        return getFragmentManager().beginTransaction();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.switchfragment1) {
            ff().replace(R.id.fragment_now_open, new Fragment1())
                    .addToBackStack(null).commit();
            return true;
        }

        if (id == R.id.switchfragment2) {
            ff().replace(R.id.fragment_now_open, new Fragment2())
                    .addToBackStack(null).commit();
            return true;
        }

        if (id == R.id.switchfragment3) {
            ff().replace(R.id.fragment_now_open, new Fragment3())
                    .addToBackStack(null).commit();
            return true;
        }

        if (id == R.id.activityviews) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
