package cityboys.skorkort;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.res.Configuration;
import android.widget.Spinner;
import android.widget.TextView;

import cityboys.golfapp.R;
import cityboys.rastimar.Rastimar_master;
import cityboys.user.LoginActivity;
import cityboys.user.User;
import cityboys.user.profile;

/*
Notkun: Intent open_skor = new Intent(this, Skorkort.class);
        startActivity(open_skor);
Fyrir: ekkert
Eftir: Búið er að búa til nýtt Activity sem inniheldur skorkort
 */
public class SpilaVoll extends Activity {

    //Fyrir navigation drawer
    private String[] nav_menu_values;
    private DrawerLayout myDrawerLayout;
    private ListView myDrawerList;
    // Heldur utan hvort navigation drawer sér opið eða lokað
    private ActionBarDrawerToggle myDrawerToggle;

    /*
    Notkun: Kallað er á þetta fall þegar klasinn er búinn til
    Fyrir: ekkert
    Eftir: Búið er að núllstilla alla hluti sem sýna skal. Þar má nefna navigation drawer
           og tengslin milli navigation drawer og Action Bar
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spila_voll);

        TextView kylfingur = (TextView) findViewById(R.id.spv_id_kylfingur);
        kylfingur.setText(User.getFullName());

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

        /*Möguleikum bætt í spinnera*/
        ArrayAdapter<CharSequence> teigarArrayAdapter= ArrayAdapter.createFromResource(this,R.array.teigar,android.R.layout.simple_spinner_dropdown_item);
        Spinner teigarspinner = (Spinner) findViewById(R.id.spv_teigar_spinner);
        teigarspinner.setAdapter(teigarArrayAdapter);

        ArrayAdapter<CharSequence> dagsArrayAdapter= ArrayAdapter.createFromResource(this,R.array.dagsetningar,android.R.layout.simple_spinner_dropdown_item);
        Spinner dagsspinner = (Spinner) findViewById(R.id.spv_dags_spinner);
        dagsspinner.setAdapter(dagsArrayAdapter);

        ArrayAdapter<CharSequence> fjHolaArrayAdapter= ArrayAdapter.createFromResource(this,R.array.fjoldihola,android.R.layout.simple_spinner_dropdown_item);
        Spinner fjhola = (Spinner) findViewById(R.id.spv_fjhola_spinner);
        fjhola.setAdapter(fjHolaArrayAdapter);

        ArrayAdapter<CharSequence> vellirArrayAdapter= ArrayAdapter.createFromResource(this,R.array.vellir,android.R.layout.simple_spinner_dropdown_item);
        Spinner vellir = (Spinner) findViewById(R.id.spv_vollur_spinner);
        vellir.setAdapter(vellirArrayAdapter);


    }

    // Býr til nýtt activity þegar ýtt er á hnapp
    public void Saekjaskorkort(View view) {
        Intent saekja_skorkort = new Intent(this, SkorkortVollur.class);
        startActivity(saekja_skorkort);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Ef ýtt er á myndtákn í Action Bar skilar þetta tru
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
                Intent open_rastimar = new Intent(this, Rastimar_master.class);
                startActivity(open_rastimar);
                finish();
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