package cityboys.golfapp;

/**
 * Created by Busli on 29.10.2014.
 */
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<HeaderInfo> timeList;

    public MyListAdapter(Context context, ArrayList<HeaderInfo> timeList) {
        this.context = context;
        this.timeList = timeList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<DetailInfo> productList = timeList.get(groupPosition).getTimeList();
        return productList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        DetailInfo detailInfo = (DetailInfo) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.child_row, null);
        }

        TextView childItem = (TextView) view.findViewById(R.id.childItem);
        childItem.setText(detailInfo.getName().trim());

        if(childPosition%2 == 0) {
            view.setBackgroundColor(Color.GRAY);
        } else {
            view.setBackgroundColor(Color.WHITE);
        }

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<DetailInfo> productList = timeList.get(groupPosition).getTimeList();
        return productList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return timeList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return timeList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
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
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}