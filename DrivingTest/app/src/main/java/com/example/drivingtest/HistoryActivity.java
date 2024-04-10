package com.example.drivingtest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drivingtest.models.DataBase;
import com.example.drivingtest.models.Result;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    DataBase dataBase;

    ListView lv;
    ArrayList<Result> results = new ArrayList<>();

    ResultAdapter adapter;
    int user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        lv = findViewById(R.id.lv);

        dataBase = new DataBase(this, "user.sqlite", null, 1);

        getHomeIntentData();
        getResultData();

        adapter = new ResultAdapter(this,R.layout.result_item,results);
        lv.setAdapter(adapter);

    }

    private void getResultData() {
        String getUserData = "SELECT * FROM results WHERE user_id = "+user_id;
        Cursor cursor = dataBase.QueryGetData(getUserData);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            int user_id = cursor.getInt(1);
            int result = cursor.getInt(2);
            results.add(new Result(id,user_id,result));
        }
    }

    private void getHomeIntentData() {
        Intent intent = this.getIntent();
        user_id = intent.getIntExtra("user_id",0);
    }
}