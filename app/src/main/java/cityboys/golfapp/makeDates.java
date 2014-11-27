package cityboys.golfapp;

import android.widget.ArrayAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * Created by Busli on 11.11.2014.
 */
/*
Lýsing: Klasinn tekur inn ArrayAdapter<String> sem bundinn er við Spinner element og
      setur inn réttar dagsetningar í Spinner-inn
*/
public class makeDates {
    // Notkun: makeDates.loadDates();
    // Fyrir: currentAdapter verður að vera af taginu ArrayAdapter<String>
    // Eftir: Búið er að setja dagsetningar inn í Spinner
    public static void loadDates(ArrayAdapter<String> currentAdapter) {

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

    /*
    Notkun: makeDates.getCurrentTime()
    Fyrir: ekkert
    Eftir: skilað er ArrayList<String> sem inniheldur tíma
     */
    public static ArrayList<String> getCurrentTime() {
        // Finna núverandi tíma og setja hann á rétt form
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date currentTime = new Date();
        String formatedTime = dateFormat.format(currentTime);

        int lastNumberInTime = Integer.parseInt(formatedTime.substring(formatedTime.length() - 1));

        // TODO: vantar líka tjékka þegar startHour > numHourse
        int startHour = Integer.parseInt(formatedTime.substring(0,2));
        if(startHour < 7) startHour = 7;

        int numHours = 22;

        int startMin = Integer.parseInt(formatedTime.substring(3,4));
        if(lastNumberInTime > 0) startMin++;

        int numMins = 50;
        String stringToSend;

        // ArrayList fyrir tímana
        ArrayList<String> times = new ArrayList<String>();

        // Tímar settir inn í ArrayList<String>
        for(int i = startHour; i < numHours; i++) {
            for(int j = startMin*10; j <= numMins; j += 10) {
                if(j == 0) { stringToSend = String.valueOf(i) + ":00"; }
                else { stringToSend = String.valueOf(i) + ":" + String.valueOf(j); }
                times.add(stringToSend);
            }
            startMin = 0;
        }
        return times;
    }
}