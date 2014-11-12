package cityboys.golfapp;

import android.widget.ArrayAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Busli on 11.11.2014.
 */
// Lýsing: Klasinn tekur inn ArrayAdapter<String> sem bundinn er við Spinner element og
//      setur inn réttar dagsetningar í Spinner-inn
public class makeDates {
    // Notkun: makeDates.makeDates();
    // Fyrir: currentAdapter verður að vera af taginu ArrayAdapter<String>
    // Eftir: Búið er að setja dagsetningar inn í Spinner
    public static void makeDates(ArrayAdapter<String> currentAdapter) {

        /*
        TODO:
        Þegar gagnagrunnstenging er komin þá þarf að gera viðeigandi prófanir
        til að fá réttan dagsetningarfjölda inn
         */

        // Finna dagsetninguna í dag og format-a hana rétt
        Calendar currentDate = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Bæta við 10 dagsetningum
        for (int i = 0; i < 10; i++) {
            currentAdapter.add(dateFormat.format(currentDate.getTime()));
            // Hér dagurinn hækkaður um einn
            currentDate.add(Calendar.DAY_OF_MONTH, 1);
            currentAdapter.notifyDataSetChanged();
        }
    }
}
