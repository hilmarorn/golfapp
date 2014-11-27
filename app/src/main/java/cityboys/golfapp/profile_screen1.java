package cityboys.golfapp;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Created by Busli on 15.11.2014.
 */
public class profile_screen1 {
    public static void initScreen(View view) {

        Context context = view.getContext();
        CharSequence text = "Strjúktu til vinstri fyrir forgjöf og tölfræði";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        /*set user values*/
        TextView handicap = (TextView) view.findViewById(R.id.textView6);
        handicap.setText(User.getHandicap());

        TextView club_name = (TextView) view.findViewById(R.id.ps1_golfklubbur);
        club_name.setText(User.getGolfClub());

        TextView handicap_club = (TextView) view.findViewById(R.id.textView7);
        handicap_club.setText(User.getHandicapClub());

        TextView handicap_country = (TextView) view.findViewById(R.id.textView9);
        handicap_country.setText(User.getHandicapCountry());

        TextView times_played_year = (TextView) view.findViewById(R.id.textView11);
        times_played_year.setText(User.getTimesPlayedYear());

        TextView last_played = (TextView) view.findViewById(R.id.textView16);
        last_played.setText(User.getLastPlayed());

        TextView best_played_year = (TextView) view.findViewById(R.id.textView18);
        best_played_year.setText(User.getBestPlayedYear());

        TextView avg_point_count = (TextView) view.findViewById(R.id.textView20);
        avg_point_count.setText(User.getAvgPointCount());

        ImageView profile_picture = (ImageView) view.findViewById(R.id.imageView2);
        //profile_picture.setImageBitmap(User.getProfilePicture());

        BitmapDrawable bitmapDrawable = new BitmapDrawable(User.getProfilePicture());
        profile_picture.setImageDrawable(bitmapDrawable);
    }
}
