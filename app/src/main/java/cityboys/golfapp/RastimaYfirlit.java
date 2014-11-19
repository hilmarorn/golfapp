package cityboys.golfapp;

import android.view.View;
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
    private static MyListAdapter listAdapter;
    public static ArrayAdapter<String> dateYfirlitAdapter, courseYfirlitAdapter;
    // Listinn og Spinner-arnir
    private static ExpandableListView myList;
    public static Spinner dates_yfirlit, courses_yfirlit;

    // TODO: Kannski þarf að fá þessa breytu frá GSÍ
    public static final int MAX_PLAYERS = 4;

    // TODO: Finna betri lausn
    private static View myView;

    private static int index;
    /*
    Notkun: RastimaYfirlit.initScreen(view)
    Fyrir: view er view-ið sem er verið að vinna með
    Eftir: búið er að búa til RastimaYfirlit fragment-ið
     */
    public static void initScreen(View view) {
        myView = view;
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
        addCourses.add(courseYfirlitAdapter, view, "");

        /*
        Hér þarf ég að setja réttan klúbb sem hefur verið valinn.
        Annaðhvort er það klúbburinn sem notandi er skráður í
        eða klúbburinn sem valinn var í Rástímar skjánum
         */
        int selectedClubPosition = courseYfirlitAdapter.getPosition(Rastimar_master.selectedClub);
        courses_yfirlit.setSelection(selectedClubPosition);
        courseYfirlitAdapter.notifyDataSetChanged();
        Rastimar.firstTimeInScreen = false;
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
        }
    }

    /*
    Notkun: addPlayers()
    Fyrir: ekkert
    Eftir: búið er að setja allar upplýsingar inn í master tréin og börnin þeirra
     */
    public static int addPlayers(String timeToAdd, String player_name){

        int groupPosition = 0;

        // Ná í rétta tímann sem á að setja leikmanninn á
        HeaderInfo currentInputTime = myTimes.get(timeToAdd);

        // TODO: Þarf að gera tjékk til að tjékka hvort það sé leikmaður skráður
        if(currentInputTime != null) {
            // Finna undirlistann fyrir tímann sem settur var inn
            ArrayList<DetailInfo> lst_players = currentInputTime.getTimeList();

            // Tjékkar hvort fullt sé í rástímann
            // TODO: Þarf virkilega að laga þetta, but hey it werkz!
            if(!checkIfFull(lst_players) && !lst_players.isEmpty()) {
                // Búa til nýtt barn ef ekki til
                if(lst_players.size() < MAX_PLAYERS){
                    DetailInfo playerInfo = new DetailInfo();
                    playerInfo.setName(player_name);
                    lst_players.add(playerInfo);
                    currentInputTime.setTimeList(lst_players);
                } else if(!lst_players.get(index).isFull()){
                    DetailInfo tempPlayer = lst_players.get(index);
                    tempPlayer.setName(player_name);
                    lst_players.set(index, tempPlayer);
                    currentInputTime.setTimeList(lst_players);
                }
            } else if(lst_players.isEmpty()) {
                DetailInfo playerInfo = new DetailInfo();
                //detailInfo.setSequence(String.valueOf(listSize));
                playerInfo.setName(player_name);
                lst_players.add(playerInfo);
                currentInputTime.setTimeList(lst_players);
            }

            // Finna rétta staðsetningu hópsins
            groupPosition = timeList.indexOf(currentInputTime);
            return groupPosition;
                    } /*catch (Exception e) {
            // TODO: Hvað er betra að gera ef eitthvað klikkar?
            Toast toast = Toast.makeText(myView.getContext().getApplicationContext(), e.getMessage(),
                    Toast.LENGTH_SHORT);
            toast.show();
        }*/

        return groupPosition;
    }

    private static boolean checkIfFull(ArrayList<DetailInfo> currentList) {
        for(int i = 0; i < currentList.size(); i++) {
            if(!currentList.get(i).isFull()) {
                index = i;
                return false;
            }
        }
        return true;
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
}