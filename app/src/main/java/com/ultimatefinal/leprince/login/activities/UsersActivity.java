package com.ultimatefinal.leprince.login.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ultimatefinal.leprince.login.R;

/**
 * Created by Le prince on 13/08/2018.
 */
public class UsersActivity extends AppCompatActivity {
    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        getSupportActionBar().hide();

        textViewName= (TextView) findViewById(R.id.text1);
        String nammeFrontIntent=getIntent().getStringExtra("EMAIL");
    }
}
