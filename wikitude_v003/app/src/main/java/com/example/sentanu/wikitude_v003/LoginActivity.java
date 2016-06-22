package com.example.sentanu.wikitude_v003;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sentanu.wikitude_v003.tempat_db.tempat_database;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText input_name;
    Button btn_regis;
    tempat_database dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dm = new tempat_database(this);

        openplayer();
        input_name = (EditText)findViewById(R.id.input_name);
        btn_regis =(Button)findViewById(R.id.btn_regis);

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dm.editRowPlayer(input_name.getText().toString(), "true", 0);//------------if intro dah ada, rubah ke true
                Intent i = new Intent(LoginActivity.this, scene_tutorial.class);
                startActivity(i);
            }
        });
    }

    public void openplayer(){

        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBarisPlayer();
        for (int posisi = 0; posisi < data.size(); posisi++) {
            ArrayList<Object> baris = data.get(posisi);

            //menentukan konsidi dr database
            if(baris.get(2).toString().equals("false")){
                Intent i=new Intent(LoginActivity.this,MapsActivity.class);
                startActivity(i);
            }


        }

    }
}
