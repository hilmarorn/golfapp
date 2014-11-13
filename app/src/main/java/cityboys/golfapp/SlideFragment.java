package cityboys.golfapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
Notkun: SlideFragment myFragment = new SlideFragment()
Fyrir: ekkert
Eftir: búið er að búa til nýtt fragment með blaðsíðutali
 */
public class SlideFragment extends Fragment {

    private int currentPageNumber;
    private View mainView;
    private String identifier;

    /*
    Norkun: SlideFragment.create(x)
    Fyrir: x er af taginu int
    Eftir: Búið er að búa til nýtt fragment með blaðsíðutal x
     */
	public static SlideFragment create(int pageNumber, String origin) {
        SlideFragment myFragment = new SlideFragment();

        // Binda blaðsíðutal við fragmentið ásamt því að finna frá hvaða skjá það kom
        Bundle args = new Bundle();
        args.putInt("page",pageNumber);
        args.putString("origin", origin);
        myFragment.setArguments(args);

        return myFragment;
	}

    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentPageNumber = getArguments().getInt("page");
        identifier = getArguments().getString("origin");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Hér er valið rétt layout eftir því hvaða fragment kallaði á klasann
        if(identifier.equals("profile")) {
            // Hér er fundið hvaða skjá á að birta eftir blaðsíðutali
            if (currentPageNumber == 0) {
                mainView = inflater.inflate(R.layout.profile_screen1, container, false);
            } else {
                mainView = inflater.inflate(R.layout.profile_screen2, container, false);
            }
        } else if(identifier.equals("rastimi")) {
            // Hér er fundið hvaða skjá á að birta eftir blaðsíðutali
            switch(currentPageNumber) {
                case 0:
                    mainView = inflater.inflate(R.layout.rastimar, container, false);
                    Rastimar.initScreen(mainView);
                    break;
                case 1:
                    mainView = inflater.inflate(R.layout.rastima_leit, container, false);
                    RastimaLeit.init(mainView);
                    break;
                case 2:
                    mainView = inflater.inflate(R.layout.rastima_yfirlit, container, false);
                    RastimaYfirlit.initScreen(mainView);
                    break;
            }
        }
        return mainView;
    }
}