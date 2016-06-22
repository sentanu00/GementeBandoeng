package com.example.sentanu.wikitude_v003;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sentanu.wikitude_v003.tempat_db.tempat_database;

import java.util.ArrayList;

public class key extends AppCompatActivity {

    tempat_database dm;
    ImageView key_story, btn_key;
    Button btn_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);
        dm = new tempat_database(this);

        key_story = (ImageView)findViewById(R.id.img_key);
        btn_key = (ImageView)findViewById(R.id.btn_kunci);
        btn_reset = (Button)findViewById(R.id.btn_reset);


        key_story.setImageResource(R.mipmap.treasure_locked);
        btn_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                open_all_quest();

            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(key.this,ar.class);
                startActivity(i);
            }
        });

    }


    public void open_all_quest(){

        int x=0;
        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();
        for (int posisi = 0; posisi < data.size(); posisi++) {
            ArrayList<Object> baris = data.get(posisi);

            //menentukan konsidi dr database
            if(baris.get(3).toString().equals("true")){
                x=x+1;
            }
        }
        dm.close();
        if(x==10){

            Intent i=new Intent(key.this,hadiah.class);
            startActivity(i);

        }
    }

    @Override
    public void onBackPressed(){
        Intent i=new Intent(key.this,MapsActivity.class);
        startActivity(i);
    }

    private void editData(String kunci, String status, String lock){
        dm.editRow(kunci, status, lock);
    }
}
