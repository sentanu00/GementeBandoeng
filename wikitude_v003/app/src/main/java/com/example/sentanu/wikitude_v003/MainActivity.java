package com.example.sentanu.wikitude_v003;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sentanu.wikitude_v003.tempat_db.tempat_database;
import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.StartupConfiguration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    tempat_database dm;
    Button getTarget;
    String assetAR;

    protected ArchitectView architectView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm = new tempat_database(this);

        //mengambil kiriman data asset dari maps activity
        Bundle extras = getIntent().getExtras();
        assetAR = extras.getString("Asset_key");

        String serial_key = getResources().getString(R.string.serial_key);
        this.architectView = (ArchitectView)this.findViewById( R.id.architectView );
        final StartupConfiguration config = new StartupConfiguration(serial_key);
        this.architectView.onCreate(config);

        getTarget =(Button)findViewById(R.id.btn_get_target);
        getTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter_quest();
            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);

        architectView.onPostCreate();

        try {
            //set asset AR yang telah diterima
            this.architectView.load( this.assetAR );
            // "file:///android_asset/GD_Dikleur/index.html" 1-4
            // "file:///android_asset/GD_Jiwasraya/index.html" 5-7
            // "file:///android_asset/GD_Denis/index.html" 8-10
        }catch (Exception e){

        }
    }
    @Override
    protected void onResume(){
        super.onResume();

        architectView.onResume();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();

        architectView.onDestroy();
    }
    @Override
    protected void onPause(){
        super.onPause();

        architectView.onPause();
    }


    //----------------------------------------------------------------------------------------------

    public void filter_quest(){

        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();
        for (int posisi = 0; posisi < data.size(); posisi++) {
            ArrayList<Object> baris = data.get(posisi);



            //menentukan konsidi dr database
            if(baris.get(2).toString().equals("true")){

                if(baris.get(1).toString().equals("stilasi 1")){
                    editData("stilasi 1", "false", "true");
                    editData("stilasi 2", "true", "false");
                    dm.editRowPlayer(100);
                    reward("stilasi 1");
                }else if(baris.get(1).toString().equals("stilasi 2")){
                    editData("stilasi 2", "false", "true");
                    editData("stilasi 3", "true", "false");
                    dm.editRowPlayer(200);
                    reward("stilasi 2");
                }else if(baris.get(1).toString().equals("stilasi 3")){
                    editData("stilasi 3", "false", "true");
                    editData("stilasi 4", "true", "false");
                    dm.editRowPlayer(300);
                    reward("stilasi 3");
                }else if(baris.get(1).toString().equals("stilasi 4")){
                    editData("stilasi 4", "false", "true");
                    editData("stilasi 5", "true", "false");
                    dm.editRowPlayer(400);
                    reward("stilasi 4");
                }else if(baris.get(1).toString().equals("stilasi 5")){
                    editData("stilasi 5", "false", "true");
                    editData("stilasi 6", "true", "false");
                    dm.editRowPlayer(500);
                    reward("stilasi 5");
                }else if(baris.get(1).toString().equals("stilasi 6")){
                    editData("stilasi 6", "false", "true");
                    editData("stilasi 7", "true", "false");
                    dm.editRowPlayer(600);
                    reward("stilasi 6");
                }else if(baris.get(1).toString().equals("stilasi 7")){
                    editData("stilasi 7", "false", "true");
                    editData("stilasi 8", "true", "false");
                    dm.editRowPlayer(700);
                    reward("stilasi 7");
                }else if(baris.get(1).toString().equals("stilasi 8")){
                    editData("stilasi 8", "false", "true");
                    editData("stilasi 9", "true", "false");
                    dm.editRowPlayer(800);
                    reward("stilasi 8");
                }else if(baris.get(1).toString().equals("stilasi 9")){
                    editData("stilasi 9", "false", "true");
                    editData("stilasi 10", "true", "false");
                    dm.editRowPlayer(900);
                    reward("stilasi 9");
                }else if(baris.get(1).toString().equals("stilasi 10")){
                    editData("stilasi 10", "false", "true");
                    editData("stilasi 1", "true", "true");//       harusnya mengarah ke lokasi untuk membuak hadiah utama(monumen bandung lautan api besar atau utama)
                    dm.editRowPlayer(1000);

                    new AlertDialog.Builder(this)
                            .setTitle("Selamat")
                            .setMessage("Semua Quest Komlit! \n selakan Buka Treasure yang telah tersedia")
                            .setPositiveButton("buka Treasure", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {
                                    Intent i=new Intent(MainActivity.this,key.class);
                                    startActivity(i);
                                }
                            }).create().show();/*

                    reward("stilasi 10");
                    Intent i=new Intent(MainActivity.this,key.class);
                    startActivity(i);*/
                }else{

                }
            }

        }
        dm.close();
    }

    public void reward(String Quest){
        new AlertDialog.Builder(this)
                .setTitle("Selamat")
                .setMessage("Quest "+Quest+" Sukses!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent i=new Intent(MainActivity.this,MapsActivity.class);
                        startActivity(i);
                    }
                }).create().show();
    }

    private void editData(String kunci, String status, String lock){
        dm.editRow(kunci, status, lock);
    }
    @Override
    public void onBackPressed(){
        Intent i=new Intent(MainActivity.this,MapsActivity.class);
        startActivity(i);
    }
}