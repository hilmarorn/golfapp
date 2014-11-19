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
    public static void add(ArrayAdapter<String> currentAdapter, View view, String identifier) {
        // Finna golfvelli ef beðið er um það
        if(identifier.equals("courses")) {
            String[] string_courses = view.getResources().getStringArray(R.array.courses);
            for(String course : string_courses){
                currentAdapter.add(course);
                currentAdapter.notifyDataSetChanged();
            }
        }

        // Finna listann með Golfklúbbunum
        String[] string_clubs = view.getResources().getStringArray(R.array.clubs);
        // Golfvöllum bætt við
        for(String club : string_clubs) {
            currentAdapter.add(club);
            currentAdapter.notifyDataSetChanged();
        }
    }
}
