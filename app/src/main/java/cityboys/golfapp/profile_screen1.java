package cityboys.golfapp;

import android.view.View;
import android.widget.TextView;

/*
 * Created by Busli on 15.11.2014.
 */
public class profile_screen1 {

    protected static String club;

    public static void initScreen(View view) {
        // Ná í golfklúbb notanda
        TextView textViewClub = (TextView)view.findViewById(R.id.ps1_golfklubbur);
        club = textViewClub.getText().toString();
    }
}
