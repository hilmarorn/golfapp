package cityboys.golfapp;

/*
 * Created by Busli on 12.11.2014.
 */
public class loadData {

    /*
    Þessi klasi er hugsaður til þess að ná í gögn fyrir rástímaskráningu á golf.is
    Verður útfærður fyrir það þegar gagnatenging er komin
     */
    public static void load(){

        /*
        Hér fyrir neðan er núna verið að kalla á fall í RastimaYfirlit sem sér um að setja
        þessi gögn inn í ExpandableListView í þeim skjá
         */

        RastimaYfirlit.addProduct("10:00","Guðmundur Jónsson");
        RastimaYfirlit.addProduct("10:00","Karl Áki Gústafsson");
        RastimaYfirlit.addProduct("10:00","");
        RastimaYfirlit.addProduct("10:00","");

        RastimaYfirlit.addProduct("11:00","Jón Jónson");
        RastimaYfirlit.addProduct("11:00","Tryggvi Einarsson");
        RastimaYfirlit.addProduct("11:00","Bjarki Ármannsson");
        RastimaYfirlit.addProduct("11:00","");

        RastimaYfirlit.addProduct("12:00","Gréta Magnúsdóttir");
        RastimaYfirlit.addProduct("12:00","Margrét Ingvarsdóttir");
        RastimaYfirlit.addProduct("12:00","Gerður Gunnarsdóttir");
        RastimaYfirlit.addProduct("12:00","Hildur Valdimarsdóttir");

        RastimaYfirlit.addProduct("13:00","");
        RastimaYfirlit.addProduct("13:00","");
        RastimaYfirlit.addProduct("13:00","");
        RastimaYfirlit.addProduct("13:00","");

    }
}
