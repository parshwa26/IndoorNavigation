package io.mapwize.example;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListSubCenterDetailViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<CenterNames> arraylist;
    private static SharedPreferences prefs;
    ImageView centerPic;

    public ListSubCenterDetailViewAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<CenterNames>();
        this.arraylist.addAll(SubCenterDetailList.subCenterDetailNamesArrayList);
    }

    public class ViewHolder {
        TextView centerName;
        TextView centerFloor;
        TextView centerArea;
        ImageView centerImage;
        FrameLayout centerBack;
    }

    @Override
    public int getCount() {
        return SubCenterDetailList.subCenterDetailNamesArrayList.size();
    }

    @Override
    public CenterNames getItem(int position) {
        return SubCenterDetailList.subCenterDetailNamesArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();

            prefs = mContext.getSharedPreferences("language", 0);
            String language = prefs.getString("language", "");

            Log.d("par_language",language);
            if(language == "EN"){
                view = inflater.inflate(R.layout.listview_item, null);
            }
            else {
                view = inflater.inflate(R.layout.listview_item_arabic, null);
            }

            // Locate the TextViews in listview_item.xml
            holder.centerName = (TextView) view.findViewById(R.id.center_name);
            holder.centerFloor = (TextView) view.findViewById(R.id.center_floor);
            holder.centerArea = (TextView) view.findViewById(R.id.center_area);
            holder.centerImage = (ImageView) view.findViewById(R.id.center_image);
            holder.centerBack = (FrameLayout) view.findViewById(R.id.center_back);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews

        holder.centerName.setText(SubCenterDetailList.subCenterDetailNamesArrayList.get(position).getPlace_name());
        holder.centerFloor.setText(SubCenterDetailList.subCenterDetailNamesArrayList.get(position).getFloor());
        holder.centerArea.setText(SubCenterDetailList.subCenterDetailNamesArrayList.get(position).getArea());
        holder.centerImage.setImageResource(SubCenterList.subCenterNamesArrayList.get(position).getPic());

        holder.centerBack.setBackgroundColor(Color.rgb(255, 255, 255));

        return view;
    }

}