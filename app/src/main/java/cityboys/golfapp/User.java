package cityboys.golfapp;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

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

    private static int total_eagles;
    private static int total_birdies;
    private static int total_par;
    private static int total_bogey;
    private static int total_double_bogey;

    private static String eagles_percentage;
    private static String birdies_percentage;
    private static String par_percentage;
    private static String bogey_percentage;
    private static String double_bogey_percentage;

    private static String profile_picture_url;
    private static Bitmap profile_picture;

    public static void initUser(String userInfo) {
        //set data from database as Json and assign to variables
        try {
            JSONArray Jarray = new JSONArray(userInfo);

            for(int i=0;i<Jarray.length();i++)
            {
                JSONObject Jasonobject = null;
                Jasonobject = Jarray.getJSONObject(i);

                //assign variables to the user
                user_id = Jasonobject.getString("user_id");
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

                total_eagles = Integer.parseInt(Jasonobject.getString("total_eagles"));
                total_birdies = Integer.parseInt(Jasonobject.getString("total_birdies"));
                total_par = Integer.parseInt(Jasonobject.getString("total_par"));
                total_bogey = Integer.parseInt(Jasonobject.getString("total_bogey"));
                total_double_bogey = Integer.parseInt(Jasonobject.getString("total_double_bogey"));
                int sumTotal = total_eagles+total_birdies+total_par+total_bogey+total_double_bogey;

                eagles_percentage = String.valueOf(100*total_eagles/sumTotal)+"%";
                birdies_percentage = String.valueOf(100*total_birdies/sumTotal)+"%";
                par_percentage = String.valueOf(100*total_par/sumTotal)+"%";
                bogey_percentage = String.valueOf(100*total_bogey/sumTotal)+"%";
                double_bogey_percentage = String.valueOf(100*total_double_bogey/sumTotal)+"%";

                profile_picture_url = Jasonobject.getString("profile_picture");

                /*Load the user profile picture*/
                //new UserImageLoader("https://notendur.hi.is/~hoh40/Hugbunadarverkfraedi1/S3/"+profile_picture_url).execute();
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
        return String.valueOf(total_eagles);
    }

    public static String getTotalBirdies() {
        return String.valueOf(total_birdies);
    }

    public static String getTotalPar() {
        return String.valueOf(total_par);
    }

    public static String getTotalBogey() {
        return String.valueOf(total_bogey);
    }

    public static String getTotalDoubleBogey() {
        return String.valueOf(total_double_bogey);
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

    public static void setProfilePicture(Bitmap loadedImage) {
        profile_picture = loadedImage;
    }

    public static Bitmap getProfilePicture() {
        return profile_picture;
    }

    public static String getProfilePictureUrl() {
        return profile_picture_url;
    }

    public static void clearUserData() {
        //clear user variables
        user_id = null;
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
        total_eagles = 0;
        total_birdies = 0;
        total_par = 0;
        total_bogey = 0;
        total_double_bogey = 0;
        eagles_percentage = null;
        birdies_percentage = null;
        par_percentage = null;
        bogey_percentage = null;
        double_bogey_percentage = null;
        profile_picture = null;
        profile_picture_url = null;
    }
}
