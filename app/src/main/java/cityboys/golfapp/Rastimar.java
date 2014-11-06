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
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.content.res.Configuration;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;

/*
Notkun: Intent open_ras = new Intent(this, Rastimar.class);
        startActivity(open_ras);
Fyrir: ekkert
Eftir: Búið er að búa til nýtt Activity sem inniheldur rástíma skráningu
 */
public class Rastimar extends FragmentActivity {

    // TO DO
    // Gera private final string identifier við tækifæri
    // Sameina kannski makeListView og makeSpinners þar sem það er fyrir sama skjáinn


    private ActionBar actionBar;

    // Strengja fylki fyrir spinner-a
    private String string_courses[] = new String[] {"Golfklúbbur Reykjavíkur", "Golfklúbbur Akureyrar",
            "Golfklúbburinn Leynir", "Golfklúbburinn á Selfossi"};
    private String string_times[] = new String[] {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00"};

    // Fjöldi rástímasíða
    private final int NUM_PAGE = 3;
    // Pager widget, sér um swipe-ið á milli fragment-a
    private ViewPager myViewpager;
    // Pager adapter sem heldur utan um síðurnar fyrir viewpager
    private PagerAdapter myPagerAdapter;

    //Fyrir navigation drawer
    private String[] nav_menu_values;
    private DrawerLayout myDrawerLayout;
    private ListView myDrawerList;
    private ActionBarDrawerToggle myDrawerToggle; // Heldur utan hvort nav drawer sér opið/lokað

    // Breytur fyrir Spinners
    private Spinner spinner_dates;
    private String selectedDate;
    private String course;
    private ArrayAdapter<String> dates_adapter;

    // ListView fyrir Rástímaskjá
    private ListView course_list;

    // Fyrir Rástímayfirlit
    private LinkedHashMap<String, HeaderInfo> myTimes = new LinkedHashMap<String, HeaderInfo>();
    private ArrayList<HeaderInfo> timeList = new ArrayList<HeaderInfo>();
    private MyListAdapter listAdapter;
    private ExpandableListView myList;
    private Spinner dates_yfirlit, courses_yfirlit;
    private ArrayAdapter<String> dateYfirlitAdapter, courseYfirlitAdapter;

    //Allt fyrir Rástímaleit
    private Spinner dates_leit, course_leit, start_time, end_time;
    private ArrayAdapter<String> dateAdapter, courseAdapter, startAdapter, endAdapter;
    private String selectedRastimaDate, selectedCourse, selectedStartTime, selectedEndTime;

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
        // Hér vantar að setja rétt drasl inn í fallið, þarf að hugsa þetta aðeins
        myPagerAdapter = new ScreenSlide(getSupportFragmentManager(), "rastimi", NUM_PAGE);
        myViewpager.setAdapter(myPagerAdapter);

        /////////////////////////////
        // Fyrir tabs í Action Bar //
        /////////////////////////////
        actionBar = getActionBar();
        // Specify that tabs should be displayed in the action bar.
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
                        getActionBar().setSelectedNavigationItem(position);
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
    }

    // Notkun: makeListView(view)
    // Fyrir: view er view-ið sem kallað var úr
    // Eftir: búið er að gera ListView með golfvöllum í rástíma fragment-inu
    public void makeListView(View view) {
        /*
        Til að lita ListView þarf ég að fá position á lista elementunum

        if(childPosition%2 == 0) {
            view.setBackgroundColor(Color.GRAY);
        } else {
            view.setBackgroundColor(Color.WHITE);
        }
         */
        // ListView fundið
        course_list = (ListView)view.findViewById(R.id.myList);

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, string_courses);
        course_list.setAdapter(adapter);

        course_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int itemPosition = position;
                String itemValue = (String) course_list.getItemAtPosition(position);

