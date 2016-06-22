package com.example.sentanu.wikitude_v003;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sentanu.wikitude_v003.tempat_db.tempat_database;

public class ar extends AppCompatActivity {
    tempat_database dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);
        dm = new tempat_database(this);
    }
    public void kunci(View v) {
        Intent i;
        if (v.getId() == R.id.kunci1) {
            editData("stilasi 1", "false", "true");
            editData("stilasi 2", "true", "false");
            dm.editRowPlayer(100);
            reward("stilasi 1");
        } else if (v.getId() == R.id.kunci2) {
            editData("stilasi 2", "false", "true");
            editData("stilasi 3", "true", "false");
            dm.editRowPlayer(200);
            reward("stilasi 2");
        } else if (v.getId() == R.id.kunci3) {
            editData("stilasi 3", "false", "true");
            editData("stilasi 4", "true", "false");
            dm.editRowPlayer(300);
            reward("stilasi 3");
        }else if (v.getId() == R.id.kunci4) {
            editData("stilasi 4", "false", "true");
            editData("stilasi 5", "true", "false");
            dm.editRowPlayer(400);
            reward("stilasi 4");
        }else if (v.getId() == R.id.kunci5) {
            editData("stilasi 5", "false", "true");
            editData("stilasi 6", "true", "false");
            dm.editRowPlayer(500);
            reward("stilasi 5");
        }else if (v.getId() == R.id.kunci6) {
            editData("stilasi 6", "false", "true");
            editData("stilasi 7", "true", "false");
            dm.editRowPlayer(600);
            reward("stilasi 6");
        }else if (v.getId() == R.id.kunci7) {
            editData("stilasi 7", "false", "true");
            editData("stilasi 8", "true", "false");
            dm.editRowPlayer(700);
            reward("stilasi 7");
        }else if (v.getId() == R.id.kunci8) {
            editData("stilasi 8", "false", "true");
            editData("stilasi 9", "true", "false");
            dm.editRowPlayer(800);
            reward("stilasi 8");
        }else if (v.getId() == R.id.kunci9) {
            editData("stilasi 9", "false", "true");
            editData("stilasi 10", "true", "false");
            dm.editRowPlayer(900);
            reward("stilasi 9");
        }else if (v.getId() == R.id.kunci10) {
            editData("stilasi 10", "false", "true");
            editData("stilasi 1", "true", "true");//       harusnya mengarah ke lokasi untuk membuak hadiah utama(monumen bandung lautan api besar atau utama)
            dm.editRowPlayer(1000);
            new AlertDialog.Builder(this)
                    .setTitle("Selamat")
                    .setMessage("Semua Quest Komlit! \n selakan Buka Treasure yang telah tersedia")
                    .setPositiveButton("buka Treasure", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent i=new Intent(ar.this,key.class);
                            startActivity(i);
                        }
                    }).create().show();
        } else {
            editData("stilasi 1", "true", "false");
            editData("stilasi 2", "false", "false");
            editData("stilasi 3", "false", "false");
            editData("stilasi 4", "false", "false");
            editData("stilasi 5", "false", "false");
            editData("stilasi 6", "false", "false");
            editData("stilasi 7", "false", "false");
            editData("stilasi 8", "false", "false");
            editData("stilasi 9", "false", "false");
            editData("stilasi 10", "false", "false");
            dm.editRowPlayer(0);
            i = new Intent(ar.this, MapsActivity.class);
            startActivity(i);
        }
    }

    public void reward(String Quest){
        new AlertDialog.Builder(this)
                .setTitle("Selamat")
                .setMessage("Quest "+Quest+" Sukses!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent i=new Intent(ar.this,ar.class);
                        startActivity(i);
                    }
                }).create().show();
    }

    private void editData(String kunci, String status, String lock){
        dm.editRow(kunci, status, lock);
    }


    @Override
    public void onBackPressed(){
        Intent i=new Intent(ar.this,MapsActivity.class);
        startActivity(i);
    }
}
