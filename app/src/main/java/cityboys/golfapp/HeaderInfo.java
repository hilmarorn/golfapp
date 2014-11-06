package cityboys.golfapp;

/**
 * Created by Busli on 29.10.2014.
 */
import java.util.ArrayList;

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