                // TO DO
                // Köllum á skjáinn sem heldur utan um rástímayfirlitið
                // Hér vantar að taka upplýsingarnar með
            }
        });
    }

    /*
    Notkun: makeSpinners(view)
    Fyrir: view er view-ið sem kallað er úr
    Eftir: búið er að gera spinner fyrir rastima fragment-ið
     */
    public void makeSpinners(View view) {
        // Finna spinner
        spinner_dates = (Spinner) view.findViewById(R.id.spinner_dates);

        // ArrayAdapter fyrir spinner
        // Hér þarf líklegast að nota CursorAdapter þar sem við erum með dataquery
        dates_adapter = new ArrayAdapter<String>(view.getContext(),
            android.R.layout.simple_spinner_item, android.R.id.text1);

        // Setjum default layout á Spinner-ana
        dates_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //course_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Festa adapter við spinner
        spinner_dates.setAdapter(dates_adapter);

        //Setja inn dagsetningar í Spinner
        makeDates("rastimar");

        // onClickListeners fyrir Spinner
        spinner_dates.setOnItemSelectedListener(new onSpinnerSelected());
    }

    /*
    Notkun: makeExpandableListView(view)
    Fyrir: view er view-ið sem kallað var úr
    Eftir: búið er að gera ExpandableListView fyrir rástímayfirlit fragment-ið
     */
    public void makeExpandableListView(View view) {
        //Just add some data to start with
        loadData();

        // Finna ExpandableListView-ið
        myList = (ExpandableListView) view.findViewById(R.id.myList);
        // Búa til adapter fyrir ExpandableListView-ið og setja inn gögnin
        listAdapter = new MyListAdapter(view.getContext(), timeList);
        myList.setAdapter(listAdapter);
        // Listener fyrir börnin
        myList.setOnChildClickListener(myListItemClicked);
        // listener fyrir master tréið
        myList.setOnGroupClickListener(myListGroupClicked);
    }

    /*
    Notkun: loadData()
    Fyrir: ekkert
    Eftir: búið er að setja inn gögn í master tréið og börn master trésins
     */
    private void loadData(){

        addProduct("10:00","Guðmundur Jónsson");
        addProduct("10:00","Karl Áki Gústafsson");
        addProduct("10:00","");
        addProduct("10:00","");

        addProduct("11:00","Jón Jónson");
        addProduct("11:00","Tryggvi Einarsson");
        addProduct("11:00","Bjarki Ármannsson");
        addProduct("11:00","");

        addProduct("12:00","Gréta Magnúsdóttir");
        addProduct("12:00","Margrét Ingvarsdóttir");
        addProduct("12:00","Gerður Gunnarsdóttir");
        addProduct("12:00","Hildur Valdimarsdóttir");

        addProduct("13:00","");
        addProduct("13:00","");
        addProduct("13:00","");
        addProduct("13:00","");

    }

    // Listener fyrir börnin
    private ExpandableListView.OnChildClickListener myListItemClicked =  new ExpandableListView.OnChildClickListener() {

        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {

            // Fá nafn master trésins
            HeaderInfo headerInfo = timeList.get(groupPosition);
            // Fá allar upplýsingar frá börnunum
            DetailInfo detailInfo =  headerInfo.getTimeList().get(childPosition);
            // Kóðinn fyrir neðan er geymdur hér til að muna útfærsluatriði
            /*
            Intent skra_tima = new Intent(getBaseContext(), skraTima.class);
            skra_tima.putExtra("date", date);
            skra_tima.putExtra("course", course);
            skra_tima.putExtra("time", headerInfo.getName());
            startActivity(skra_tima);
            */
            return false;
        }

    };

    // Listener fyrir master tréin
    private ExpandableListView.OnGroupClickListener myListGroupClicked =  new ExpandableListView.OnGroupClickListener() {

        public boolean onGroupClick(ExpandableListView parent, View v,
                                    int groupPosition, long id) {

            // Fá nafn master trésins
            HeaderInfo headerInfo = timeList.get(groupPosition);

            return false;
        }

    };

    /*
    Notkun: addProduct()
    Fyrir: ekkert
    Eftir: búið er að setja allar upplýsingar inn í master tréin og börnin þeirra
     */
    private int addProduct(String time, String player_name){

        int groupPosition = 0;

        // Gá í hash mappinu hvort master tréið sé til
        HeaderInfo headerInfo = myTimes.get(time);
        // Bæta við ef ekki til
        if(headerInfo == null){
            headerInfo = new HeaderInfo();
            headerInfo.setName(time);
            myTimes.put(time, headerInfo);
            timeList.add(headerInfo);
        }

        // Finna rétt börn fyrir master tréið
        ArrayList<DetailInfo> lst_players = headerInfo.getTimeList();
        //size of the children list
        int listSize = lst_players.size();
        //add to the counter
        listSize++;

        // Búa til nýtt barn ef ekki til
        DetailInfo detailInfo = new DetailInfo();
        //detailInfo.setSequence(String.valueOf(listSize));
        detailInfo.setName(player_name);
        lst_players.add(detailInfo);
        headerInfo.setTimeList(lst_players);

        // Finna rétta staðsetningu hópsins
        groupPosition = timeList.indexOf(headerInfo);
        return groupPosition;
    }

    // Notkun: makeDates();
    // Fyrir: ekkert
    // Eftir: Búið er að setja dagsetningar inn í Spinner
    private void makeDates(String whatSpinner) {

        // Finna dagsetninguna í dag og format-a hana rétt
        Calendar currentDate = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Hér er fundið út hvaða fragment kallaði og rétt aðgerð framkvæmd
        if (whatSpinner.equals("rastimar")) {
            for (int i = 0; i < 10; i++) {
                dates_adapter.add(dateFormat.format(currentDate.getTime()));
                // Hér dagurinn hækkaður um einn
                currentDate.add(Calendar.DAY_OF_MONTH, 1);
                dates_adapter.notifyDataSetChanged();
            }

        } else if (whatSpinner.equals("rastimaleit")) {
            for (int i = 0; i < 10; i++) {
                dateAdapter.add(dateFormat.format(currentDate.getTime()));
                currentDate.add(Calendar.DAY_OF_MONTH, 1);
                dateAdapter.notifyDataSetChanged();
            }

        } else if (whatSpinner.equals("yfirlit")) {
            for (int i = 0; i < 10; i++) {
                dateYfirlitAdapter.add(dateFormat.format(currentDate.getTime()));
                currentDate.add(Calendar.DAY_OF_MONTH, 1);
                dateYfirlitAdapter.notifyDataSetChanged();
            }

        }

    }

    // Notkun: new onSpinnerSelected();
    // Fyrir: ekkert
    // Eftir: Búið er að finna út á hvaða element var smellt á í Spinner
    private class onSpinnerSelected implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // Fundið út á hvaða spinner var smellt og rétt aðgerð framkvæmd
            switch(parent.getId()) {
                case R.id.dates:
                    selectedRastimaDate = parent.getItemAtPosition(pos).toString();
                    break;
                case R.id.courses:
                    selectedCourse = parent.getItemAtPosition(pos).toString();
                    break;
                case R.id.spinner_start_time:
                    selectedStartTime = parent.getItemAtPosition(pos).toString();
                    break;
                case R.id.spinner_end_time:
                    selectedEndTime = parent.getItemAtPosition(pos).toString();
                    break;
                case R.id.spinner_dates:
                    selectedDate = parent.getItemAtPosition(pos).toString();
                    break;
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    // Notkun findTime();
    // Fyrir: ekkert
    // Eftir: Búið er að byrja nýtt Activity og fært er á milli dagsetninguna og völlinn sem
    //        sem notandi valdi
    public void findTime(View view) {
        if(selectedDate != "" && course != "") {
            Intent intent = new Intent(this, finnaRastima.class);
            intent.putExtra("date", selectedDate);
            intent.putExtra("course", course);
            startActivity(intent);
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
        if (myDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    Notkun: þegar ýtt er á takkann "Lausir tímar"
    Fyrir: ekkert
    Eftir: búið er að taka öll gögn frá spinner-unum og flytja yfir í næsta skjá
     */
    public void findAvailableTime(View view) {
        Intent lausir_timar = new Intent(this, LausirTimar.class);
        lausir_timar.putExtra("Date", selectedRastimaDate);
        lausir_timar.putExtra("Course", selectedCourse);
        lausir_timar.putExtra("StartTime", selectedStartTime);
        lausir_timar.putExtra("EndTime", selectedEndTime);
        startActivity(lausir_timar);
    }

    /*
    Notkun: makeSpinnersForRastimaLeit(view)
    Fyrir: ekkert
    Eftir: búið er að búa til alla spinner-ana fyrir rástímaleit skjáinn
     */
    public void makeSpinnersForRastimaLeit(View view) {

        // Finnna alla Spinner-ana
        dates_leit = (Spinner) view.findViewById(R.id.dates);
        course_leit = (Spinner) view.findViewById(R.id.courses);
        start_time = (Spinner) view.findViewById(R.id.spinner_start_time);
        end_time = (Spinner) view.findViewById(R.id.spinner_end_time);

        // ArrayAdapter fyrir spinner-ana
        dateAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1);
        courseAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1);
        startAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1);
        endAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1);

        // Setjum default layout á Spinner-ana
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Festa adapter-ana við spinner-ana
        dates_leit.setAdapter(dateAdapter);
        course_leit.setAdapter(courseAdapter);
        start_time.setAdapter(startAdapter);
        end_time.setAdapter(endAdapter);

        //Setja inn upplýsingar í spinner-ana
        makeDates("rastimaleit");
        addCourses("rastimaleit");
        addTime();

        // onClickListeners fyrir Spinner-ana
        dates_leit.setOnItemSelectedListener(new onSpinnerSelected());
        course_leit.setOnItemSelectedListener(new onSpinnerSelected());
        start_time.setOnItemSelectedListener(new onSpinnerSelected());
        end_time.setOnItemSelectedListener(new onSpinnerSelected());
    }

    /*
    Notkun: addCourses(whatSpinner)
    Fyrir: whatSpinner er strengur
    Eftir: búið er að finna út hver kallaði á fallið og fyllt er í þann sem kallaði
     */
    public void addCourses(String whatSpinner) {
        // Fundið hver er réttur og rétt aðgerð framkvæmd
        if(whatSpinner == "rastimaleit") {
            for (int i = 0; i < string_courses.length; i++) {
                courseAdapter.add(string_courses[i]);
                courseAdapter.notifyDataSetChanged();
            }
        } else if(whatSpinner == "yfirlit") {
            for (int i = 0; i < string_courses.length; i++) {
                courseYfirlitAdapter.add(string_courses[i]);
                courseYfirlitAdapter.notifyDataSetChanged();
            }
        }
    }

    /*
    Notkun: addTime()
    Fyrir: ekkert
    Eftir: búið er að fylla út rétta tímasetningu í spinner-ana sem kölluðu á fallið
     */
    public void addTime() {
        for(int i = 0; i < string_times.length; i++) {
            startAdapter.add(string_times[i]);
            endAdapter.add(string_times[i]);
            startAdapter.notifyDataSetChanged();
            endAdapter.notifyDataSetChanged();
        }
    }

    /*
    Notkun: makeSpinnersForYfirlit(view)
    Fyrir: view er view-ið frá skjánum sem kallaði
    Eftir: búið er að búa til spinner-a fyrir rástíma yfirlit
     */
    public void makeSpinnersForYfirlit(View view) {
        // Finna spinner-a
        dates_yfirlit = (Spinner)view.findViewById(R.id.spinner_date_yfirlit);
        courses_yfirlit = (Spinner)view.findViewById(R.id.spinner_course_yfirlit);

        // Setja adapter á þá
        dateYfirlitAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1);
        courseYfirlitAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1);

        // Sét fyrirfram skilgreint útlit á þá
        dateYfirlitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseYfirlitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dates_yfirlit.setAdapter(dateYfirlitAdapter);
        courses_yfirlit.setAdapter(courseYfirlitAdapter);

        // Fylla inní þá
        makeDates("yfirlit");
        addCourses("yfirlit");
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
                Intent open_rastimar = new Intent(this, Rastimar.class);
                startActivity(open_rastimar);
                finish();
                break;
        }
        // Ljóma element-ið sem ýtt var á
        myDrawerList.setItemChecked(position, true);

        myDrawerLayout.closeDrawer(myDrawerList);
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