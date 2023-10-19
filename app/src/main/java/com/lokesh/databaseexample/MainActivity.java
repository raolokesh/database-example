package com.lokesh.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyDBHelper db = new MyDBHelper(MainActivity.this);

//        db.addContact("dfdfa", "+916438495685");
//        db.addContact("adssa", "+916438495685");
//        db.addContact("sdfsd", "+913548895685");
//
        ArrayList<DataModel> data;
        data = db.fetchContact();


        for (int i = 0; i < data.size(); i++) {

            Log.d("data get", "Name: "+data.get(i).name+" Contact: "+data.get(i).phone_no);
        }

        DataModel dataModel = new DataModel();
        dataModel.id = 1;
        dataModel.phone_no = "+914645645454";
        db.updateContact(dataModel);

        data = db.fetchContact();


        for (int i = 0; i < data.size(); i++) {

            Log.d("data get", "Name: "+data.get(i).name+" Contact: "+data.get(i).phone_no);
        }


        db.deleteContact(dataModel);

        data = db.fetchContact();


        for (int i = 0; i < data.size(); i++) {

            Log.d("data get", "Name: "+data.get(i).name+" Contact: "+data.get(i).phone_no);
        }
    }
}