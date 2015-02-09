package cityboys.user;

import android.view.View;
import android.widget.TextView;

import cityboys.golfapp.R;

/*
 * Created by Busli on 15.11.2014.
 */
public class profile_screen2 {

    public static void initScreen(View view) {
        // Gera eitthvað fancy hér þegar virknir er kominn í notandaskjáinn

        /*set user values*/
        TextView total_eagles = (TextView) view.findViewById(R.id.ps2_fjoldi1);
        total_eagles.setText(User.getTotalEagles());

        TextView total_birdies = (TextView) view.findViewById(R.id.ps2_fjoldi2);
        total_birdies.setText(User.getTotalBirdies());

        TextView total_par = (TextView) view.findViewById(R.id.ps2_fjoldi3);
        total_par.setText(User.getTotalPar());

        TextView total_bogey = (TextView) view.findViewById(R.id.ps2_fjoldi4);
        total_bogey.setText(User.getTotalBogey());

        TextView total_double_bogey = (TextView) view.findViewById(R.id.ps2_fjoldi5);
        total_double_bogey.setText(User.getTotalDoubleBogey());

        TextView eagles_percentage = (TextView) view.findViewById(R.id.ps2_alls1);
        eagles_percentage.setText(User.getEaglesPercentage());

        TextView birdies_percentage = (TextView) view.findViewById(R.id.ps2_alls2);
        birdies_percentage.setText(User.getBirdiesPercentage());

        TextView par_percentage = (TextView) view.findViewById(R.id.ps2_alls3);
        par_percentage.setText(User.getParPercentage());

        TextView bogey_percentage = (TextView) view.findViewById(R.id.ps2_alls4);
        bogey_percentage.setText(User.getBogeyPercentage());

        TextView double_bogey_percentage = (TextView) view.findViewById(R.id.ps2_alls5);
        double_bogey_percentage.setText(User.getDoubleBogeyPercentage());
    }
}
