package com.theyoungauthors.randomsubmission;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TheYoungAuthorsUpload extends AppCompatActivity {

    private WebView wv;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_the_young_authors_upload);
        // Get Web View element to populate with parsed HTML
        wv = (WebView) findViewById(R.id.WebView01);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("https://theyoungauthors.com/wp-login.php");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        if (wv != null) {
            wv.destroy();
        }
    }
}