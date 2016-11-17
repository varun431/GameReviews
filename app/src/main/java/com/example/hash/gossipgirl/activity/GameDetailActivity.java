package com.example.hash.gossipgirl.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.hash.gossipgirl.R;

/**
 * Created by HaSh on 11/18/2016.
 */

public class GameDetailActivity extends AppCompatActivity{

    TextView name, desc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_detail);

        name = (TextView) this.findViewById(R.id.name);
        desc = (TextView) this.findViewById(R.id.desc);

        String nameString = getIntent().getExtras().getString("name");
        String descString = getIntent().getExtras().getString("desc");

        name.setText(nameString);
        desc.setText(descString);
    }
}
