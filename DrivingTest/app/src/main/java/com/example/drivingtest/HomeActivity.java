package com.example.drivingtest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ArrayList<Question> questions = new ArrayList<>();
    DataBase dataBase = new DataBase(this,null,null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DataBase dataBase = new DataBase(this,"user.sqlite",null,1);
        Cursor cursor = dataBase.QueryGetData("select * from Questions");
//        SQLiteDatabase db = dataBase.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery("select * from Questions",null);
//
// Lấy dữ liệu từ con trỏ
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String question = cursor.getString(1);
            String ideaA = cursor.getString(2);
            String ideaB = cursor.getString(3);
            String ideaC = cursor.getString(4);
            String ideaD = cursor.getString(5);
            int answer = cursor.getInt(6);
            Question ques = new Question(id,question,ideaA,ideaB,ideaC,ideaD,answer);
            questions.add(ques);

            // Làm gì đó với dữ liệu
        }
        Toast.makeText(this, questions.size()+"", Toast.LENGTH_SHORT).show();
        for(int i=0;i<questions.size();i++){
            Log.d("cccc", questions.get(i).getId()+" "+questions.get(i).getQuestion()+" "+questions.get(i).getIdeaA()+" "+questions.get(i).getIdeaB()+" "+questions.get(i).getIdeaC()+" "+questions.get(i).getIdeaD()+" "+questions.get(i).getAnswer());
        }


    }



}