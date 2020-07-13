package com.project.Spicles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class OrderPlacedActivity extends AppCompatActivity {
    TextView call,orderId;
    String string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);

        orderId=findViewById(R.id.order_id);
        Intent i = getIntent();
        string = i.getStringExtra("order_id");
        orderId.setText(string);
        call = findViewById(R.id.mobile);
        //final String num = call.getText().toString();
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String number=call.getText().toString();
                Log.e("call","please");
                Uri u = Uri.parse("tel:" + call.getText().toString());
                Intent callIntent = new Intent(Intent.ACTION_DIAL,u);

                if(callIntent.resolveActivity(getPackageManager())!=null)
                    startActivity(callIntent);
            }
        });
    }
}