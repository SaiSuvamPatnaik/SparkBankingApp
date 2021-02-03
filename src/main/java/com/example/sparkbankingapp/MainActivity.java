package com.example.sparkbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation topAnim,bottomAnim,leftanim;
    ImageView logo;
    TextView txt1,txt2,txt3;
    private static int SPLASH_SCREEN = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ANIMATION
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        leftanim=AnimationUtils.loadAnimation(this,R.anim.left_anim);

        logo=findViewById(R.id.logo);
        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);

        logo.setAnimation(leftanim);
        txt1.setAnimation(topAnim);
        txt2.setAnimation(bottomAnim);
        txt3.setAnimation(bottomAnim);

        new Handler() .postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Homepage.class);
                startActivity(intent);
                finish();

            }

        },SPLASH_SCREEN);

    }
}