package com.theyoungauthors.randomsubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TheWay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_way);
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Come on man, show me the way...");
    }

    /** Called when the user taps the Send button */
    public void speak(View view) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.incrementProgressBy(1);
        TextView textView = findViewById(R.id.textView2);
        if(progressBar.getProgress() < 30) {
            textView.setText("You are showing me the way, carry on soldier...");
        }

        else if(progressBar.getProgress() < 60) {
            textView.setText("You have shown me much of the way... to victory!");
        }

        else if(progressBar.getProgress() < 90) {
            textView.setText("The end of the way is near... this is the way!");
        }

        else if(progressBar.getProgress() == 100) {
            textView.setText("I have spoken!");
        }

    }
}