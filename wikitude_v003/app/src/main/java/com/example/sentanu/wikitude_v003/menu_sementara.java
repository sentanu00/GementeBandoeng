package com.example.sentanu.wikitude_v003;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.example.sentanu.wikitude_v003.tempat_db.tempat_database;

import java.util.ArrayList;

public class menu_sementara extends AppCompatActivity {

    tempat_database dm;
    TextView nama, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sementara);

        dm = new tempat_database(this);

        nama = (TextView)findViewById(R.id.nama);
        score = (TextView)findViewById(R.id.score);

        set_player_profil();
        DisplayMetrics dmetric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dmetric);
        setTitle("Menu");

        int width = dmetric.widthPixels;
        int height = dmetric.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));
    }
    public void menu(View v){
        Intent i;
        if(v.getId()==R.id.kunci){

            i=new Intent(menu_sementara.this,key.class);
            startActivity(i);

        }else if(v.getId()==R.id.item){

            i=new Intent(menu_sementara.this,item.class);
            startActivity(i);

        }else if(v.getId()==R.id.tutorial){

            i=new Intent(menu_sementara.this,tutorial.class);
            i.putExtra("key_tutor", 0);
            startActivity(i);

        }else if(v.getId()==R.id.about){

            i=new Intent(menu_sementara.this,about.class);
            startActivity(i);

        }else if(v.getId()==R.id.street_direction){
            String dataLA = getIntent().getStringExtra("latitude");
            String dataLO = getIntent().getStringExtra("longitude");
            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + dataLA + ","+dataLO+ "");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
    }

    public void set_player_profil(){

        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBarisPlayer();
        for (int posisi = 0; posisi < data.size(); posisi++) {
            ArrayList<Object> baris = data.get(posisi);

            //menentukan konsidi dr database
            if(baris.get(2).toString().equals("false")){
                nama.setText(baris.get(1).toString());
                score.setText(baris.get(4).toString());
            }

        }
        dm.close();

    }
}
