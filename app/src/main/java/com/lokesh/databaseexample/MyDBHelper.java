package com.lokesh.databaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Queue;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final  String DATABASE_NAME= "ContactDB";
    private static final  int DATABASE_VERSION = 1;
    private static final String TABLE_CONTACT = "Contact";
    private static final String KEY_ID = "id" ;
    private static final String KEY_NAME = "name";
    private static final String KEY_CONTACT = "phone_no";


    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //  CREATE TABLE TABLE_NAME ( TABLE COLUMNS WITH EXPLAIN DATA TYPE )
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_CONTACT+
                "("+ KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +KEY_NAME+" TEXT,"+KEY_CONTACT+"  TEXT)");




        // CREATE TABLE CONTACT (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, PHONE_NO TEXT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTACT);
        onCreate(sqLiteDatabase);

    }

    public void  addContact(String name, String phone_no){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_CONTACT,phone_no);

        sqLiteDatabase.insert(TABLE_CONTACT,null,values);

    }

    public ArrayList<DataModel> fetchContact(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_CONTACT;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        ArrayList<DataModel> getdata = new ArrayList<>();

        while (cursor.moveToNext()) {

            DataModel model = new DataModel();

            model.id = cursor.getInt(0);
            model.name = cursor.getString(1);
            model.phone_no = cursor.getString(2);

            getdata.add(model);
        }
        cursor.close();

        return getdata;
    }

    public  void  updateContact(DataModel dataModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put(KEY_NAME,dataModel.name);
        values.put(KEY_CONTACT,dataModel.phone_no);
        sqLiteDatabase.update(TABLE_CONTACT,values,KEY_ID +" == "+dataModel.id,null);

    }


    public void  deleteContact(DataModel dataModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_CONTACT,KEY_ID +" == "+dataModel.id,null);
    }

}
