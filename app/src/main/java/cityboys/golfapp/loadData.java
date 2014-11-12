package cityboys.golfapp;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Busli on 12.11.2014.
 */
public class loadData {
    /*
        Notkun: loadData()
        Fyrir: ekkert
        Eftir: búið er að setja inn gögn í master tréið og börn master trésins
    */
    public static void loadData(){

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
