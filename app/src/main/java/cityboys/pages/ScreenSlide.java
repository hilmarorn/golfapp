package cityboys.pages;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/*
 * Created by Busli on 3.11.2014.
 */
/*
Notkun: pagerAdapter = new ScreenSlide(myFragment)
Fyrir: myFragment verður að vera af taginu FragmentManager
Eftir: Búið er að búa til nýtt fragment og setja það inn í PagerAdapter sem heldur
       utan um öll fragments sem búin hafa verið til og gerir okkur kleift að nýta
       HorizontalScrollView á milli fragment-anna
 */
public class ScreenSlide extends FragmentStatePagerAdapter {

    // Breytur sem klasinn vinnur með
    private int NUM_PAGE;
    private String identifier;

    /*
    Notkun: Kallað er á fallið um leið og búið er til eintak af klasanum
    Fyrir: myFragment verður að vera af taginu FragmentManager
    Eftir: Búið er að bæta fragmentManager-num við PagerAdapter-inn
     */
    public ScreenSlide(FragmentManager myFragment, String identify_activity, int numPage) {
        super(myFragment);
        NUM_PAGE = numPage;
        identifier = identify_activity;
    }

    /*
    Notkun y = myScreenSlide.getItem(x)
    Fyrir: 0 <= x <= NUM_PAGES
    Eftir: y er fragment með blaðsíðutal x
     */
    @Override
    public Fragment getItem(int position) {
        return SlideFragment.create(position, identifier);
    }

    /*
    Notkun: x = myScreenSlide.getCount()
    Fyrir: myScreenSlide er af taginu ScreenSlide
    Eftir: x er blaðsíðutal myScreenSlide
     */
    @Override
    public int getCount() {
        return NUM_PAGE;
    }

    @Override
    /*
    Notkun: title = myScreenSlide.getPageTitle(x)
    Fyrir: x er löglegt blaðsíðutal
    Eftir: title inniheldur nú réttan titil eftir blaðsíðu
     */
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Rástímar";
            case 1:
                return "Rástímaleit";
            case 2:
                return "Rástímayfirlit";
        }
        return "";
    }
}