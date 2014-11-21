package cityboys.golfapp;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/*
 * Created by Busli on 11.11.2014.
 */
public class RastimaLeit {

    private static Spinner dates_leit, course_leit, start_time, end_time;
    private static ArrayAdapter<String> dateAdapter, courseAdapter, startAdapter, endAdapter;
    private static String selectedRastimaDate, selectedCourse, selectedStartTime,
                            fjoldiTiladSkra, selectedEndTime;
    private static String string_times[] = new String[] {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00"};
    private static EditText fjoldiFraNotanda;

    /*
    Notkun: Rastimar.init()
    Fyrir: view er view-ið sem verið er að vinna með
    Eftir: Búið er að gera Rastímar fragment-ið
     */
    public static void init(View view) {
        fjoldiFraNotanda = (EditText) view.findViewById(R.id.rl_fjoldiFraNotanda);
        initScreen(view);
        makeButtonListener(view);
    }

    /*
    Notkun: initScreen(view)
    Fyrir: view er view-ið sem verið er að vinna með
    Eftir: búið er að setja gögn inní öll element á skjánum
     */
    public static void initScreen(View view) {

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
        makeDates.loadDates(dateAdapter);
        addCourses.add(courseAdapter, view, "courses");
        addTime();

        // onClickListeners fyrir Spinner-ana
        dates_leit.setOnItemSelectedListener(new onSpinnerSelected());
        course_leit.setOnItemSelectedListener(new onSpinnerSelected());
        start_time.setOnItemSelectedListener(new onSpinnerSelected());
        end_time.setOnItemSelectedListener(new onSpinnerSelected());
    }

    /*
    Notkun: new onSpinnerSelected();
    Fyrir: ekkert
    Eftir: Búið er að finna út á hvaða element var smellt á í Spinner
     */
    private static class onSpinnerSelected implements AdapterView.OnItemSelectedListener {
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
            }
        }
        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    /*
    Notkun: makeButtonListener(view)
    Fyrir: ekkert
    Eftir: búið er að finna takkann og festa onClickListener á hann
     */
    private static void makeButtonListener(View view) {
        Button lausir_timar = (Button) view.findViewById(R.id.button_lausir_timar);
        lausir_timar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findAvailableTime(view);
            }
        });
    }

    /*
    Notkun: addTime()
    Fyrir: ekkert
    Eftir: búið er að fylla út rétta tímasetningu í spinner-ana sem kölluðu á fallið
    */
    public static void addTime() {
        // TODO: Gera þetta dynamicly og frá núverandi tíma
        for(String time : string_times) {
            startAdapter.add(time);
            endAdapter.add(time);
            startAdapter.notifyDataSetChanged();
            endAdapter.notifyDataSetChanged();
        }
    }

    /*
    Notkun: þegar ýtt er á takkann "Lausir tímar"
    Fyrir: ekkert
    Eftir: búið er að taka öll gögn frá spinner-unum og flytja yfir í næsta skjá
    */
    public static void findAvailableTime(View view) {
        Intent lausir_timar = new Intent(view.getContext(), LausirTimar.class);
        lausir_timar.putExtra("Date", selectedRastimaDate);
        lausir_timar.putExtra("Course", selectedCourse);
        lausir_timar.putExtra("StartTime", selectedStartTime);
        lausir_timar.putExtra("EndTime", selectedEndTime);

        try{
            fjoldiTiladSkra = fjoldiFraNotanda.getText().toString();
            if(fjoldiTiladSkra.equals("")) fjoldiTiladSkra = "1";
        } catch (Exception e) {
            fjoldiTiladSkra = "1";
        }
        lausir_timar.putExtra("Fjoldi", fjoldiTiladSkra);
        view.getContext().startActivity(lausir_timar);
    }
}
