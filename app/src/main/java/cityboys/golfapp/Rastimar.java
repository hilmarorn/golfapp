package cityboys.golfapp;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * Created by Busli on 11.11.2014.
 */
public class Rastimar {

    private static ListView club_list;    // View-ið fyrir golfvellina
    private static String string_clubs[]; // Fylki sem inniheldur golfvellina
    // Segir til hvort búið sé að gera skjáinn í RástímaYfirlit
    public static boolean firstTimeInScreen;
    private static ArrayAdapter<String> clubAdapter;

    /*
    Notkun: Rastimar.initScreen(view)
    Fyrir: view er view-ið sem verið er að vinna með
    Eftir: Búið er að gera Rastimar fragment-ið
     */
    public static void initScreen(View view) {
        string_clubs = view.getContext().getResources().getStringArray(R.array.clubs);
        firstTimeInScreen = true;
        makeListView(view);
    }

    /*
    Notkun: makeListView(view)
    Fyrir: view er view-ið sem kallað var úr
    Eftir: búið er að gera ListView með golfvöllum í rástíma fragment-inu
    */
    private static void makeListView(View view) {
        // ListView fundið
        club_list = (ListView)view.findViewById(R.id.r_club_list);

        // Glósur um hvernig þetta virkar
        /*
        Define a new Adapter
        First parameter - Context
        Second parameter - Layout for the row
        Third parameter - ID of the TextView to which the data is written
        Forth - the Array of data
        */
        // Adapter fyrir ListView og hann festur við
        clubAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, string_clubs);
        club_list.setAdapter(clubAdapter);

        club_list.setOnItemClickListener(myClickListener);
    }

    private static AdapterView.OnItemClickListener myClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Rastimar_master.selectedClub = (String) adapterView.getItemAtPosition(position);
            Rastimar_master.actionBar.setSelectedNavigationItem(Rastimar_master.NUM_PAGE - 1);
        }
    };
}