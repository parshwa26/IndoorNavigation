package io.mapwize.example;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;



public class CenterList extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ListView centerList;
    private ListCenterViewAdapter centerAdapter;
    private SearchView editsearch;
    private String[] centerNameList;
    private String[] centerFloorList;
    private String selectedLang;
    TypedArray place_pics;
    LinearLayout backButton;

    private int[] centerColorList;

    public static ArrayList<CenterNames> centerNamesArrayList = new ArrayList<CenterNames>();
    private static SharedPreferences prefs;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_list);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Intent intent = getIntent();
        selectedLang = intent.getStringExtra("selectedLanguage");

        if (selectedLang.equals("EN")) {

            title = (TextView)findViewById(R.id.title_en);
            title.setVisibility(View.VISIBLE);
            title.setText("Centers");

            backButton = (LinearLayout)findViewById(R.id.english);
            backButton.setVisibility(View.VISIBLE);
            centerNameList = new String[]{"Director General",
                    "Strategic Planning and Performance Management Sector",
                    "Real Estate Registration Department",
                    "Infrastructure and Municipal Assets Sector",
                    "Municipal Services Sector",
                    "Support and Support Services Sector",
                    "Town Planning Sector",
                    "Sub-municipal Operations Sector"};

            centerFloorList = new String[]{"First Floor","Second Floor",
                    "Third Floor","Fourth Floor",
                    "Fifth Floor","Sixth Floor",
                    "Seventh Floor","Eight Floor"};
        } else {

            title = (TextView)findViewById(R.id.title_ar);
            title.setVisibility(View.VISIBLE);
            title.setText("مراكز");

            backButton = (LinearLayout)findViewById(R.id.arabic);
            backButton.setVisibility(View.VISIBLE);
            centerNameList = new String[]{"المدير العام",
                    "قطاع التخطيط الإستراتيجي وإدارة الأداء",
                    "قطاع الاراضي والعقارات/ إدارة التسجيل العقاري",
                    "قطاع البنية التحتية وأصول البلدية",
                    "قطاع الخدمات البلدية",
                    "قطاع خدمات الدعم والمساندة",
                    "قطاع تخطيط المدن",
                    "قطاع عمليات البلديات الفرعية"};

            centerFloorList = new String[]{"الطابق الاول", "الطابق الثاني",
                    "الطابق الثالث", "الطابق الرابع",
                    "الطابق الخامس", "الطابق السادس",
                    "الطابق السابع", "الطابق الثامن"
            };
        }

        centerColorList = new int[]{Color.rgb(242, 92, 25),
                Color.rgb(238, 174, 48),
                Color.rgb(136, 92, 61),
                Color.rgb(27, 116, 49),
                Color.rgb(44, 50, 138),
                Color.rgb(116, 31, 34),
                Color.rgb(0, 114, 178),
                Color.rgb(18, 170, 177)};


        // Locate the ListView in listview_main.xml
        centerList = (ListView) findViewById(R.id.centerListview);

        centerNamesArrayList = new ArrayList<>();
        //place_pics = getResources().obtainTypedArray(R.array.place_image);
        for (int i = 0; i < centerNameList.length; i++) {

            CenterNames cent = new CenterNames(centerNameList[i],centerColorList[i],true, centerFloorList[i]);
            // Binds all strings into an array
            centerNamesArrayList.add(cent);

            /*if (i == 0) {
                CenterNames cent = new CenterNames(centerNameList[i],centerColorList[i],true, centerFloorList[i]);
                // Binds all strings into an array
                centerNamesArrayList.add(cent);
            } else  {
                CenterNames cent = new CenterNames(centerNameList[i],centerColorList[i],false, centerFloorList[i]);
                // Binds all strings into an array
                centerNamesArrayList.add(cent);
            }*/
        }

        // Pass results to io.mapwize.mapwizesimpleapplication.button.ListCenterViewAdapter Class
        centerAdapter = new ListCenterViewAdapter(this);

        // Binds the Adapter to the ListView
        centerList.setAdapter(centerAdapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        centerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    //SENDING DATA TO ANOTHER ACTIVITY
                    Intent intent = new Intent(CenterList.this, SubCenterList.class);
                    intent.putExtra("selectedLanguage",selectedLang);
                    intent.putExtra("label",centerNameList[0]);
                    startActivity(intent);
//                    Toast.makeText(CenterList.this, centerNamesArrayList.get(position).getPlace_name(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        centerAdapter.filter(text);
        return false;
    }

    public void backButton(View view) {
        onBackPressed();
    }
}
