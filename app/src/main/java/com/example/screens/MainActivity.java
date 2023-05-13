package com.example.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tw;
    EditText urlField;
    Button switchActivity;
    static final String defaultUrl="https://www.youtube.com/watch?v=LIUoFuSuvTM&ab_channel=NeringaRekasiute";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tw = findViewById(R.id.message);
        urlField = findViewById(R.id.url_field);
        switchActivity = findViewById(R.id.switch_activity);
    }



    public void onButtonClick(View view) {
        Intent switchIntent = new Intent(this, VideoActivity.class);
        String url = urlField.getText().toString();

        /* if nothing has been entered by user we send a default url with the intent
         * otherwise we extract the entered string and send that
         */
        if(url.isEmpty()) {
            switchIntent.putExtra("url", defaultUrl);
        }else {
            if( ! ( url.startsWith("http://") ) || url.startsWith(("https://") )) {
                url = "https://" + url;
            }
            switchIntent.putExtra("url", url);
        }

        startActivity(switchIntent);
        urlField.setText("", TextView.BufferType.EDITABLE); // set content of field to empty string
                                                                // in case the user returns to this activity
    }
}
