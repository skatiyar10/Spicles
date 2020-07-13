package com.project.Spicles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        toolbar = findViewById(R.id.toolbar_about);
        toolbar.setTitle("About Us");
        toolbar.setTitleTextColor(getResources().getColor(R.color.gen_white));
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        t = findViewById(R.id.about);
        t.setText("Spicles specializes in various forms of spices, snacks and picles ranging from quick bites to delicious aroma. Spicles is owned by Spicles Limited, situated at Mansarovar, Jaipur. With a wide assortment of spices and pickles straight from the hands of Daadis and Naanis, Spicles values the taste, aroma and satisfaction of taste buds as it's utmost priority.");
    }
}