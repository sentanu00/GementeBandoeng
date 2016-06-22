package com.example.sentanu.wikitude_v003;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sentanu.wikitude_v003.tempat_db.tempat_database;

import java.util.ArrayList;

public class popupQuest extends AppCompatActivity {


    tempat_database dm;
    String[] Quest;
    TextView TV_Story_key;
    ImageView iv, btn_ar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_quest);
        dm = new tempat_database(this);

        DisplayMetrics dmetric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dmetric);
        setTitle("Quest");


        int width = dmetric.widthPixels;
        int height = dmetric.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        Quest = getResources().getStringArray(R.array.quest);
        TV_Story_key = (TextView)findViewById(R.id.TV_Story_Key);
        iv =(ImageView)findViewById(R.id.image_key);
       // btn_ar = (ImageView)findViewById(R.id.button_ar);

        TV_Story_key.setGravity(Gravity.LEFT);
        TV_Story_key.setText(Quest[0]);

        //button_ar();

        filter_quest();
    }

    /*public void button_ar(){
        btn_ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(popupQuest.this,MainActivity.class);
                startActivity(i);
            }
        });
    }*/


    public void filter_quest(){

        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();
        for (int posisi = 0; posisi < data.size(); posisi++) {
            ArrayList<Object> baris = data.get(posisi);



            //menentukan konsidi dr database
            if(baris.get(2).toString().equals("true")){

                if(baris.get(1).toString().equals("stilasi 1")){
                    TV_Story_key.setText(Quest[0]);
                    iv.setImageResource(R.mipmap.locked_1);
                }else if(baris.get(1).toString().equals("stilasi 2")){
                    TV_Story_key.setText(Quest[1]);
                    iv.setImageResource(R.mipmap.locked_2);
                }else if(baris.get(1).toString().equals("stilasi 3")){
                    TV_Story_key.setText(Quest[2]);
                    iv.setImageResource(R.mipmap.locked_3);
                }else if(baris.get(1).toString().equals("stilasi 4")){
                    TV_Story_key.setText(Quest[3]);
                    iv.setImageResource(R.mipmap.locked_4);
                }else if(baris.get(1).toString().equals("stilasi 5")){
                    TV_Story_key.setText(Quest[4]);
                    iv.setImageResource(R.mipmap.locked_5);
                }else if(baris.get(1).toString().equals("stilasi 6")){
                    TV_Story_key.setText(Quest[5]);
                    iv.setImageResource(R.mipmap.locked_6);
                }else if(baris.get(1).toString().equals("stilasi 7")){
                    TV_Story_key.setText(Quest[6]);
                    iv.setImageResource(R.mipmap.locked_7);
                }else if(baris.get(1).toString().equals("stilasi 8")){
                    TV_Story_key.setText(Quest[7]);
                    iv.setImageResource(R.mipmap.locked_8);
                }else if(baris.get(1).toString().equals("stilasi 9")){
                    TV_Story_key.setText(Quest[8]);
                    iv.setImageResource(R.mipmap.locked_9);
                }else if(baris.get(1).toString().equals("stilasi 10")){
                    TV_Story_key.setText(Quest[9]);
                    iv.setImageResource(R.mipmap.locked_10);
                }else{

                }
            }

        }
        dm.close();

    }
}
