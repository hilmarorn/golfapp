package cityboys.golfapp;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/*
 * Created by Busli on 12.11.2014.
 */
public class RastimaYfirlit {

    // Listarnir fyrir master tréið og börnin
    private static LinkedHashMap<String, HeaderInfo> myTimes = new LinkedHashMap<String, HeaderInfo>();
    private static ArrayList<HeaderInfo> timeList = new ArrayList<HeaderInfo>();
    // Adapter-ar fyrir tréin og börning og Spinner-ana
    private static MyListAdapter listAdapter;
    private static ArrayAdapter<String> dateYfirlitAdapter, courseYfirlitAdapter;
    // Listinn og Spinner-arnir
    private static ExpandableListView myList;
    private static Spinner dates_yfirlit, courses_yfirlit;

    /*
    Notkun: RastimaYfirlit.initScreen(view)
    Fyrir: view er view-ið sem er verið að vinna með
    Eftir: búið er að búa til RastimaYfirlit fragment-ið
     */
    public static void initScreen(View view) {
        // Skjárinn búinn til
        makeExpandableListView(view);
        makeSpinners(view);
    }

    /*
    Notkun: makeExpandableListView(view)
    Fyrir: view er view-ið sem kallað var úr
    Eftir: búið er að gera ExpandableListView fyrir rástímayfirlit fragment-ið
    */
    public static void makeExpandableListView(View view) {
        // Setjum gögn í ExpandableListView-ið
        loadData.load();

        // Finna ExpandableListView-ið
        myList = (ExpandableListView) view.findViewById(R.id.myList);
        // Búa til adapter fyrir ExpandableListView-ið og setja inn gögnin
        listAdapter = new MyListAdapter(view.getContext(), timeList);
        myList.setAdapter(listAdapter);
        // Listener fyrir börnin
        myList.setOnChildClickListener(myListItemClicked);
        // listener fyrir master tréið
        myList.setOnGroupClickListener(myListGroupClicked);
    }

    /*
    Notkun: makeSpinnersForYfirlit(view)
    Fyrir: view er view-ið frá skjánum sem kallaði
    Eftir: búið er að búa til spinner-a fyrir rástíma yfirlit
     */
    public static void makeSpinners(View view) {
        // Finna spinner-a
        dates_yfirlit = (Spinner)view.findViewById(R.id.spinner_date_yfirlit);
        courses_yfirlit = (Spinner)view.findViewById(R.id.spinner_course_yfirlit);

        // Setja adapter á þá
        dateYfirlitAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1);
        courseYfirlitAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1);

        // Sét fyrirfram skilgreint útlit á þá
        dateYfirlitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseYfirlitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dates_yfirlit.setAdapter(dateYfirlitAdapter);
        courses_yfirlit.setAdapter(courseYfirlitAdapter);

        // Fylla inní þá
        makeDates.loadDates(dateYfirlitAdapter);
        addCourses.add(courseYfirlitAdapter, view);
    }


    /*
    Notkun: addProduct()
    Fyrir: ekkert
    Eftir: búið er að setja allar upplýsingar inn í master tréin og börnin þeirra
     */
    public static int addProduct(String time, String player_name){

        int groupPosition = 0;

        // Gá í hash mappinu hvort master tréið sé til
        HeaderInfo headerInfo = myTimes.get(time);
        // Bæta við ef ekki til
        if(headerInfo == null){
            headerInfo = new HeaderInfo();
            headerInfo.setName(time);
            myTimes.put(time, headerInfo);
            timeList.add(headerInfo);
        }

        // Finna rétt börn fyrir master tréið
        ArrayList<DetailInfo> lst_players = headerInfo.getTimeList();
        //size of the children list
        //int listSize = lst_players.size();
        //add to the counter
        //listSize++;

        // Búa til nýtt barn ef ekki til
        DetailInfo detailInfo = new DetailInfo();
        //detailInfo.setSequence(String.valueOf(listSize));
        detailInfo.setName(player_name);
        lst_players.add(detailInfo);
        headerInfo.setTimeList(lst_players);

        // Finna rétta staðsetningu hópsins
        groupPosition = timeList.indexOf(headerInfo);
        return groupPosition;
    }

    // Listener fyrir börnin
    private static ExpandableListView.OnChildClickListener myListItemClicked =  new ExpandableListView.OnChildClickListener() {

        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {

            // Fá nafn master trésins
            HeaderInfo headerInfo = timeList.get(groupPosition);
            // Fá allar upplýsingar frá börnunum
            DetailInfo detailInfo =  headerInfo.getTimeList().get(childPosition);
            // Kóðinn fyrir neðan er geymdur hér til að muna útfærsluatriði
            /*
            Intent skra_tima = new Intent(getBaseContext(), skraTima.class);
            skra_tima.putExtra("date", date);
            skra_tima.putExtra("course", course);
            skra_tima.putExtra("time", headerInfo.getName());
            startActivity(skra_tima);
            */
            return false;
        }

    };

    // Listener fyrir master tréin
    private static ExpandableListView.OnGroupClickListener myListGroupClicked =  new ExpandableListView.OnGroupClickListener() {

        public boolean onGroupClick(ExpandableListView parent, View v,
                                    int groupPosition, long id) {
            // Fá nafn master trésins
            //HeaderInfo headerInfo = timeList.get(groupPosition);
            return false;
        }

    };

    /*
    Glósað hér fyrir mögulegt notagildi

        //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.collapseGroup(i);
        }
    }
     */
}