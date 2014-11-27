package cityboys.golfapp;

/*
 * Created by Busli on 29.10.2014.
 */
// Notkun: DetailInfo detailInfo = new DetailInfo();
// Fyrir: ekkert
// Eftir: Búið er að búa til undirtré fyrir ExpandableListView í RastimaYfirlit
public class DetailInfo {

    private String sequence = "";
    private String name = "";
    private String userId = "";

    // Kannski hægt að nýta þetta fall
    public String getSequence() {
        return sequence;
    }
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isFull() {
        if(name.equals("")) return false;
        return true;
    }
    public void setUserId(String id) {
        userId = id;
    }
    public boolean alreadyRegistered(String id) {
        if(userId == id) return true;
        return false;
    }

    public void clearAll() {
        name = "";
        userId = "";
    }
}