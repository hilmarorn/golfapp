package cityboys.util;

/*
 * Created by Busli on 29.10.2014.
 */
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import cityboys.golfapp.R;

/*
    Notkun: MyListAdapter myAdapter = new MyListAdapter(context, myList);
    Fyrir: context er skjárinn sem er verið vinna í og myList er listinn sem á að tengja
            adapter við
    Eftir: búið er að tengja þennan adapter við myList
*/
public class MyListAdapter extends BaseExpandableListAdapter {

    // Þær breytur sem klasinn vinnur með
    private Context context;
    private ArrayList<HeaderInfo> timeList;

    /*
    Notkun: Smiður sem kallað er sjálfvirkt á þegar búið er til nýtt instance af klasanum
    Fyrir: sjá "Fyrir" lýsingu fyrir klasann í heild
    Eftir: búið er að núllstilla þær breytur sem klasinn er að vinna með
    */
    public MyListAdapter(Context context, ArrayList<HeaderInfo> timeList) {
        this.context = context;
        this.timeList = timeList;
    }

    @Override
    // Notkun: myObject = getChild(groupPosition, childPosition)
    // Fyrir: groupPosition er staðsetning master trésins (HeaderInfo)
    //        childPosition er staðsetning barnsins innan master trésins
    // Eftir: búið er að skila upplýsingunum sem barnið inniheldur
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<DetailInfo> productList = timeList.get(groupPosition).getTimeList();
        return productList.get(childPosition);
    }

    @Override
    // Notkun: position = getChild(groupPosition, childPosition)
    // Fyrir: groupPosition er staðsetning master trésins (HeaderInfo)
    //        childPosition er staðsetning barnsins innan master trésins
    // Eftir: búið er að skila staðsetningu barnsins
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    /*
    Notkun: view = getChildView(int groupPosition, int childPosition, boolean isLastChild,
                                          View view, ViewGroup parent)
    Fyrir: groupPosition er staðsetning master trésins (HeaderInfo)
           childPosition er staðsetning barnsins innan master trésins
           isLastChild segir til um hvort þetta sé síðasta barn eður ei
           view er view-ið sem kallað var úr parent er master tréið
    Eftir: búið er að skila réttu view-i fyrir barnið
    */
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        DetailInfo detailInfo = (DetailInfo) getChild(groupPosition, childPosition);
        // Ef ekki er til view frá skjánum sem var kallað frá þá er fyrirfram skilgreint view notað
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.child_row, null);
        }

        // Hér er fundið textview fyrir barnið og skrifað í það
        TextView childItem = (TextView) view.findViewById(R.id.childItem);
        childItem.setText(detailInfo.getName().trim());

        // Annað hvert barn er litað grátt, verð0ur kannski notað
        /*if(childPosition%2 == 0) {
            view.setBackgroundColor(Color.GRAY);
        } else {
            view.setBackgroundColor(Color.WHITE);
        }*/

        return view;
    }

    @Override
    // Notkun: cnt = getChildrenCount(groupPosition);
    // Fyrir: groupPosition er lögleg staðsetning
    // Eftir: búið er að skila fjölda barna
    public int getChildrenCount(int groupPosition) {
        // Listinn fundinn
        ArrayList<DetailInfo> productList = timeList.get(groupPosition).getTimeList();
        return productList.size();

    }

    @Override
    // Notkun: myObject = getGroup(groupPosition);
    // Fyrir: groupPosition er lögleg staðsetningartala
    // Eftir: búið er að skila öllum börnunum
    public Object getGroup(int groupPosition) {
        return timeList.get(groupPosition);
    }

    @Override
    // Notkun: cnt = getGroupCount();
    // Fyrir: ekkert
    // Eftir: cnt er fjöldi master trjáa
    public int getGroupCount() {
        return timeList.size();
    }

    @Override
    // Notkun: x = getGroupId(groupPosition);
    // Fyrir: groupPosition er lögleg staðsetningartala
    // Eftir: x er id-ið fyrir master tréið með staðsetninguna groupPosition
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    // Notkun: myView = getGroupView(int groupPosition, boolean isLastChild, View view, ViewGroup parent)
    // Fyrir: groupPosition er lögleg staðsetningartala
    //        isLastChild segir hvort þetta sé síðasta barn eða ekki
    //        view er view-ið sem kallað var úr
    //        parent er master tréið
    // Eftir: búið er að skila view-inu fyrir master tréið
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        HeaderInfo headerInfo = (HeaderInfo) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.group_heading, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(headerInfo.getName().trim());

        return view;
    }

    @Override
    //Notkun: myBool = hasStableIds();
    // Fyrir: ekkert
    // Eftir: búið er að skila true
    public boolean hasStableIds() {
        return true;
    }

    @Override
    // Notkun: myBool = isChildSelectable(groupPosition, childPosition)
    // Fyrir: groupPosition er lögleg staðsetningartala
    //        childPosition er lögleg staðsetningartala
    // Eftir: búið er að skila true
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}