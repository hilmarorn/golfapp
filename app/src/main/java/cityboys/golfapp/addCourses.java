package cityboys.golfapp;

import android.view.View;
import android.widget.ArrayAdapter;

/**
 * Created by Busli on 12.11.2014.
 */
public class addCourses {

    private static String string_courses[];

    /*
    Notkun: addCourses(whatSpinner)
    Fyrir: whatSpinner er strengur
    Eftir: búið er að finna út hver kallaði á fallið og fyllt er í þann sem kallaði
    */
    public static void addCourses(ArrayAdapter<String> currentAdapter, View view) {
        string_courses = view.getResources().getStringArray(R.array.courses);
        for (int i = 0; i < string_courses.length; i++) {
            currentAdapter.add(string_courses[i]);
            currentAdapter.notifyDataSetChanged();
        }
    }
}
