package cityboys.golfapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
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
    public static MyListAdapter listAdapter;
    public static ArrayAdapter<String> dateYfirlitAdapter, courseYfirlitAdapter;
    // Listinn og Spinner-arnir
    private static ExpandableListView myList;
    public static Spinner dates_yfirlit, courses_yfirlit;

    // TODO: Kannski þarf að fá þessa breytu frá GSÍ
    public static final int MAX_PLAYERS = 4;

    private static Context myContext;

    private static int index;
    private static String date;

    /*
    Notkun: RastimaYfirlit.initScreen(view)
    Fyrir: view er view-ið sem er verið að vinna með
    Eftir: búið er að búa til RastimaYfirlit fragment-ið
     */
    public static void initScreen(View view) {
        myContext = view.getContext();

        // Skjárinn búinn til
        makeSpinners(view);
        makeExpandableListView(view);
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

        addCourses.add(courseYfirlitAdapter, "courses");

        courses_yfirlit.setOnItemSelectedListener(new onSpinnerSelected());
        dates_yfirlit.setOnItemSelectedListener(new onSpinnerSelected());
        // Fá dagsetninguna þegar skjárinn er búinn til
        date = dates_yfirlit.getItemAtPosition(0).toString();
    }

    /*
    Notkun: new onSpinnerSelected();
    Fyrir: ekkert
    Eftir: Búið er að finna út á hvaða element var smellt á í Spinner
    */
    private static class onSpinnerSelected implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // Fundið út á hvaða spinner var smellt og rétt aðgerð framkvæmd
            switch(parent.getId()) {
                case R.id.spinner_date_yfirlit:
                    date = parent.getItemAtPosition(pos).toString();

                    ArrayList<String> times = makeDates.getCurrentTime();
                    String[] gimmeTimes = new String[times.size()];
                    times.toArray(gimmeTimes);

                    for(int i = 0; i < gimmeTimes.length;i++){
                        HeaderInfo ekkedShit = myTimes.get(gimmeTimes[i]);
                        ArrayList<DetailInfo> prump = ekkedShit.getTimeList();
                        for(int j = 0; j < prump.size();j++) {
                            prump.get(j).clearAll();
                        }
                    }

                    loadData.load(date);
                    listAdapter.notifyDataSetChanged();
                    break;
                case R.id.spinner_course_yfirlit:
                    changeValueInSpinner();
                    break;
            }
        }
        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    /*
    Notkun: makeExpandableListView(view)
    Fyrir: view er view-ið sem kallað var úr
    Eftir: búið er að gera ExpandableListView fyrir rástímayfirlit fragment-ið
    */
    public static void makeExpandableListView(View view) {
        // Finna ExpandableListView-ið
        myList = (ExpandableListView) view.findViewById(R.id.myList);
        // Búa til adapter fyrir ExpandableListView-ið og setja inn gögnin
        listAdapter = new MyListAdapter(view.getContext(), timeList);
        myList.setAdapter(listAdapter);
        // Listener fyrir börnin
        myList.setOnChildClickListener(myListItemClicked);
        // listener fyrir master tréið
        myList.setOnGroupClickListener(myListGroupClicked);

        // Setjum gögn í ExpandableListView-ið
        loadData.load(date);
    }

    public static void changeValueInSpinner() {
        // TODO: kannski að fá parent view sent hingað inn til að finna réttan streng
        int selectedClubPosition = courseYfirlitAdapter.getPosition(Rastimar_master.selectedCourse);
        courses_yfirlit.setSelection(selectedClubPosition, true);
        courseYfirlitAdapter.notifyDataSetChanged();
    }

    public static void makeTimeList(String timeToAdd) {
        // Gá í hash mappinu hvort master tréið sé til
        HeaderInfo currentInputTime = myTimes.get(timeToAdd);
        // Bæta við ef ekki til
        if(currentInputTime == null){
            currentInputTime = new HeaderInfo();
            currentInputTime.setName(timeToAdd);
            myTimes.put(timeToAdd, currentInputTime);
            timeList.add(currentInputTime);
            ArrayList<DetailInfo> slotsForPlayers = currentInputTime.getTimeList();
            for(int i = 0; i < MAX_PLAYERS; i++) {
                DetailInfo emptyPlayer = new DetailInfo();
                emptyPlayer.setName("");
                slotsForPlayers.add(emptyPlayer);
            }
            currentInputTime.setTimeList(slotsForPlayers);
        }
    }

    /*
    Notkun: addPlayers()
    Fyrir: ekkert
    Eftir: búið er að setja allar upplýsingar inn í master tréin og börnin þeirra
     */
    public static void addPlayers(String timeToAdd, String player_name, String userID){

        // TODO: Tjékka hvort notandi sé þegar skráður á þessum tíma

        // Ná í rétta tímann sem á að setja leikmanninn á
        HeaderInfo currentInputTime = myTimes.get(timeToAdd);

        if(currentInputTime != null) {
            // Finna undirlistann fyrir tímann sem settur var inn
            ArrayList<DetailInfo> lst_players = currentInputTime.getTimeList();
            index = 0;
            if(!checkIfFull(lst_players, userID)) {
                //Toast.makeText(myContext, "UserId sem er verið að skrá er: "+userID,Toast.LENGTH_LONG).show();
                DetailInfo tempPlayer = lst_players.get(index);
                tempPlayer.setName(player_name);
                tempPlayer.setUserId(userID);
                lst_players.set(index, tempPlayer);
                currentInputTime.setTimeList(lst_players);
            } else {
                Toast toast = Toast.makeText(myContext, "Þú ert þegar skráður á þennan tíma", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    // TODO: ekki alveg viss með þetta
    private static boolean checkIfFull(ArrayList<DetailInfo> currentList, String userId) {
        for (int i = 0; i < currentList.size(); i++)
            if (currentList.get(i).alreadyRegistered(userId)) return true;

        for(int j = 0; j < currentList.size(); j++) {
            if (!currentList.get(j).isFull()) {
                index = j;
                return false;
            }
        }
        return false;
    }

    // Listener fyrir börnin
    private static ExpandableListView.OnChildClickListener myListItemClicked =  new ExpandableListView.OnChildClickListener() {

        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {

            // Fá nafn master trésins
            HeaderInfo headerInfo = timeList.get(groupPosition);
            // Fá allar upplýsingar frá börnunum
            DetailInfo detailInfo =  headerInfo.getTimeList().get(childPosition);

            // Bæta player við og láta vita
            /*addPlayers(headerInfo.getName(), User.getFullName(), User.getUserId());
            listAdapter.notifyDataSetChanged();*/

            // Finna völlinn í Spinner
            String course = courseYfirlitAdapter.getItem(courseYfirlitAdapter.getPosition(Rastimar_master.selectedCourse));

            // Ræsum nýjan skjá til að sjá um að skrá notanda á rástíma
            Intent skra_tima = new Intent(myContext, skraTima.class);
            skra_tima.putExtra("date", date);
            skra_tima.putExtra("course", course);
            skra_tima.putExtra("time", headerInfo.getName());
            myContext.startActivity(skra_tima);

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
}