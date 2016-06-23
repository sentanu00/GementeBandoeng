package com.example.sentanu.wikitude_v003;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sentanu.wikitude_v003.tempat_db.tempat_database;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    tempat_database dm;


    String[] namalokasi, lat, longi, radius, MarkerID;
    String assetAR;

    Button btn_camera, btn_tahun, item, menu_sementara, GTTarget, GTMyloc;
    LatLng Bandung = new LatLng(-6.9235085,107.6093448);
    LatLng mylocation;//-----------------------------------------------------------------lokasi user

    //inisialisasi lokasi target (just inisialisasi) ingat cuma inisialisasi
    LatLng Target , targetShare= null;
    private LocationManager locManager;


    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;
    private final int[] MAP_TYPES = { GoogleMap.MAP_TYPE_SATELLITE,
            GoogleMap.MAP_TYPE_NORMAL,
            GoogleMap.MAP_TYPE_HYBRID,
            GoogleMap.MAP_TYPE_TERRAIN,
            GoogleMap.MAP_TYPE_NONE };

    private int curMapTypeIndex = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        dm = new tempat_database(this);


        menu_sementara = (Button)findViewById(R.id.btn_key);
        btn_camera = (Button)findViewById(R.id.btn_camera);
        btn_tahun = (Button)findViewById(R.id.btn_tahun);
        GTTarget = (Button)findViewById(R.id.btn_go_to_target);
        GTMyloc = (Button)findViewById(R.id.btn_go_to_myloc);


        namalokasi = getResources().getStringArray(R.array.NamaLokasi);
        lat = getResources().getStringArray(R.array.Lat);
        longi = getResources().getStringArray(R.array.Longi);
        radius = getResources().getStringArray(R.array.Radius);
        //---------------------------------------------------------------------------------------------- ambil lokasi user
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location =  locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        mylocation = new LatLng(location.getLatitude(),location.getLongitude());
        //Toast.makeText(MapsActivity.this, location.getLatitude() + " + " + location.getLongitude(), Toast.LENGTH_LONG).show();
        //----------------------------------------------------------------------------------------------

        //set button search target on---------------------------------------------------------------
        GTTarget.setVisibility(View.GONE);
        GTMyloc.setVisibility(View.VISIBLE);

        GTTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GTTarget.setVisibility(View.GONE);
                GTMyloc.setVisibility(View.VISIBLE);
                setingKamera(targetShare);
            }
        });
        GTMyloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GTTarget.setVisibility(View.VISIBLE);
                GTMyloc.setVisibility(View.GONE);
                setingKamera(mylocation);
            }
        });

        //------------------------------------------------------------------------------------------
        menu_sementara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MapsActivity.this,menu_sementara.class);
                i.putExtra("latitude",targetShare.latitude +"");
                i.putExtra("longitude",targetShare.longitude+"");
                startActivity(i);
            }
        });
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(set_button_camera_in_radius(mylocation,targetShare)){
                    Intent i=new Intent(MapsActivity.this,MainActivity.class);

                    //kirim lokasi asset AR dan menuju ke activity AR
                    i.putExtra("Asset_key", assetAR);
                    startActivity(i);
                }else{
                    Toast.makeText(MapsActivity.this, "belum didalam radius", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_tahun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MapsActivity.this,popupQuest.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Keluar?")
                .setMessage("Benarkah anda ingin keluar?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        //MapsActivity.super.onBackPressed();
                        //finish();
                        // System.exit(0);

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                        startActivity(intent);
                        finish();
                        System.exit(0);
                    }
                }).create().show();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        checkPermisionMyLocation();
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        open_all_quest();

    }


    public void checkPermisionMyLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }
    }

    public void setingKamera(LatLng target){
        //posisi awal dan posisi zoom kamera
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Bandung, 12));

        //aktifkan zoom in pada kamera
        mMap.animateCamera(CameraUpdateFactory.zoomIn());

        //lakukan animasi zoom in ke level 10, dengan animasi durasi 2 detik
        mMap.animateCamera(CameraUpdateFactory.zoomTo(4),2000,null);

        //mengatur fokus pada tampilan target
        CameraPosition cm = new CameraPosition.Builder()
                .target(target)     //sementara target ada di luar
                .zoom(15)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cm));
    }

    public void buatMarker(LatLng target, int lks_mrkr_img, String nama_marker){
        mMap.addMarker(new MarkerOptions()
                .position(target)
                .title(nama_marker)
                .snippet("Population: 4,137,400")
                .icon(BitmapDescriptorFactory.fromResource(lks_mrkr_img)));
    }

    public void buatLingkaran(LatLng target){

        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(target.latitude,target.longitude))
                .radius(100)// In meters
                .strokeColor(0xffff9914)
                .fillColor(0x40eeff00)
                .strokeWidth(1);

        mMap.addCircle(circleOptions);

    }

    public Boolean set_button_camera_in_radius(LatLng myLoc, LatLng targetLoc){
        Location loc1 = new Location("");
        loc1.setLatitude(myLoc.latitude);
        loc1.setLongitude(myLoc.longitude);
        Location loc2 = new Location("");
        loc2.setLatitude(targetLoc.latitude);
        loc2.setLongitude(targetLoc.longitude);

        float distanceInMeters = loc1.distanceTo(loc2); //---------------------------------- jarak dalam meter
        if(distanceInMeters<100){
            return true;
        }else {
            return false;
        }

    }

    public void tahun_quest(int lokasi_gambar){

        //kondisi if menyesuaikan database yang sudah di kerjakan..:D
        btn_tahun.setBackgroundResource(lokasi_gambar);

    }

    public void open_all_quest(){

        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();
        for (int posisi = 0; posisi < data.size(); posisi++) {
            ArrayList<Object> baris = data.get(posisi);

            LatLng latlong = new LatLng(Double.parseDouble(lat[posisi]), Double.parseDouble(longi[posisi]));
            this.Target = latlong;

            //menentukan konsidi dr database
            if(baris.get(2).toString().equals("true")){
                Toast.makeText(MapsActivity.this, posisi +"", Toast.LENGTH_LONG).show();

                //set lokasi asset AR .html
                switch(posisi) {
                    case 0:
                        this.assetAR = "file:///android_asset/GD_Dikleur/index.html";
                        break;
                    case 1:
                        this.assetAR = "file:///android_asset/GD_Dikleur/index.html";
                        break;
                    case 2:
                        this.assetAR = "file:///android_asset/GD_Dikleur/index.html";
                        break;
                    case 3:
                        this.assetAR = "file:///android_asset/GD_Dikleur/index.html";
                        break;
                    case 4:
                        this.assetAR = "file:///android_asset/GD_Jiwasraya/index.html";
                        break;
                    case 5:
                        this.assetAR = "file:///android_asset/GD_Jiwasraya/index.html";
                        break;
                    case 6:
                        this.assetAR = "file:///android_asset/GD_Jiwasraya/index.html";
                        break;
                    case 7:
                        this.assetAR = "file:///android_asset/GD_Denis/index.html";
                        break;
                    case 8:
                        this.assetAR = "file:///android_asset/GD_Denis/index.html";
                        break;
                    case 9:
                        this.assetAR = "file:///android_asset/GD_Denis/index.html";
                        break;
                    default:
                        this.assetAR = "file:///android_asset/GD_Dikleur/index.html";
                }

                //tahun_quest(R.drawable.q1);
                setingKamera(latlong);
                //inisialisasikan semua koordinat, marker warna n greyscalenya di atas
                buatMarker(latlong, R.mipmap.sample_marker_48, namalokasi[posisi]);
                buatLingkaran(latlong);
                this.targetShare = latlong;
            }

        }
        dm.close();

    }

    public String search_quest(String namakunci){

        String ketemu = null;
        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();
        for (int posisi = 0; posisi < data.size(); posisi++) {
            ArrayList<Object> baris = data.get(posisi);

            LatLng latlong = new LatLng(Double.parseDouble(lat[posisi]), Double.parseDouble(longi[posisi]));

            //menentukan konsidi dr database
            if(baris.get(1).toString().equals(namakunci)){

                ketemu = namakunci;
            }

        }
        dm.close();
        return ketemu;
    }
}
