package cityboys.golfapp;

import java.util.ArrayList;
import static cityboys.golfapp.RastimaYfirlit.*;

/*
 * Created by Busli on 12.11.2014.
 */
public class loadData {

    /*
    Þessi klasi er hugsaður til þess að ná í gögn fyrir rástímaskráningu á golf.is
    Verður útfærður fyrir það þegar gagnatenging er komin
     */
    /*
    Notkun: loadData.load(x)
    Fyrir: x er strengur sem táknar dagsetningu
    Eftir: búið er að búa til lista af tímum fyrir rástímayfirlit og fylla inní leikmenn sem eru skráðir
     */
    public static void load(String date){
        loadHeader();
        checkForPlayers(date);
    }

    /*
    Notkun: loadHeader()
    Fyrir: ekkert
    Eftir: búið er að búa til lista af tímum fyrir rástímayfirlit
     */
    private static void loadHeader() {
        // Fáum lista yfir tímana
        ArrayList<String> lst_times = makeDates.getCurrentTime();

        // Búin til master tré fyrir hvern tíma
        for(String time : lst_times)
            makeTimeList(time);
    }

    /*
    Notkun: checkForPlayers(x)
    Fyrir: x er stengur sem inniheldur dagsetningu
    Eftir: Búið er að setja alla skráða leikmenn á rétta tíma
     */
    private static void checkForPlayers(String date) {
        // Þurfum að setja golfvöllinn á rétt form
        String[] trimmedNameOfCourse = Rastimar_master.selectedCourse.split("-");
        String formatedDate = "";

        for(int i = 0; i < StartingTimes.startingArray.length; i++) {
            // Hér er tíminn og dagsetning sett á rétt form
            String[] trimmedTime = StartingTimes.startingArray[i].getStartTime().split(":");
            String[] splitDate = StartingTimes.startingArray[i].getStartDate().split("-");
            formatedDate = splitDate[2]+"/"+splitDate[1]+"/"+splitDate[0];

            // Ef leikmaður er skráður á þennan völl á þessum degi þá er hann skráður á réttan tíma
            if(trimmedNameOfCourse[1].trim().equals(StartingTimes.startingArray[i].getCourseName()) && date.equals(formatedDate)) {
                    addPlayers(trimmedTime[0]+":"+trimmedTime[1], StartingTimes.startingArray[i].getUserName(), StartingTimes.startingArray[i].getUserId());
            }
        }
    }
}