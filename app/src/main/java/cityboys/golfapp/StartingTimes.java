package cityboys.golfapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by hilmarhergeirsson on 26/11/14.
 */
public class StartingTimes {

    public static StartingTime[] startingArray;

    // Notkun: StartingTimes.initStartingTimes(startingData);
    // Fyrir: ekkert
    // Eftir: búið er að gera nýjan StartingTimes hlut
    public static void initStartingTimes(String startingData) {
        //set data from database as Json and assign to variables

        try {
            JSONArray Jarray = new JSONArray(startingData);

            startingArray = new StartingTime[Jarray.length()];

            for(int i=0;i<Jarray.length();i++)
            {
                JSONObject Jasonobject = null;
                Jasonobject = Jarray.getJSONObject(i);

                //assign variables to the course
                String course_name = Jasonobject.getString("course_name");
                String course_id = Jasonobject.getString("course_id");
                String full_name = Jasonobject.getString("full_name");
                String user_id = Jasonobject.getString("user_id");
                String startDate = Jasonobject.getString("startDate");
                String startTime = Jasonobject.getString("startTime");

                startingArray[i] = new StartingTime(course_name,course_id,full_name, user_id, startDate, startTime);

            }
        }
        catch (Exception e) {
            // TODO: handle exception
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
    }
}
