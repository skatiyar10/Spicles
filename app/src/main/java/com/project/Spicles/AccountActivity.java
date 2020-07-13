package com.project.Spicles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class AccountActivity extends AppCompatActivity {
    ImageView photo;
    TextView username,useremail;
    EditText mobile,address;
    Button save,logout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        toolbar = findViewById(R.id.toolbar4);
        toolbar.setTitle("My Account");
        toolbar.setTitleTextColor(getResources().getColor(R.color.gen_white));
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        photo = findViewById(R.id.profileimg);
        username = findViewById(R.id.username);
        useremail = findViewById(R.id.useremail);
        mobile = findViewById(R.id.usercontact);
        address = findViewById(R.id.useraddress);
        save = findViewById(R.id.buttonsave);
        logout=findViewById(R.id.logout);

        SharedPreferences sharedPref = this.getSharedPreferences("FromsignIn", Context.MODE_PRIVATE);
        String name = sharedPref.getString("keyname","default");
        String email = sharedPref.getString("keyemail","default");
        String image=sharedPref.getString("keyimage",null);
        String m=sharedPref.getString("keymobile",null);
        String addr = sharedPref.getString("keyaddr",null);
        address.setText(addr);
        mobile.setText(m);
        // int highScore = sharedPref.getInt(getString(R.string.saved_high_score_key), defaultValue);
        username.setText(name);
        Log.e("image",image);

        useremail.setText(email);
        photo.setImageResource(R.drawable.spicles);
        // userPhoto.setImageURI(Uri.parse(image));
        Picasso.get().load(image).into(photo);


        save = findViewById(R.id.buttonsave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mob = mobile.getText().toString().trim();
                String add = address.getText().toString().trim();
                SharedPreferences pref=getSharedPreferences("FromsignIn",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                editor.putString("keymobile",mob);
                editor.putString("keyaddr",add);
                editor.apply();
                Toast.makeText(getApplicationContext(),"Saved Succesfully",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
