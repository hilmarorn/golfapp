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
    private ViewGroup mainView;

    /*
    Norkun: SlideFragment.create(x)
    Fyrir: x er af taginu int
    Eftir: Búið er að búa til nýtt fragment með blaðsíðutal x
     */
	public static SlideFragment create(int pageNumber) {
        SlideFragment myFragment = new SlideFragment();

        // Binda blaðsíðutal við fragmentið
        Bundle args = new Bundle();
        args.putInt("page",pageNumber);
        myFragment.setArguments(args);

        return myFragment;
	}

    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentPageNumber = getArguments().getInt("page");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Hér er fundið hvaða skjá á að birta eftir blaðsíðutali
        if(currentPageNumber == 0) {
            mainView = (ViewGroup)inflater
                    .inflate(R.layout.profile_screen1, container, false);
        } else {
            mainView = (ViewGroup)inflater
                    .inflate(R.layout.profile_screen2, container, false);
        }

        return mainView;
    }
}