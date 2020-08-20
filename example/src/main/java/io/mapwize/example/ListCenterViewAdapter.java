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
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class ListCenterViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<CenterNames> arraylist;

    private static SharedPreferences prefs;
    ImageView centerPic;

    public ListCenterViewAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<CenterNames>();
        this.arraylist.addAll(CenterList.centerNamesArrayList);
    }

    public class ViewHolder {
        TextView centerName;
        TextView centerFloor;
        ImageView centerImage;
        FrameLayout centerBack;
    }

    @Override
    public int getCount() {
        return CenterList.centerNamesArrayList.size();
    }

    @Override
    public CenterNames getItem(int position) {
        return CenterList.centerNamesArrayList.get(position);
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

            if(language == "EN"){
                Log.d("par_lan",language);
                view = inflater.inflate(R.layout.centers_listview_item, null);
            } else {
                Log.d("par_lan",language);
                view = inflater.inflate(R.layout.centers_listview_item_arabic, null);
            }

            // Locate the TextViews in listview_item.xml
            holder.centerName = (TextView) view.findViewById(R.id.center_name);
            holder.centerImage = (ImageView) view.findViewById(R.id.center_image);
            holder.centerBack = (FrameLayout) view.findViewById(R.id.center_back);
            holder.centerFloor = (TextView) view.findViewById(R.id.center_floor);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews

        holder.centerName.setText(CenterList.centerNamesArrayList.get(position).getPlace_name());
        holder.centerImage.setBackgroundColor(CenterList.centerNamesArrayList.get(position).getImgBackCol());
        holder.centerFloor.setText(CenterList.centerNamesArrayList.get(position).getFloor());

        if (CenterList.centerNamesArrayList.get(position).getIsEnable()) {
            holder.centerBack.setBackgroundColor(Color.rgb(255, 255, 255));
        } else {
            holder.centerBack.setBackgroundColor(Color.rgb(225, 225, 225));
        }

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        CenterList.centerNamesArrayList.clear();
        if (charText.length() == 0) {
            CenterList.centerNamesArrayList.addAll(arraylist);
        } else {
            for (CenterNames wp : arraylist) {
                if (wp.getPlace_name().toLowerCase(Locale.getDefault()).contains(charText)) {
                    CenterList.centerNamesArrayList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}