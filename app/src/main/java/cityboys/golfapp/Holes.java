package cityboys.golfapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by hilmarhergeirsson on 26/11/14.
 */
public class Holes {

    /*private final Hole[] holeArray;

    public static void initCourses(String courseData) {
        //set data from database as Json and assign to variables

        try {
            JSONArray Jarray = new JSONArray(courseData);

            holeArray = new Hole[Jarray.length()];

            for(int i=0;i<Jarray.length();i++)
            {
                JSONObject Jasonobject = null;
                Jasonobject = Jarray.getJSONObject(i);

                //assign variables to the course
                String course_name = Jasonobject.getString("course_name");
                String club_name = Jasonobject.getString("club_name");
                String short_club_name = Jasonobject.getString("short_club_name");
                String course_id = Jasonobject.getString("course_id");

                courseArray[i] = new Course(course_name,club_name,short_club_name, course_id);

            }
        }
        catch (Exception e) {
            // TODO: handle exception
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
    }*/
}
