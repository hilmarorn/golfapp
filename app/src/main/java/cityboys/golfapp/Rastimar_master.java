package cityboys.golfapp;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.res.Configuration;

/*
Notkun: Intent open_ras = new Intent(this, Rastimar_master.class);
        startActivity(open_ras);
Fyrir: ekkert
Eftir: Búið er að búa til nýtt Activity sem inniheldur rástíma skráningu
 */
public class Rastimar_master extends FragmentActivity {

    protected static ActionBar actionBar;
    // Þetta er deafault klúbburinn hjá innskráðum notanda
    protected static String selectedCourse;

    // Allt fyrir fragment-in
    protected final static int NUM_PAGE = 3;         // Fjöldi rástímasíða
    private final String IDENTIFIER = "rastimi"; // Bera kennsl á Activity-ið
    private ViewPager myViewpager;          // Pager widget, sér um swipe-ið á milli fragment-a
    private PagerAdapter myPagerAdapter;    // Pager adapter sem heldur utan um síðurnar fyrir viewpager

    //Fyrir navigation drawer
    private String[] nav_menu_values;
    private DrawerLayout myDrawerLayout;
    private ListView myDrawerList;
    private ActionBarDrawerToggle myDrawerToggle; // Heldur utan hvort nav drawer sér opið/lokað

    /*
    Notkun: Kallað er á þetta fall þegar klasinn er búinn til
    Fyrir: ekkert
    Eftir: Búið er að núllstilla alla hluti sem sýna skal. Þar má nefna navigation drawer
           og tengslin milli navigation drawer og Action Bar
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rastima_master);

        // Búa til Viewpager og PageAdapter. Því næst tengja þá saman
        myViewpager = (ViewPager)findViewById(R.id.pager);
        myPagerAdapter = new ScreenSlide(getSupportFragmentManager(), IDENTIFIER, NUM_PAGE);
        myViewpager.setAdapter(myPagerAdapter);

        /////////////////////////////
        // Fyrir tabs í Action Bar //
        /////////////////////////////
        actionBar = getActionBar();
        // Sér um að birta tabs í ActionBar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // Breyta um lit á tabs
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#2b574e")));

        // Tab listener
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                // show the given tab
                myViewpager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // hide the given tab
            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // probably ignore this event
            }
        };

        // Búin til 3 tabs og festum listener við þá
        for (int i = 0; i < NUM_PAGE; i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(myPagerAdapter.getPageTitle(i))
                            .setTabListener(tabListener));
        }

        // Fyrir touch á tabs
        myViewpager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        actionBar.setSelectedNavigationItem(position);
                    }
                });

        /////////////////////////////
        // Fyrir navigation drawer //
        /////////////////////////////
        // Núllstilla nav drawer
        nav_menu_values = getResources().getStringArray(R.array.nav_drawer);
        myDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        myDrawerList = (ListView)findViewById(R.id.left_drawer);

        // Setja adapter á listann sem á að birtast í navigation drawer
        myDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, nav_menu_values));
        // onClickListener fyrir navigation drawer
        myDrawerList.setOnItemClickListener(new NavMenuItemClickListener());

        // Partur af því að gera myndtáknið ásmellanlegt, þannig að það opnar/lokar navigation drawer
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        // Fela myndtákn í Action Bar
        actionBar.setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));

        // Fall sem hlustar eftir því hvort ýtt hafi verið á myndtákn í Action Bar
        myDrawerToggle = new ActionBarDrawerToggle(this, myDrawerLayout, R.drawable.ic_drawer,
                R.string.drawer_open, R.string.drawer_closed) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpen(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        myDrawerLayout.setDrawerListener(myDrawerToggle);

        // Hér er verið að tjékka hvort verið sé að kalla frá skraTima.java og ef svo er
        // velja rétta blaðsíðu.
        Intent previousActivity = getIntent();
        String pageNumber = previousActivity.getStringExtra("pageNumber");

        if(pageNumber != null) {
            actionBar.setSelectedNavigationItem(Integer.valueOf(pageNumber));
        } else {
            for (int i = 0; i < Courses.courseArray.length; i++) {
                if (User.getGolfClub().equals(Courses.courseArray[i].getClubName()))
                    selectedCourse = Courses.courseArray[i].getClubShortName() + " - " + Courses.courseArray[i].getCourseName();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Ef ýtt er á myndtákn í Action Bar skilar þetta true
        if (myDrawerToggle.onOptionsItemSelected(item)) return true;

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

/*
Allt sem tengist Navigation Menu
*/
    /*
    Notkun: myListView.setOnItemClickListener(NavMenuItemClickListener())
    Fyrir: myListView verður að vera af taginu ListView
    Eftir: búið er að tengja onClickListener við myListView
     */
    private class NavMenuItemClickListener implements ListView.OnItemClickListener {
        @Override
        /*
        Notkun: Kallað er á þetta þegar eintak er búið til af klasanum NavMenuClickListener,
                þar sem position er staðsetning á element-inu sem ýtt var á
        Fyrir: ekkert
        Eftir: búið er að finna út á hvaða element var ýtt á
         */
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /*
    Notkun: selectItem(x)
    Fyrir: x er af taginu int
    Eftir: Búið er að finna út á hvaða element var ýtt á
     */
    private void selectItem(int position) {
        // Hér er fundið hvað var ýtt á
        switch(position) {
            case 0:
                Intent open_profile = new Intent(this, profile.class);
                startActivity(open_profile);
                finish();
                break;
            case 1:
                Intent open_skorkort = new Intent(this, Skorkort.class);
                startActivity(open_skorkort);
                finish();
                break;
            case 2:
                // Gerum ekkert, erum í þessum skjá
                break;
            case 4:
                Intent open_login = new Intent(this, LoginActivity.class);
                startActivity(open_login);
                User.clearUserData();
                finish();
                break;
        }
        // Ljóma element-ið sem ýtt var á
        myDrawerList.setItemChecked(position, true);
        myDrawerLayout.closeDrawer(myDrawerList);
        // Taka ljómann af element-inu
        myDrawerList.setItemChecked(position, false);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        myDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        myDrawerToggle.onConfigurationChanged(newConfig);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = myDrawerLayout.isDrawerOpen(myDrawerList);
        // Þetta getur falið icon þegar drawer er opin
        // menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
}