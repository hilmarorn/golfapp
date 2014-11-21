package cityboys.golfapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by hilmarhergeirsson on 15/11/14.
 */
public class User {

    private static String user_id;
    private static String user_name;
    private static String full_name;
    private static String handicap;
    private static String club_name;
    private static String handicap_country;
    private static String handicap_club;
    private static String times_played_year;
    private static String last_played;
    private static String best_played_year;
    private static String avg_point_count;
    private static String total_eagles;
    private static String total_birdies;
    private static String total_par;
    private static String total_bogey;
    private static String total_double_bogey;
    private static String eagles_percentage;
    private static String birdies_percentage;
    private static String par_percentage;
    private static String bogey_percentage;
    private static String double_bogey_percentage;

    public static void initUser(String userInfo) {
        //set data from database as Json and assign to variables
        try {
            JSONArray Jarray = new JSONArray(userInfo);

            for(int i=0;i<Jarray.length();i++)
            {
                JSONObject Jasonobject = null;
                Jasonobject = Jarray.getJSONObject(i);

                //assign variables to the user
                //user_id = Jasonobject.getString("user_id");
                user_name = Jasonobject.getString("user_name");
                full_name = Jasonobject.getString("full_name");
                handicap = Jasonobject.getString("handicap");
                club_name = Jasonobject.getString("club_name");
                handicap_club = Jasonobject.getString("handicap_club");
                handicap_country = Jasonobject.getString("handicap_country");
                times_played_year = Jasonobject.getString("times_played_year");
                last_played = Jasonobject.getString("last_played");
                best_played_year = Jasonobject.getString("best_played_year");
                avg_point_count = Jasonobject.getString("avg_point_count");
                total_eagles = Jasonobject.getString("total_eagles");
                total_birdies = Jasonobject.getString("total_birdies");
                total_par = Jasonobject.getString("total_par");
                total_bogey = Jasonobject.getString("total_bogey");
                total_double_bogey = Jasonobject.getString("total_double_bogey");
                eagles_percentage = Jasonobject.getString("eagles_percentage");
                birdies_percentage = Jasonobject.getString("birdies_percentage");
                par_percentage = Jasonobject.getString("par_percentage");
                bogey_percentage = Jasonobject.getString("bogey_percentage");
                double_bogey_percentage = Jasonobject.getString("double_bogey_percentage");

            }
        }
        catch (Exception e) {
            // TODO: handle exception
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
    }

    public static String getUserId() {
        return user_id;
    }

    public static String getFullName() {
        return full_name;
    }

    public static String getUserName() {
        return user_name;
    }

    public static String getGolfClub() {
        return club_name;
    }

    public static String getHandicap() {
        return handicap;
    }

    public static String getHandicapClub() {
        return handicap_club;
    }

    public static String getHandicapCountry() {
        return handicap_country;
    }

    public static String getTimesPlayedYear() {
        return times_played_year;
    }

    public static String getLastPlayed() {
        return last_played;
    }

    public static String getBestPlayedYear() {
        return best_played_year;
    }

    public static String getAvgPointCount() {
        return avg_point_count;
    }

    public static String getTotalEagles() {
        return total_eagles;
    }

    public static String getTotalBirdies() {
        return total_birdies;
    }

    public static String getTotalPar() {
        return total_par;
    }

    public static String getTotalBogey() {
        return total_bogey;
    }

    public static String getTotalDoubleBogey() {
        return total_double_bogey;
    }

    public static String getEaglesPercentage() {
        return eagles_percentage;
    }

    public static String getBirdiesPercentage() {
        return birdies_percentage;
    }

    public static String getParPercentage() {
        return par_percentage;
    }

    public static String getBogeyPercentage() {
        return bogey_percentage;
    }

    public static String getDoubleBogeyPercentage() {
        return double_bogey_percentage;
    }

    public static void clearUserData() {
        //clear user variables
        //user_id = null;
        user_name = null;
        full_name = null;
        handicap = null;
        club_name = null;
        handicap_club = null;
        handicap_country = null;
        times_played_year = null;
        last_played = null;
        best_played_year = null;
        avg_point_count = null;
        total_eagles = null;
        total_birdies = null;
        total_par = null;
        total_bogey = null;
        total_double_bogey = null;
        eagles_percentage = null;
        birdies_percentage = null;
        par_percentage = null;
        bogey_percentage = null;
        double_bogey_percentage = null;
    }
}
