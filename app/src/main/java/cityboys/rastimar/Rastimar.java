package cityboys.rastimar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cityboys.golfInfo.Courses;
import cityboys.golfapp.R;
import cityboys.util.addCourses;

/*
 * Created by Busli on 11.11.2014.
 */
public class Rastimar {

    private static ListView club_list;    // View-ið fyrir golfvellina
    private static ArrayAdapter<String> clubAdapter; //Adapter fyrir golfklubbana

    /*
    Notkun: Rastimar.initScreen(view)
    Fyrir: view er view-ið sem verið er að vinna með
    Eftir: Búið er að gera Rastimar fragment-ið
     */
    public static void initScreen(View view) {
        makeListView(view);
    }

    /*
    Notkun: makeListView(view)
    Fyrir: view er view-ið sem kallað var úr
    Eftir: búið er að gera ListView með golfvöllum í rástíma fragment-inu
    */
    private static void makeListView(View view) {
        // ListView fundið
        club_list = (ListView)view.findViewById(R.id.r_club_list);

        // Adapter fyrir ListView og hann festur við
        clubAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1);
        club_list.setAdapter(clubAdapter);

        // Setja upplýsingar í Adapter-inn
        addCourses.add(clubAdapter, "clubs");

        club_list.setOnItemClickListener(myClickListener);
    }

    private static AdapterView.OnItemClickListener myClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            // Klúbburinn sem er valinn
            String selectedClub = (String) adapterView.getItemAtPosition(position);

            // Hér er fundin réttur golfvöllur út frá golfklúbbnum
            for(int i = 0; i < Courses.courseArray.length; i++) {
                if(selectedClub.equals(Courses.courseArray[i].getClubName()))
                    Rastimar_master.selectedCourse = Courses.courseArray[i].getClubShortName() + " - " + Courses.courseArray[i].getCourseName();
            }

            // Færum okkur yfir í Rástímayfirlit skjáinn
            Rastimar_master.actionBar.setSelectedNavigationItem(Rastimar_master.NUM_PAGE - 1);
        }
    };
}