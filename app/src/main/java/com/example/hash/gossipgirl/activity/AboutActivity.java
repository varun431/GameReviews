package com.example.hash.gossipgirl.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.hash.gossipgirl.R;

/**
 * Created by HaSh on 11/18/2016.
 */

public class AboutActivity extends AppCompatActivity {

    TextView about;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        about = (TextView) findViewById(R.id.about);
        about.setText("The latest video game rating and release date, piped into your pocket." +
                " A must-have app for gamers" +
                " that need to know what's new and which products are worthy.");
    }
}
