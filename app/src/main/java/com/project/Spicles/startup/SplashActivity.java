package com.project.Spicles.startup;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.Spicles.MyBroadcast;
import com.project.Spicles.R;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener {
    Animation animFadeIn;
    LinearLayout linearLayout;
    MyBroadcast broadcast;
    //TextView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);

        }
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_fade_in);
       // logo.setAnimation(animFadeIn);
        animFadeIn.setAnimationListener(this);
        linearLayout = (LinearLayout) findViewById(R.id.layout_linear);
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout.startAnimation(animFadeIn);

    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
            Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
            startActivity(i);
            this.finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcast=new MyBroadcast();
        IntentFilter filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcast,filter);

    }

    @Override
    protected void onStop() {
        super.onStop();

        //unregisterReceiver(broadCast);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcast);
    }

}