package cityboys.rastimar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import cityboys.golfInfo.Courses;
import cityboys.golfapp.R;
import cityboys.skorkort.Skorkort;
import cityboys.network.DatabaseConnection;
import cityboys.user.profile;

/*
Notkun: Intent skraTima = new Intent(this, skraTima.class)
Fyrir: ekkert
Eftir: búið er að ræsa skraTima activity-inu
 */
public class skraTima extends Activity {

    // Breytur fyrir upplýsingarnar sem teknar eru með í skjáinn
    private String date;
    private String course;
    private String time;

    //Fyrir navigation drawer
    private String[] nav_menu_values;
    private DrawerLayout myDrawerLayout;
    private ListView myDrawerList;
    private ActionBarDrawerToggle myDrawerToggle; // Heldur utan hvort nav drawer sér opið/lokað

    private static Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skra_tima);

        myContext = getBaseContext();

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

        // Finnum activity-ið sem kallaði og upplýsingarnar frá því
        Intent previousActivity = getIntent();
        date = previousActivity.getStringExtra("date");
        course = previousActivity.getStringExtra("course");
        time = previousActivity.getStringExtra("time");

        // Skrifum út staðfestingar textann til notanda
        TextView infoText = (TextView)findViewById(R.id.sk_info);
        infoText.setText("Þú ert að fara skrá þig á rástíma "+date+" klukkan "+time+ " á " +course);
    }

    /*
    Notkun: Þegar ýtt er á takkann í skjánum
    Fyrir: ekkert
    Eftir: búið er að skrá notanda á tímann sem hann valdi
     */
    public void stadfestaRastima(View view) {
        // Setja gögnin á rétt format
        String[] arrayDateToSend = date.split("/");
        String dateToSend = arrayDateToSend[2]+"-"+arrayDateToSend[1]+"-"+arrayDateToSend[0];
        // Finna course id
        String[] arrayCourseToSend = course.split("-");
        String courseToSend = arrayCourseToSend[1].trim();
        String course_id = "";
        for(int i = 0; i < Courses.courseArray.length; i++) {
            if(courseToSend.equals(Courses.courseArray[i].getCourseName())) {
                course_id = Courses.courseArray[i].getCourseId();
            }
        }

        // Búum til gagnatengingu til þess að senda rástíma til gagnagrunns
        String insertLink="https://notendur.hi.is/~hoh40/Hugbunadarverkfraedi1/insertStartingTime.php";
        DatabaseConnection mInsertTask = new DatabaseConnection(course_id, dateToSend, time, insertLink, 'i');
        mInsertTask.execute();

        // Búum til nýjan skjá og setjum inn rétta blaðsíðu til að fara á
        Intent rastimar = new Intent(this, Rastimar_master.class);
        rastimar.putExtra("pageNumber", "2");
        startActivity(rastimar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.skra_tima, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
                Intent open_rastimar = new Intent(this, Rastimar_master.class);
                startActivity(open_rastimar);
                finish();
                break;
        }
        // Ljóma element-ið sem ýtt var á
        myDrawerList.setItemChecked(position, true);
        myDrawerLayout.closeDrawer(myDrawerList);
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
