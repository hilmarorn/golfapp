package cityboys.golfInfo;

/**
 * Created by hilmarhergeirsson on 21/11/14.
 */

// Notkun: Club this_club = new Club(String clubName, String clubShortName);
// Fyrir: ekkert
// Eftir: búið er að gera nýjan Club hlut
public class Club {

    private final String clubName;
    private final String clubShortName;

    Club(String clubName, String clubShortName) {
        this.clubName = clubName;
        this.clubShortName = clubShortName;
    }

    // Notkun: Club.getClubName()
    // Fyrir: ekkert
    // Eftir: búið er að sækja nafn klúbbsins
    public String getClubName(){
        return this.clubName;
    }

    // Notkun: Club.getClubShortName()
    // Fyrir: ekkert
    // Eftir: búið er að sækja skammstöfun klúbbsins
    public String getClubShortName(){
        return this.clubShortName;
    }
}
