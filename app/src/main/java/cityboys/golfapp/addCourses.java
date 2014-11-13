package cityboys.golfapp;

import android.view.View;
import android.widget.ArrayAdapter;

/*
 * Created by Busli on 12.11.2014.
 */
public class addCourses {
    /*
    Notkun: addCourses.add(currentAdapter, view)
    Fyrir: currentAdapter er af taginu ArrayAdapter<String> og view er view-ið sem kallað er úr
    Eftir: búið er að bæta golfvöllunum við currentAdapter
    */
    public static void add(ArrayAdapter<String> currentAdapter, View view) {
        // Finna listann með golfvellina
        String[] string_courses = view.getResources().getStringArray(R.array.courses);
        // Golfvöllum bætt við
        for(String course : string_courses) {
            currentAdapter.add(course);
            currentAdapter.notifyDataSetChanged();
        }
    }
}
