package cityboys.golfapp;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
/*
Þarf að refactor-a, klasinn er því ekki í notkun sem stendur
 */
public class finnaRastima extends Activity {

    private LinkedHashMap<String, HeaderInfo> myTimes = new LinkedHashMap<String, HeaderInfo>();
    private ArrayList<HeaderInfo> timeList = new ArrayList<HeaderInfo>();

    private MyListAdapter listAdapter;
    private ExpandableListView myList;

    // Til að taka hluti á milli skjáa
    private String date;
    private String course;

    //Fyrir navigation drawer
    private String[] nav_menu_values;
    private DrawerLayout myDrawerLayout;
    private ListView myDrawerList;
    private ActionBarDrawerToggle myDrawerToggle; // Heldur utan hvort nav drawer sér opið/lokað

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rastima_yfirlit);

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
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        // Fela myndtákn í Action Bar
        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));

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

        // Ná í upplýsingar úr fyrri skjá
        Intent previousActivity = getIntent();
        date = previousActivity.getStringExtra("date");
        course = previousActivity.getStringExtra("course");

        TextView header = (TextView)findViewById(R.id.textView1);
        header.setText(date);

        //Just add some data to start with
        loadData();

        //get reference to the ExpandableListView
        myList = (ExpandableListView) findViewById(R.id.myList);
        //create the adapter by passing your ArrayList data
        listAdapter = new MyListAdapter(this, timeList);
        //attach the adapter to the list
        myList.setAdapter(listAdapter);

        //expand all Groups
        //expandAll();

        //listener for child row click
        myList.setOnChildClickListener(myListItemClicked);
        //listener for group heading click
        myList.setOnGroupClickListener(myListGroupClicked);


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

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.collapseGroup(i);
        }
    }

    //load some initial data into out list
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

    //our child listener
    private OnChildClickListener myListItemClicked =  new OnChildClickListener() {

        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {

            //get the group header
            HeaderInfo headerInfo = timeList.get(groupPosition);
            //get the child info
            DetailInfo detailInfo =  headerInfo.getTimeList().get(childPosition);
            //display it or do something with it
            /*Toast.makeText(getBaseContext(), "Clicked on Detail " + headerInfo.getName()
                    + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();*/
            Intent skra_tima = new Intent(getBaseContext(), skraTima.class);
            skra_tima.putExtra("date", date);
            skra_tima.putExtra("course", course);
            skra_tima.putExtra("time", headerInfo.getName());
            startActivity(skra_tima);
            return false;
        }

    };

    //our group listener
    private OnGroupClickListener myListGroupClicked =  new OnGroupClickListener() {

        public boolean onGroupClick(ExpandableListView parent, View v,
                                    int groupPosition, long id) {

            //get the group header
            HeaderInfo headerInfo = timeList.get(groupPosition);
            //display it or do something with it
            /*Toast.makeText(getBaseContext(), "Child on Header " + headerInfo.getName(),
                    Toast.LENGTH_LONG).show();*/

            return false;
        }

    };

    //here we maintain our products in various departments
    private int addProduct(String time, String player_name){

        int groupPosition = 0;

        //check the hash map if the group already exists
        HeaderInfo headerInfo = myTimes.get(time);
        //add the group if doesn't exists
        if(headerInfo == null){
            headerInfo = new HeaderInfo();
            headerInfo.setName(time);
            myTimes.put(time, headerInfo);
            timeList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<DetailInfo> lst_players = headerInfo.getTimeList();
        //size of the children list
        int listSize = lst_players.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        DetailInfo detailInfo = new DetailInfo();
        //detailInfo.setSequence(String.valueOf(listSize));
        detailInfo.setName(player_name);
        lst_players.add(detailInfo);
        headerInfo.setTimeList(lst_players);

        //find the group position inside the list
        groupPosition = timeList.indexOf(headerInfo);
        return groupPosition;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
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