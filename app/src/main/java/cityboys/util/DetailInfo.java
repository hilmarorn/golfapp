package cityboys.util;

/*
 * Created by Busli on 29.10.2014.
 */
// Notkun: DetailInfo detailInfo = new DetailInfo();
// Fyrir: ekkert
// Eftir: Búið er að búa til undirtré fyrir ExpandableListView í RastimaYfirlit
public class DetailInfo {

    private String name = "";
    private String userId = "";

    /*
    Notkun: x.getName()
    Fyrir: x er af taginu DetailInfo
    Eftir: Búið er að skila nafninu sem skráð er
     */
    public String getName() {
        return name;
    }

    /*
    Notkun: x.setName(name)
    Fyrir: x er af taginu DetailInfo og name er stengur
    Eftir: Búið er að skrá nafnið name í x
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
    Notkun: x.isFull()
    Fyrir: x er af taginu DetailInfo
    Eftir: Ef name er tómur stengur er skilað false annars true
     */
    public boolean isFull() {
        if(name.equals("")) return false;
        return true;
    }

    /*
    Notkun: x.setUserId(y)
    Fyrir: x er af taginu DetailInfo og y er strengur sem er ID notanda
    Eftir: Búið er að skrá y sem userId
    */
    public void setUserId(String id) {
        userId = id;
    }

    /*
    Notkun: x.alreadyRegistered(y)
    Fyrir: x er af taginu DetailInfo og y er strengur sem táknar id notanda
    Eftir: Ef y er sama og skrá userId þá er skilað true annars false
     */
    public boolean alreadyRegistered(String id) {
        if(userId == id) return true;
        return false;
    }

    /*
    Notkun: x.clearAll()
    Fyrir: x er af taginu DetailInfo
    Eftir: búið er að hreinsa x
     */
    public void clearAll() {
        name = "";
        userId = "";
    }
}