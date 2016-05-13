package com.example.hulk.golf;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(Splash.this)
                .withFullScreen()
                .withTargetActivity(Intro.class)
                .withSplashTimeOut(4000)
                .withBackgroundResource(android.R.color.holo_red_light)
                .withHeaderText("Header")
                .withFooterText("Copyright 2016")
                .withBeforeLogoText("My cool company")
//                .withLogo(R.drawable.logo)
                .withAfterLogoText("Some more details with custom font");
        //add custom font
        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "Pacifico.ttf");
        config.getAfterLogoTextView().setTypeface(pacificoFont);

        //change text color
        config.getHeaderTextView().setTextColor(Color.WHITE);

        //finally create the view
        View easySplashScreenView = config.create();
        setContentView(easySplashScreenView);
    }
}
