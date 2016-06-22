package com.example.sentanu.wikitude_v003;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sentanu.wikitude_v003.tempat_db.tempat_database;

import java.util.ArrayList;

public class scene_tutorial extends AppCompatActivity {


    tempat_database dm;
    //String[] Story;
    //TextView TV_Story_op;
    ImageView iv1, btn_next;
    int tutor_state=0, x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_tutorial);
        dm = new tempat_database(this);

        //Story = getResources().getStringArray(R.array.story_op);
        //TV_Story_op = (TextView)findViewById(R.id.TV_Story_op);
        iv1 =(ImageView)findViewById(R.id.image_story_op);
        btn_next = (ImageView)findViewById(R.id.button_next);

        //TV_Story_op.setGravity(Gravity.CENTER_HORIZONTAL);


        set_player_profil();

    }

    public void button_next(final int x){
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                if(x==0){

                    try {
                        dm.editRowPlayer("true","1");
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "gagal simpan, " + e.toString(),
                                Toast.LENGTH_LONG).show();
                    }

                    i=new Intent(scene_tutorial.this,scene_tutorial.class);
                    startActivity(i);

                }else if(x==1){
                    try {
                        dm.editRowPlayer("true","2");
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "gagal simpan, " + e.toString(),
                                Toast.LENGTH_LONG).show();
                    }

                    i=new Intent(scene_tutorial.this,scene_tutorial.class);
                    startActivity(i);
                }else if(x==2){
                    try {
                        dm.editRowPlayer("false","0");
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "gagal simpan, " + e.toString(),
                                Toast.LENGTH_LONG).show();
                    }

                    i=new Intent(scene_tutorial.this,tutorial.class);
                    i.putExtra("key_tutor", 0);
                    startActivity(i);
                }


            }
        });
    }


    public void set_player_profil(){

        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBarisPlayer();
        for (int posisi = 0; posisi < data.size(); posisi++) {
            ArrayList<Object> baris = data.get(posisi);

            //menentukan konsidi dr database
            if(baris.get(3).toString().equals("0")) {
                iv1.setImageResource(R.mipmap.cutscene_1);
                //    TV_Story_op.setText(Story[0]);
                button_next(0);
            }else if(baris.get(3).toString().equals("1")){
                iv1.setImageResource(R.mipmap.cutscene_2);
                //   TV_Story_op.setText(Story[1]);
                button_next(1);
            }else if(baris.get(3).toString().equals("2")){
                iv1.setImageResource(R.mipmap.cutscene_3);
                // TV_Story_op.setText(Story[2]);
                button_next(2);
            }

        }

    }
}
