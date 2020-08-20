package io.mapwize.example;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SubCenterDetailList extends AppCompatActivity {

    private ListView subCenterDetailList;
    private ListSubCenterDetailViewAdapter subCenterDetailAdapter;


    private String[] subCenterDetailNameList;
    private String languageId;
    private int[] pic;
    private String[] subCenterFloorList;
    private String[] subCenterAreaList;

    private int[] subCenterColorDetailList;

    public static ArrayList<CenterNames> subCenterDetailNamesArrayList = new ArrayList<CenterNames>();

    LinearLayout area_floor;
    LinearLayout backButton;

    TextView title;
    ImageView navigate_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_center_detail_list);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        languageId = intent.getStringExtra("selectedLanguage");
        String label = intent.getStringExtra("label");

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

            subCenterDetailNameList = new String[]{"Legal Opinion and Legislation Section",
                    "Cases and Investigations Section"};
        } else {

            title = (TextView)findViewById(R.id.title_ar);
            title.setVisibility(View.VISIBLE);
            title.setText(label);

            navigate_logo = (ImageView) findViewById(R.id.navigate_logo_ar);
            navigate_logo.setVisibility(View.VISIBLE);

            area_floor = (LinearLayout)findViewById(R.id.arabic);
            area_floor.setVisibility(View.VISIBLE);

            backButton = (LinearLayout) findViewById(R.id.arabicBack);
            backButton.setVisibility(View.VISIBLE);

            subCenterDetailNameList = new String[]{"قسم الرأي القانوني و التشريعات",
                    "قسم القضايا والتحقيقات"};
        }

        pic = new int[10];
        pic[0] = R.drawable.icon_01;
        pic[1] = R.drawable.icon_02;

        subCenterColorDetailList = new int[]{Color.rgb(213, 99, 37),
                Color.rgb(213, 99, 37)};

        subCenterFloorList = new String[]{"1","1"};

        subCenterAreaList = new String[]{"C","C"};

        // Locate the ListView in listview_main.xml
        subCenterDetailList = (ListView) findViewById(R.id.sub_center_detail_listview_listview);

        subCenterDetailNamesArrayList = new ArrayList<>();
        //place_pics = getResources().obtainTypedArray(R.array.place_image);
        for (int i = 0; i < subCenterDetailNameList.length; i++) {
            CenterNames cent = new CenterNames(subCenterDetailNameList[i],subCenterColorDetailList[i],true,subCenterFloorList[i],subCenterAreaList[i],pic[i]);
            // Binds all strings into an array
            subCenterDetailNamesArrayList.add(cent);
        }

        // Pass results to io.mapwize.mapwizesimpleapplication.button.ListCenterViewAdapter Class
        subCenterDetailAdapter = new ListSubCenterDetailViewAdapter(this);

        // Binds the Adapter to the ListView
        subCenterDetailList.setAdapter(subCenterDetailAdapter);

        subCenterDetailList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    Intent intent = new Intent(SubCenterDetailList.this, MainActivity.class);
                    intent.putExtra("placeid","5d233d8ab7e7d80016db9f3d");
                    intent.putExtra("label",subCenterDetailNameList[0]);
                    startActivity(intent);
                }if(position == 1){
                    Intent intent = new Intent(SubCenterDetailList.this, MainActivity.class);
                    intent.putExtra("placeid","5d233d9ab7e7d80016db9f3f");
                    intent.putExtra("label",subCenterDetailNameList[1]);
                    startActivity(intent);
                }
            }
        });
    }


    public void backButton(View view) {
        onBackPressed();
    }
}
