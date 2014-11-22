package cityboys.golfapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by hilmarhergeirsson on 21/11/14.
 */
public class Clubs {
    public static Club[] clubArray;

    public static void initClubs(String clubData) {
        //set data from database as Json and assign to variables

        try {
            JSONArray Jarray = new JSONArray(clubData);

            clubArray = new Club[Jarray.length()];

            for(int i=0;i<Jarray.length();i++)
            {
                JSONObject Jasonobject = null;
                Jasonobject = Jarray.getJSONObject(i);

                //assign variables to the course
                String club_name = Jasonobject.getString("club_name");
                String short_club_name = Jasonobject.getString("short_club_name");

                clubArray[i] = new Club(club_name,short_club_name);

            }
        }
        catch (Exception e) {
            // TODO: handle exception
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
    }
}
