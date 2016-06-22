package com.example.sentanu.wikitude_v003;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.sentanu.wikitude_v003.tempat_db.tempat_database;

import java.util.ArrayList;
import java.util.HashMap;

public class item extends AppCompatActivity implements AdapterView.OnItemClickListener {

    tempat_database dm;

    protected ListView lv;
    protected ListAdapter adapter;
    SimpleAdapter Adapter;
    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> mylist;
    String[] key_name;
    String[] Gbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        dm = new tempat_database(this);

        lv = (ListView) findViewById(R.id.list_key_unlock);

        key_name = new String[] {"Anjing", "Kambing", "Kucing", "Kuda", "Naga"};
        Gbr = new String[] {Integer.toString(R.mipmap.cube),
                Integer.toString(R.mipmap.cube),
                Integer.toString(R.mipmap.cube),
                Integer.toString(R.mipmap.cube),
                Integer.toString(R.mipmap.cube) };

        mylist = new ArrayList<HashMap<String,String>>();

        open_all_quest();
        Adapter = new SimpleAdapter(this, mylist, R.layout.layout_key_unlock,
                new String[] {"name","gbr"}, new int[] {R.id.tv_nama, R.id.imV});
        lv.setAdapter(Adapter);

        lv.setOnItemClickListener(this);
    }

    @Override
    public void onBackPressed(){
        Intent i=new Intent(item.this,MapsActivity.class);
        startActivity(i);
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        intent.setClass(this, popup_item.class);
        intent.putExtra("position", position);
        // Or / And
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void open_all_quest(){

        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();
        for (int posisi = 0; posisi < data.size(); posisi++) {
            ArrayList<Object> baris = data.get(posisi);

            //menentukan konsidi dr database
            if(baris.get(3).toString().equals("true")){

                map = new HashMap<String, String>();
                map.put("name", baris.get(1).toString());
                map.put("gbr", Integer.toString(R.mipmap.cube));//---------------- gambar sementara
                mylist.add(map);
            }
        }
        dm.close();

    }
}
