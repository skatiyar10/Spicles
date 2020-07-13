package com.project.Spicles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.Spicles.startup.MainActivity;

public class ContactActivity extends AppCompatActivity {
    public static String FACEBOOK_URL = "https://www.facebook.com/sachin.katiyar.3720/";
    public static String FACEBOOK_PAGE_ID = "sachin.katiyar.3720";
    ImageView callSpicles, call, mail, facebook;
    TextView privacypolicy_razorpay,privacy,about;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("Contact Us");
        toolbar.setTitleTextColor(getResources().getColor(R.color.gen_white));
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        callSpicles=findViewById(R.id.callSpi);
        call = findViewById(R.id.call);
        mail = findViewById(R.id.mail);
        facebook = findViewById(R.id.fb);
        privacypolicy_razorpay = findViewById(R.id.razpay_policy);
        privacypolicy_razorpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://razorpay.com/sample-application/"));
                startActivity(httpIntent);
            }
        });

        privacy=findViewById(R.id.privacy);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactActivity.this ,PrivacyPolicy.class));
            }
        });

        about=findViewById(R.id.aboutus);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactActivity.this ,AboutUsActivity.class));
            }
        });

        callSpicles.setImageResource(R.drawable.call1);
        call.setImageResource(R.drawable.call1);
        mail.setImageResource(R.drawable.mail1);
        facebook.setImageResource(R.drawable.fb1);

        callSpicles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:7742065586");
                Intent callIntent = new Intent(Intent.ACTION_DIAL,uri);
                if(callIntent.resolveActivity(getPackageManager())!=null)
                    startActivity(callIntent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:7742065586");
                Intent callIntent = new Intent(Intent.ACTION_DIAL,uri);
                if(callIntent.resolveActivity(getPackageManager())!=null)
                    startActivity(callIntent);
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Uri uri = Uri.parse("https://www.facebook.com/sachin.katiyar.3720/");
                Intent i = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(ContactActivity.this);
                i.setData(Uri.parse(facebookUrl));
                startActivity(i);
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"sachinsachin10katiyar@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
       // emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
       // emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "success");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}