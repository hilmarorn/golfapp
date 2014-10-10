package cityboys.golfapp;

import android.app.Activity;
import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;


public class profile extends FragmentActivity {

    // Fjöldi síða sem á að bjóða uppá
    private static final int NUM_PAGES = 2;

    // Pager widget, sér um swipe-ið
    private ViewPager myViewpager;

    // Pager adapter sem sér um síðurnar fyrir viewpager
    private PagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Búa til Viewpager og PageAdapter
        myViewpager = (ViewPager)findViewById(R.id.pager);
        myPagerAdapter = new ScreenSlide(getSupportFragmentManager());
        myViewpager.setAdapter(myPagerAdapter);

        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.actionbar);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ScreenSlide extends FragmentStatePagerAdapter {
        public ScreenSlide(FragmentManager myFragment) {
            super(myFragment);
        }

        @Override
        public Fragment getItem(int position) {
            return SlideFragment.create(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
