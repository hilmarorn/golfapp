package cityboys.golfapp;

/*
 * Created by Busli on 29.10.2014.
 */
import java.util.ArrayList;
//Notkun: HeaderInfo headerInfo = new HeaderInfo();
// Fyrir: ekkert
// Eftir: Búið er að búa til master tré sem getur verið með fjögur börn (frá DetailedView)
public class HeaderInfo {

    private String name;
    private ArrayList<DetailInfo> timeList = new ArrayList<DetailInfo>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<DetailInfo> getTimeList() {
        return timeList;
    }
    public void setTimeList(ArrayList<DetailInfo> timeList) {
        this.timeList = timeList;
    }

}
