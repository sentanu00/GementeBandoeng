package com.example.sentanu.gemente_bandoeng.tempat_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sentanu on 4/7/2016.
 */
public class tempat_database {

    //mendeklarasikan NAMA_DB DAN DATABASE VERSION
    private static final String NAMA_DB ="GEMENTE_BANDOENG_DB";
    private static final int DB_VERSION=1;
    //---------------------------------------// Tabel Kunci //------------------------------------------
    //mendeklarasikan TABEL KUNCI dan ROW
    private static final String NAMA_TABEL="KUNCI";

    private static final String ROW_ID = "_id";
    private static final String ROW_NAMAKUNCI = "namakunci";
    private static final String ROW_STATUS = "status";
    private static final String ROW_LOCK = "Lock";

    //mendeklarasikan CREATE_TABLE = MEMBUAT TABLE"
    private static final String CREATE_TABLE =
            "create table "+NAMA_TABEL+" ("+ROW_ID+" integer PRIMARY KEY autoincrement, "+ROW_NAMAKUNCI+" text,"+ROW_STATUS+" text,"+ROW_LOCK+" text)";
//--------------------------------------------------------------------------------------------------

    //----------------------------------------// Tabel Login //-----------------------------------------
    //mendeklarasikan TABEL login dan ROW
    private static final String NAMA_TABEL_LOGIN="login";

    private static final String ROW_ID_LOGIN = "_id_login";
    private static final String ROW_NAMA = "nama_player";
    private static final String ROW_STAT_NEWBE = "status_newbe";
    private static final String ROW_STAT_TUTOR = "status_tutor";
    private static final String ROW_SCORE = "score";

    //mendeklarasikan CREATE_TABLE = MEMBUAT TABLE"
    private static final String CREATE_TABLE_LOGIN =
            "create table "+NAMA_TABEL_LOGIN+" ("+ROW_ID_LOGIN+" integer PRIMARY KEY autoincrement, "+ROW_NAMA+" text,"+ROW_STAT_NEWBE+" text,"+ROW_STAT_TUTOR+" text,"+ROW_SCORE+" integer)";

//--------------------------------------------------------------------------------------------------



    //membuat mendeklarasikan itu adalah context
    private final Context context;
    //membuat mendeklarasikan DatabaseOpenHelper itu adalah dbhelper
    private DatabaseOpenHelper dbhelper;
    //membuat mendeklarasikan SQLiteDatabase itu adalah db
    private SQLiteDatabase db;

    //mengambil context untuk mengakses system di android
    public tempat_database(Context ctx) {
        //mendeklarasikan ctx adalah context ( context context di ganti ctx )
        this.context = ctx;
        // membuat DatabaseOpenHelper
        dbhelper = new DatabaseOpenHelper(context);
        //menuliskan DatabaseOpenHelper = SQLiteDatabase
        db = dbhelper.getWritableDatabase();
    }


    //------------------------------------------------------------------------------------------------------------------------------------------------------


    private static class DatabaseOpenHelper extends SQLiteOpenHelper {
        //membuat database
        public DatabaseOpenHelper(Context context) {
            super(context, NAMA_DB, null, DB_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(CREATE_TABLE);
            db.execSQL(CREATE_TABLE_LOGIN);

            /* Insert data to a Table login*/
            db.execSQL("INSERT INTO "
                    + NAMA_TABEL_LOGIN
                    + " ("+ROW_NAMA+","+ROW_STAT_NEWBE+","+ROW_STAT_TUTOR+","+ROW_SCORE+")"
                    + " VALUES ('null', 'true','0', 0);");

            /* Insert data to a Table kunci*/
            db.execSQL("INSERT INTO "
                    + NAMA_TABEL
                    + " ("+ROW_NAMAKUNCI+","+ROW_STATUS+","+ROW_LOCK+")"
                    + " VALUES ('stilasi 1', 'true', 'false');");
            db.execSQL("INSERT INTO "
                    + NAMA_TABEL
                    + " ("+ROW_NAMAKUNCI+","+ROW_STATUS+","+ROW_LOCK+")"
                    + " VALUES ('stilasi 2', 'false', 'false');");
            db.execSQL("INSERT INTO "
                    + NAMA_TABEL
                    + " ("+ROW_NAMAKUNCI+","+ROW_STATUS+","+ROW_LOCK+")"
                    + " VALUES ('stilasi 3', 'false', 'false');");
            db.execSQL("INSERT INTO "
                    + NAMA_TABEL
                    + " ("+ROW_NAMAKUNCI+","+ROW_STATUS+","+ROW_LOCK+")"
                    + " VALUES ('stilasi 4', 'false', 'false');");
            db.execSQL("INSERT INTO "
                    + NAMA_TABEL
                    + " ("+ROW_NAMAKUNCI+","+ROW_STATUS+","+ROW_LOCK+")"
                    + " VALUES ('stilasi 5', 'false', 'false');");
            db.execSQL("INSERT INTO "
                    + NAMA_TABEL
                    + " ("+ROW_NAMAKUNCI+","+ROW_STATUS+","+ROW_LOCK+")"
                    + " VALUES ('stilasi 6', 'false', 'false');");
            db.execSQL("INSERT INTO "
                    + NAMA_TABEL
                    + " ("+ROW_NAMAKUNCI+","+ROW_STATUS+","+ROW_LOCK+")"
                    + " VALUES ('stilasi 7', 'false', 'false');");
            db.execSQL("INSERT INTO "
                    + NAMA_TABEL
                    + " ("+ROW_NAMAKUNCI+","+ROW_STATUS+","+ROW_LOCK+")"
                    + " VALUES ('stilasi 8', 'false', 'false');");
            db.execSQL("INSERT INTO "
                    + NAMA_TABEL
                    + " ("+ROW_NAMAKUNCI+","+ROW_STATUS+","+ROW_LOCK+")"
                    + " VALUES ('stilasi 9', 'false', 'false');");
            db.execSQL("INSERT INTO "
                    + NAMA_TABEL
                    + " ("+ROW_NAMAKUNCI+","+ROW_STATUS+","+ROW_LOCK+")"
                    + " VALUES ('stilasi 10', 'false', 'false');");

        }
        //memperbarui database bila sudah ada
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS "+NAMA_DB);
            onCreate(db);

        }
    }

