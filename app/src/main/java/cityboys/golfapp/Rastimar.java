package cityboys.golfapp;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

/**
 * Created by Busli on 11.11.2014.
 */
public class Rastimar {

    private static Spinner spinner_dates;               // Spinner fyrir dagsetningar
    private static String selectedDate;                 // Valin dagsetning
    private static ArrayAdapter<String> dates_adapter;  // Adapter fyrir listann í Spinner
    private static ListView course_list;                // View-ið fyrir golfvellina
    private static String string_courses[];             // Fylki sem inniheldur golfvellina

    public static void makeRastimarScreen(View view) {
        string_courses = view.getContext().getResources().getStringArray(R.array.courses);
        makeSpinners(view);
        makeListView(view);
    }

    /*
    Notkun: makeSpinners(view)
    Fyrir: view er view-ið sem kallað er úr
    Eftir: búið er að gera spinner fyrir rastima fragment-ið
    */
    public static void makeSpinners(View view) {
        // Finna Spinner
        spinner_dates = (Spinner) view.findViewById(R.id.spinner_dates);

        // ArrayAdapter fyrir spinner
        // Hér þarf líklegast að nota CursorAdapter þar sem við erum með dataquery
        dates_adapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1);

        // Setjum default layout á Spinner-ana
        dates_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Festa adapter við spinner
        spinner_dates.setAdapter(dates_adapter);

        //Setja inn dagsetningar í Spinner
        makeDates.makeDates(dates_adapter);

        // onClickListeners fyrir Spinner
        spinner_dates.setOnItemSelectedListener(new onSpinnerSelected());
    }

    // Notkun: makeListView(view)
    // Fyrir: view er view-ið sem kallað var úr
    // Eftir: búið er að gera ListView með golfvöllum í rástíma fragment-inu
    public static void makeListView(View view) {
        /*
        TODO
        Gera þetta clickable, taka að sem ýtt var á og lita listann
         */

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

    // Notkun: new onSpinnerSelected();
    // Fyrir: ekkert
    // Eftir: Búið er að vinna úr því þegar þrýst var á element í Spinner
    private static class onSpinnerSelected implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            selectedDate = parent.getItemAtPosition(pos).toString();
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }
}