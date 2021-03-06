package com.theyoungauthors.randomsubmission;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.Bundle;
import android.webkit.WebView;

public class TheYoungAuthors extends AppCompatActivity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_the_young_authors);

        //Instantiating the URL class
        URL url = null;
        try {
            url = new URL("https://theyoungauthors.com/random-submission/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //Retrieving the contents of the specified page

        // Start a new URL connection
        URLConnection yc = null;
        try {
            yc = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a bufferedReader to store the HTML contents while reading
        BufferedReader in = null;
        String result = "";
        try {
            in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read in the HMTL contents into result
        String inputLine = "";

        while(inputLine != null){
            try {
                inputLine = in.readLine();
                result += inputLine;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Close the BufferedReader
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get Web View element to populate with parsed HTML
        WebView wv = (WebView) findViewById(R.id.WebView01);

        final String mimeType = "text/html";
        final String encoding = "UTF-8";
        String content = "";

        if(result.indexOf("<div class=\"entry-content\" itemprop=\"articleBody\">") != -1) {

            // Retrieve first the post title
            int x = result.indexOf("<meta property=\"og:title\" content=");
            int y = result.substring(x).indexOf(">");
            String post_title = result.substring(x+"<meta property=\"og:title\" content=".length(),x+y).replace("/", "").replace("\"", "");

            content = "<br><u>" + post_title + "</u><br>" + result.substring(result.indexOf("<div class=\"entry-content\" itemprop=\"articleBody\">"));
            if(content.indexOf("<a href") != -1) {
                content = content.substring(0, content.indexOf("<a href"));
            }
            if(content.indexOf("<img") != -1){
                content = content.substring(0, content.indexOf("<img"));
                if(content.indexOf("related") != -1){
                    content = content.substring(0, content.indexOf("related"));
                }
            }
        }
        //Removing the HTML tags
        //result = result.replaceAll("<[^>]*>", "");
        if(content.length() > 30) {
            wv.loadDataWithBaseURL("", content, mimeType, encoding, "");
        }
        else {
            wv.loadDataWithBaseURL("", "Server encountered an error while retrieving the post. Please try again...", mimeType, encoding, "");
        }
    }
}