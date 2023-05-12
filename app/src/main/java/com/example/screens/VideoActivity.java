package com.example.screens;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity {

    TextView textview;
    Intent intent;
    private WebView webview;
    private WebSettings webSettings;


    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
            setContentView(R.layout.activity_video);

        intent = getIntent();
        webview = findViewById(R.id.youtube);
        webview.setWebViewClient(new WebViewClient());
        webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);


        String url = intent.getStringExtra("url"); // your URL here
        Log.i("VideoActivity", "url from intent is " + url);
        webview.loadUrl(url);
    }


    @Override
    public void onBackPressed() {
        if(webview.canGoBack())
            webview.goBack();
        else
            super.onBackPressed();

    }

}
