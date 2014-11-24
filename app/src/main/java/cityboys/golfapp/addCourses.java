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
    public static void add(ArrayAdapter<String> currentAdapter, String identifier) {
        // Finna golfvelli ef beðið er um það
        if(identifier.equals("courses")) {
            for(int i=0; i<Courses.courseArray.length; i++){
                currentAdapter.add(Courses.courseArray[i].getClubShortName()+" - "+Courses.courseArray[i].getCourseName());
                currentAdapter.notifyDataSetChanged();
            }

        } else if(identifier.equals("clubs")) {
            for(int i=0; i<Clubs.clubArray.length; i++){
                //currentAdapter.add(Clubs.clubArray[i].getClubShortName()+" - "+Clubs.clubArray[i].getClubName());
                currentAdapter.add(Clubs.clubArray[i].getClubName());
                currentAdapter.notifyDataSetChanged();
            }
        }
    }
}