    //---------------------------------------------------------------------------------------------


    //menutup DatabaseOpenHelper
    public void close() {
        dbhelper.close();
    }


    //menambahkan pada row
    public void addRow(String namakunci, String status) {

        ContentValues values = new ContentValues();
        values.put(ROW_NAMAKUNCI, namakunci);
        values.put(ROW_STATUS, status);

        try {
            //menambahkan nama tabel bila tidak akan error
            // db.delete(NAMA_TABEL, null, null);
            db.insert(NAMA_TABEL, null, values);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
    }

    public void editRow(String patokankunci, String status, String lock) {

        ContentValues values = new ContentValues();
        values.put(ROW_STATUS, status);
        values.put(ROW_LOCK, lock);

        try {
            //menambahkan nama tabel bila tidak akan error
            // db.delete(NAMA_TABEL, null, null);
            db.update(NAMA_TABEL, values, ROW_NAMAKUNCI + "='" + patokankunci + "'", null);
            //db.insert(NAMA_TABEL, null, values);

        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
    }

    public void editRowPlayer( String nama, String status, int skor) {

        ContentValues values = new ContentValues();
        values.put(ROW_NAMA, nama);
        values.put(ROW_STAT_NEWBE, status);
        values.put(ROW_SCORE, skor);

        try {
            //menambahkan nama tabel bila tidak akan error
            // db.delete(NAMA_TABEL, null, null);
            db.update(NAMA_TABEL_LOGIN, values, ROW_ID_LOGIN + "= 1 ", null);
            //db.insert(NAMA_TABEL, null, values);

        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
    }

    public void editRowPlayer(String status, String tutor) {

        ContentValues values = new ContentValues();
        values.put(ROW_STAT_NEWBE, status);
        values.put(ROW_STAT_TUTOR, tutor);

        try {
            //menambahkan nama tabel bila tidak akan error
            db.update(NAMA_TABEL_LOGIN, values, ROW_ID_LOGIN + "= 1 ", null);
            //db.insert(NAMA_TABEL, null, values);

        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
    }
    public void editRowPlayer(int skor) {

        ContentValues values = new ContentValues();
        values.put(ROW_SCORE, skor);

        try {
            //menambahkan nama tabel bila tidak akan error
            db.update(NAMA_TABEL_LOGIN, values, ROW_ID_LOGIN + "= 1 ", null);
            //db.insert(NAMA_TABEL, null, values);

        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
    }

    //mengedit pada row
    public void editRow( String status) {

        ContentValues values = new ContentValues();
        values.put(ROW_STATUS, status);

        try {
            //menambahkan nama tabel bila tidak akan error
            // db.delete(NAMA_TABEL, null, null);
            //db.insert(NAMA_TABEL, null, values);
            db.update(NAMA_TABEL, values, "namakunci='alun alun'",null);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString());
            e.printStackTrace();
        }
    }


    //------------------------------------------------------------------------------------------------------------------------------------------------------------
    //membuat array pada table layout
    public ArrayList<ArrayList<Object>> ambilSemuaBaris() {
        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            cur = db.query(NAMA_TABEL,
                    new String[] { ROW_ID, ROW_NAMAKUNCI, ROW_STATUS, ROW_LOCK }, null, null,
                    null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getLong(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataList.add(cur.getString(3));

                    dataArray.add(dataList);

                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;

    }

    //membuat array pada table layout
    public ArrayList<ArrayList<Object>> ambilSemuaBarisPlayer() {
        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            cur = db.query(NAMA_TABEL_LOGIN,
                    new String[] { ROW_ID_LOGIN, ROW_NAMA, ROW_STAT_NEWBE,ROW_STAT_TUTOR, ROW_SCORE }, null, null,
                    null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getLong(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataList.add(cur.getString(3));
                    dataList.add(cur.getString(4));

                    dataArray.add(dataList);

                } while (cur.moveToNext());

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DEBE ERROR", e.toString());
        }
        return dataArray;

    }


}
