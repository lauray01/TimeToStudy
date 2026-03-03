package com.example.timetostudy;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FirstFragment extends Fragment{
    private TextView textView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 1500000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.timer);
        Button btn25 = view.findViewById(R.id.twentyFiveMin);
        Button btn10 = view.findViewById(R.id.tenMin);
        Button btn5 = view.findViewById(R.id.fiveMin);
        Button btnStart = view.findViewById(R.id.buttonStart);

        btn25.setOnClickListener( v -> {
            updateTimeSelection(1500000);
        });

        btn10.setOnClickListener( v -> {
            updateTimeSelection(600000);
        });

        btn5.setOnClickListener( v -> {
            updateTimeSelection(300000);
        });

        btnStart.setOnClickListener( v -> {
            startTimer(timeLeftInMillis);
        });
    }

    private void updateTimeSelection(long millis){
        if (countDownTimer != null) countDownTimer.cancel();
        timeLeftInMillis = millis;
        updateText(millis);
    }

    private void updateText(long millis){
        NumberFormat f = new DecimalFormat("00");
        long min = (millis / 60000) % 60;
        long sec = (millis /1000) % 60;
        textView.setText(f.format(min) + ":" + f.format(sec));
    }
    private void startTimer(long duration) {
        if (countDownTimer != null) countDownTimer.cancel();
        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateText(millisUntilFinished);
            }
            @Override
            public void onFinish() {
                showAlert();
            }
        }.start();
    }
    private void showAlert() {
        new AlertDialog.Builder(getContext())
                .setTitle("Time's up!")
                .setMessage("Work session finished. Start a break?")
                .setPositiveButton("Start Break (5mins)", (dialog, which) -> startTimer(300000))
                .setNegativeButton("Dismiss", null)
                .show();
    }
}
