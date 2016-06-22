package com.example.sentanu.wikitude_v003;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sentanu.wikitude_v003.tempat_db.tempat_database;

public class popup_item extends AppCompatActivity {

    tempat_database dm;
    String[] Story;
    TextView TV_Story_key;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_item);
        dm = new tempat_database(this);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        DisplayMetrics dmetric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dmetric);
        setTitle("Quest");


        int width = dmetric.widthPixels;
        int height = dmetric.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        Story = getResources().getStringArray(R.array.story);
        TV_Story_key = (TextView)findViewById(R.id.TV_Story_item);
        iv =(ImageView)findViewById(R.id.image_item);

        TV_Story_key.setGravity(Gravity.LEFT);
        TV_Story_key.setText(Story[0]);

        folterStory(position);
    }

    public void folterStory(int position){
        if(position==0){
            TV_Story_key.setText(Story[position]);
            iv.setImageResource(R.mipmap.unlock_1);
        }else if(position==1){
            TV_Story_key.setText(Story[position]);
            iv.setImageResource(R.mipmap.unlock_2);
        }else if(position==2){
            TV_Story_key.setText(Story[position]);
            iv.setImageResource(R.mipmap.unlock_3);
        }else if(position==3){
            TV_Story_key.setText(Story[position]);
            iv.setImageResource(R.mipmap.unlock_4);
        }else if(position==4){
            TV_Story_key.setText(Story[position]);
            iv.setImageResource(R.mipmap.unlock_5);
        }else if(position==5){
            TV_Story_key.setText(Story[position]);
            iv.setImageResource(R.mipmap.unlock_6);
        }else if(position==6){
            TV_Story_key.setText(Story[position]);
            iv.setImageResource(R.mipmap.unlock_7);
        }else if(position==7){
            TV_Story_key.setText(Story[position]);
            iv.setImageResource(R.mipmap.unlock_8);
        }else if(position==8){
            TV_Story_key.setText(Story[position]);
            iv.setImageResource(R.mipmap.unlock_9);
        }else if(position==9){
            TV_Story_key.setText(Story[position]);
            iv.setImageResource(R.mipmap.unlock_10);
        }else{

        }
    }
}
