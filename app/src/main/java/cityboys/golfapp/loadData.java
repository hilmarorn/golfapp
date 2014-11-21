package cityboys.golfapp;
import android.util.Log;
import android.widget.ArrayAdapter;

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
    public static void load(){
        loadHeader();
        loadChildData();
    }

    private static void loadHeader() {
        // Láta þetta skila strengja fylki svo hægt sé að for echa-a þetta shit
        ArrayList<String> lst_times = makeDates.getCurrentTime();

        for(String time : lst_times) {
            makeTimeList(time);
        }
    }

    private static void loadChildData() {
        /*
        Hér fyrir neðan er núna verið að kalla á fall í RastimaYfirlit sem sér um að setja
        þessi gögn inn í ExpandableListView í þeim skjá
         */
        // User.getName()
        addPlayers("10:00", "Guðmundur Jónsson");
        addPlayers("10:00", "Karl Áki Gústafsson");
        addPlayers("11:00", "Jón Jónson");
        addPlayers("11:00", "Tryggvi Einarsson");
        addPlayers("12:00", "Bjarki Ármannsson");
        addPlayers("12:00", "Gréta Magnúsdóttir");
        addPlayers("12:00", "Margrét Ingvarsdóttir");
        addPlayers("13:00", "Gerður Gunnarsdóttir");
        addPlayers("13:00", "Hildur Valdimarsdóttir");
    }
}
