package com.example.sentanu.gemente_bandoeng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sentanu.gemente_bandoeng.tempat_db.tempat_database;
import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.StartupConfiguration;

public class AR_Wikitude extends AppCompatActivity {

    tempat_database dm;
    Button getTarget;

    protected ArchitectView architectView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar__wikitude);


        dm = new tempat_database(this);

        String serial_key = getResources().getString(R.string.serial_key);
        this.architectView = (ArchitectView)this.findViewById( R.id.architectView );
        final StartupConfiguration config = new StartupConfiguration(serial_key);
        this.architectView.onCreate(config);

        getTarget =(Button)findViewById(R.id.btn_get_target);
        getTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);

        architectView.onPostCreate();

        try {
            this.architectView.load( "file:///android_asset/index.html" );  // tempat memasukan alamat html dan lain lain..:D
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



}
