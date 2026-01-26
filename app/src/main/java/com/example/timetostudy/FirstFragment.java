package com.example.timetostudy;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FirstFragment extends Fragment{
    private TextView textView;
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

        new CountDownTimer(1500000, 1000){

            public void onTick(long millisUntilFinished) {
                NumberFormat f = new DecimalFormat("00");
                long seconds = (millisUntilFinished / 1000) % 60;
                long minutes = (millisUntilFinished / 60000) % 60;
                long hours = (millisUntilFinished / 360000) % 60;
                String time = f.format(hours) + ":" + f.format(minutes) + ":" + f.format(seconds);
                textView.setText(time);
            }
            public void onFinish() {
                textView.setText("00:00");
            }

        }.start();
    }
}
