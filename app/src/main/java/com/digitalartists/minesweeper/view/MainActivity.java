package com.digitalartists.minesweeper.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.digitalartists.minesweeper.R;
import com.digitalartists.minesweeper.model.FileProcessing;
import com.digitalartists.minesweeper.model.Settings;
import com.google.firebase.FirebaseApp;

import java.io.IOException;

// Main Activity class
public class MainActivity extends AppCompatActivity {


    // keywords
    public static final String SETTINGS = "SETTINGS";

    // settings object
    private Settings settings = null;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            settings = savedInstanceState.getParcelable(SETTINGS);
        } else {
            try {
                settings = FileProcessing.loadSettings(getApplicationContext());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // handler for 'Play' button
        Settings finalSettings = settings;
        findViewById(R.id.playButton_id).setOnClickListener(butPlay -> {
                Intent intent = new Intent(this, PlayActivity.class);
                intent.putExtra(SETTINGS, finalSettings);
                startActivity(intent);
        });

        // handler for 'Results' button
        findViewById(R.id.resultsButton_id).setOnClickListener(butPlay -> {
            Intent intent = new Intent(this, ResultsActivity.class);
            startActivity(intent);
        });

        // handler for 'log in' button



        findViewById(R.id.sett).setOnClickListener(butPlay -> {
            Intent intent = new Intent(this, Settinggs.class);
            startActivity(intent);
        });
        findViewById(R.id.logbuttonn).setOnClickListener(butPlay -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });

        findViewById(R.id.ldr).setOnClickListener(butPlay -> {
            Intent intent = new Intent(this, Leaderboard.class);
            startActivity(intent);
        });

        findViewById(R.id.logbuttonn).setOnClickListener(butPlay -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });

        // handler for 'Settings' button
        Settings finalSettings2 = settings;
        findViewById(R.id.settingsButton_id).setOnClickListener(butPlay -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra(SETTINGS, finalSettings2);
            startActivity(intent);
        });

        checkTheme();

    }


    // switch theme after closing 'Options' screen if it was changed
    private void checkTheme() {
        if (settings.getIsDarkMode() == 1) {
            AppCompatDelegate.setDefaultNightMode
                    (AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode
                    (AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


    // save window state
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MainActivity.SETTINGS, settings);
    }


    // after return to this activity
    @Override
    public void onResume(){
        super.onResume();
        try {
            settings = FileProcessing.loadSettings(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkTheme();
    }
    /*public void joka(View view) {
        Intent nq = new Intent(MainActivity.this ,Login.class);
        startActivity(nq);
    }*/



}