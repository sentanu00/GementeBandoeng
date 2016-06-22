package com.example.sentanu.wikitude_v003;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    public void onBackPressed(){
        Intent i=new Intent(about.this,MapsActivity.class);
        startActivity(i);
    }
}
