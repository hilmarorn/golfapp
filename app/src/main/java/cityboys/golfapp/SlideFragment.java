package cityboys.golfapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SlideFragment extends Fragment {

    private int currentPageNumber;
    private ViewGroup mainView;

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