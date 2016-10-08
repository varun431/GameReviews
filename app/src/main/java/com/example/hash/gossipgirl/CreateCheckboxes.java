package com.example.hash.gossipgirl;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;

/**
 * Created by HaSh on 10/4/2016.
 */

public class CreateCheckboxes extends AppCompatActivity {

    public CheckBox getCB(String name, int tag) {
        CheckBox cb = new CheckBox(this);
        cb.setText(name);
        cb.setTag(tag);
        return cb;
    }
}
