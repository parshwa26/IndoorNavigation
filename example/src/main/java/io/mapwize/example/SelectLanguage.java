package io.mapwize.example;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectLanguage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);



        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("language", "en");
        editor.apply();

    }
    public static final String MY_PREFS_NAME = "language";

    public void english(View view) {
        Intent intent = new Intent(SelectLanguage.this, CenterList.class);
        intent.putExtra("selectedLanguage","EN");

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("language", "EN");
        editor.apply();
        startActivity(intent);
    }

    public void arebic(View view) {
        Intent intent = new Intent(SelectLanguage.this, CenterList.class);
        intent.putExtra("selectedLanguage","AR");

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("language", "AR");
        editor.apply();
        startActivity(intent);
    }
}
