package com.theyoungauthors.randomsubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.com.theyoungauthors.com.theyoungauthors.randomsubmission.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    /** Called when the user taps the This is the way button */
    public void leadTheWay(View view) {
        Intent intent = new Intent(this, TheWay.class);
        startActivity(intent);
    }

    /** Called when the user taps the This is the way button */
    public void randomPost(View view) {
        Intent intent = new Intent(this, TheYoungAuthors.class);
        startActivity(intent);
    }

    public void uploadPost(View view) {
        Intent intent = new Intent(this, TheYoungAuthorsUpload.class);
        startActivity(intent);
    }

}