package cityboys.golfapp;

/**
 * Created by hilmarhergeirsson on 21/11/14.
 */
public class Club {

    private final String clubName;
    private final String clubShortName;

    Club(String clubName, String clubShortName) {
        this.clubName = clubName;
        this.clubShortName = clubShortName;
    }

    public String getClubName(){
        return this.clubName;
    }

    public String getClubShortName(){
        return this.clubShortName;
    }
}
