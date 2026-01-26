package com.example.timetostudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the toolbar from your layout
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        // Set it as the official action bar for the activity
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        Fragment firstFragment = new FirstFragment();
        Fragment secondFragment = new SecondFragment();

        bottomNavigationView.setOnItemSelectedListener( item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.pomodoro) {
                setCurrentFragment(firstFragment);
            } else if (itemId == R.id.analytics) {
                setCurrentFragment(secondFragment);
            }
            return true;
        });

    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flFragment, fragment)
                .commit();
    }



}