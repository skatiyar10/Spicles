package com.project.Spicles;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class NoInternet extends AppCompatActivity implements View.OnClickListener{
    Button tryagain;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        tryagain= (Button) findViewById(R.id.tryagain);
        tryagain.setOnClickListener( this);

    }


    @Override
    public void onClick(View v)
    {
        finish();
    }
}
