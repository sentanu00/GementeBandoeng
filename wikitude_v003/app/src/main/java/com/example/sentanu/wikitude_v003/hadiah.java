package com.example.sentanu.wikitude_v003;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class hadiah extends AppCompatActivity {

    ImageView iv_hadiah;
    TextView tv_hadiah;
    String hadiah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadiah);
        iv_hadiah = (ImageView)findViewById(R.id.iv_hadiah_utama);
        tv_hadiah = (TextView)findViewById(R.id.TV_hadiah_utama);
        hadiah = getResources().getString(R.string.hadiah);
        tv_hadiah.setText(hadiah);
    }

    @Override
    public void onBackPressed(){
        Intent i=new Intent(hadiah.this,key.class);
        startActivity(i);
    }
}
