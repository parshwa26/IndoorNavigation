package io.mapwize.example;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SubCenterList extends AppCompatActivity {

    private ListView subCenterList;
    private ListSubCenterViewAdapter subCenterAdapter;
    private String[] subCenterFloorList;
    private String[] subCenterAreaList;
    private String[] subCenterNameList;
    private int[] pic;
    private String languageId;
    public static final String SHARED_PREFERENCE = "";
    LinearLayout backButton;

    LinearLayout area_floor;
    private int[] subCenterColorList;

    public static ArrayList<CenterNames> subCenterNamesArrayList = new ArrayList<CenterNames>();
    TextView title;
    ImageView navigate_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_center_list);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();

        languageId = intent.getStringExtra("selectedLanguage");
        String label;
        label = intent.getStringExtra("label");



        if (languageId.equals("EN")) {

            title = (TextView)findViewById(R.id.title_en);
            title.setVisibility(View.VISIBLE);
            title.setText(label);

            navigate_logo = (ImageView) findViewById(R.id.navigate_logo_en);
            navigate_logo.setVisibility(View.VISIBLE);

            area_floor = (LinearLayout)findViewById(R.id.english);
            area_floor.setVisibility(View.VISIBLE);

            backButton = (LinearLayout)findViewById(R.id.englishBack);
            backButton.setVisibility(View.VISIBLE);

            subCenterNameList = new String[]{"Office of the Director General",
                    "Office for Program Implementation Overseeing",
                    "Department of Legal Affairs",
                    "Internal Audit Office",
                    "Director of Investment Office"};
        } else {

            title = (TextView)findViewById(R.id.title_ar);
            title.setVisibility(View.VISIBLE);
            title.setText(label);

            navigate_logo = (ImageView) findViewById(R.id.navigate_logo_ar);
            navigate_logo.setVisibility(View.VISIBLE);


            area_floor = (LinearLayout)findViewById(R.id.arabic);
            area_floor.setVisibility(View.VISIBLE);

            backButton = (LinearLayout)findViewById(R.id.arabicBack);
            backButton.setVisibility(View.VISIBLE);

            subCenterNameList = new String[]{"مكتب المدير العام",
                    "مكتب مراقبة تنفيذ البرامج",
                    "ادارة الشؤون القانونية",
                    "مكتب التدقيق الداخلي",
                    "مدير مكتب الاستثمار"};
        }
        pic = new int[10];
        pic[0] = R.drawable.icon_01;
        pic[1] = R.drawable.icon_02;
        pic[2] = R.drawable.icon_03;
        pic[3] = R.drawable.icon_04;
        pic[4] = R.drawable.icon_05;
        pic[5] = R.drawable.icon_06;


        subCenterColorList = new int[]{Color.rgb(213, 99, 37),
                Color.rgb(213, 99, 37),
                Color.rgb(213, 99, 37),
                Color.rgb(213, 99, 37),
                Color.rgb(213, 99, 37)};

        subCenterFloorList = new String[]{"1","1",
                "1","1",
                "1"};

        subCenterAreaList = new String[]{"A","B",
                "C","D",
                "E"};

        // Locate the ListView in listview_main.xml
        subCenterList = (ListView) findViewById(R.id.sub_center_listview);

        subCenterNamesArrayList = new ArrayList<>();
        //place_pics = getResources().obtainTypedArray(R.array.place_image);
        for (int i = 0; i < subCenterNameList.length; i++) {
            /*if (i == 2) {
                CenterNames cent = new CenterNames(subCenterNameList[i],subCenterColorList[i],true,subCenterFloorList[i],subCenterAreaList[i],pic[i]);
                // Binds all strings into an array
                subCenterNamesArrayList.add(cent);
            } else {*/
                CenterNames cent = new CenterNames(subCenterNameList[i],subCenterColorList[i],false,subCenterFloorList[i],subCenterAreaList[i],pic[i]);
                // Binds all strings into an array
                subCenterNamesArrayList.add(cent);
            //}
        }

        // Pass results to io.mapwize.mapwizesimpleapplication.button.ListCenterViewAdapter Class
        subCenterAdapter = new ListSubCenterViewAdapter(this);

        // Binds the Adapter to the ListView
        subCenterList.setAdapter(subCenterAdapter);

        subCenterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 2) {
                    //SENDING DATA TO ANOTHER ACTIVITY
                    Intent intent = new Intent(SubCenterList.this, SubCenterDetailList.class);
                    intent.putExtra("selectedLanguage", languageId);
                    intent.putExtra("label", subCenterNameList[2]);
                    startActivity(intent);
//                    Toast.makeText(SubCenterList.this, subCenterNamesArrayList.get(position).getPlace_name(), Toast.LENGTH_SHORT).show();
                }
                if (position == 0){
                    Intent intent = new Intent(SubCenterList.this, MainActivity.class);
                    intent.putExtra("placeid","5d233ca4214302002d1e9fa2");
                    intent.putExtra("label",subCenterNameList[0]);
                    startActivity(intent);
                }
                if (position == 1){
                    Intent intent = new Intent(SubCenterList.this, MainActivity.class);
                    intent.putExtra("placeid","5d233cf3214302002d1e9fad");
                    intent.putExtra("label",subCenterNameList[1]);
                    startActivity(intent);
                }
                if (position == 3){
                    Intent intent = new Intent(SubCenterList.this, MainActivity.class);
                    intent.putExtra("placeid","5d233d2896fcf8001640d687");
                    intent.putExtra("label",subCenterNameList[3]);
                    startActivity(intent);
                }
                if (position == 4){
                    Intent intent = new Intent(SubCenterList.this, MainActivity.class);
                    intent.putExtra("placeid","5d233d41362027002d79975d");
                    intent.putExtra("label",subCenterNameList[4]);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void backButton(View view) {
        onBackPressed();
    }
}
