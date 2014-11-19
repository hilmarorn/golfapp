package cityboys.golfapp;
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

        // TODO: Gera þetta frá núverandi tíma þannig að listinn verði ekki svona langur

        int startHour = 7;
        int numHours = 22;
        int startMin = 0;
        int numMins = 50;
        String stringToSend;

        // TODO: Held að þetta sé frekar memory intensive, þarf líklegast að breyta
        for(int i = startHour; i < numHours; i++) {
            for(int j = startMin; j <= numMins; j += 10) {
                // TODO: Þetta er skíta redding, þarf að finna betra
                if(j == 0){
                    stringToSend = String.valueOf(i) + ":00";
                } else {
                    stringToSend = String.valueOf(i) + ":" + String.valueOf(j);
                }
                makeTimeList(stringToSend);
                for(int k = 0; k < MAX_PLAYERS; k++){
                    addPlayers(stringToSend, " ");
                }
            }
        }
    }

    private static void loadChildData() {
        /*
        Hér fyrir neðan er núna verið að kalla á fall í RastimaYfirlit sem sér um að setja
        þessi gögn inn í ExpandableListView í þeim skjá
         */
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
