package cityboys.golfapp;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static cityboys.golfapp.RastimaYfirlit.*;

/*
 * Created by Busli on 12.11.2014.
 */
public class loadData {

    /*
    Þessi klasi er hugsaður til þess að ná í gögn fyrir rástímaskráningu á golf.is
    Verður útfærður fyrir það þegar gagnatenging er komin
     */
    public static void load(String date){
        loadHeader();
        checkForPlayers(date);
    }

    private static void loadHeader() {
        // Láta þetta skila strengja fylki svo hægt sé að for echa-a þetta shit
        ArrayList<String> lst_times = makeDates.getCurrentTime();

        for(String time : lst_times)
            makeTimeList(time);
    }

    private static void checkForPlayers(String date) {

        String[] trimmedNameOfCourse = Rastimar_master.selectedCourse.split("-");
        String formatedDate = "";

        for(int i = 0; i < StartingTimes.startingArray.length; i++) {

            String[] trimmedTime = StartingTimes.startingArray[i].getStartTime().split(":");
            String[] splitDate = StartingTimes.startingArray[i].getStartDate().split("-");
            formatedDate = splitDate[2]+"/"+splitDate[1]+"/"+splitDate[0];

            if(trimmedNameOfCourse[1].trim().equals(StartingTimes.startingArray[i].getCourseName()) && date.equals(formatedDate)) {
                    addPlayers(trimmedTime[0]+":"+trimmedTime[1], StartingTimes.startingArray[i].getUserName(), StartingTimes.startingArray[i].getUserId());
            }
        }
    }
}
