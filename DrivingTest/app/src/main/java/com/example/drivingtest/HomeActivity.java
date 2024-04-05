package com.example.drivingtest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.drivingtest.services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
//    private ArrayList<Question> questions = new ArrayList<>();
//    DataBase dataBase = new DataBase(this,null,null,1);

    Button btnStart;
    TextView txtRs;

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK){
                        Intent data = o.getData();
                        int rs = data.getIntExtra("result",0);
                        txtRs.setText("Result: "+rs);
                    }
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnStart = (Button) findViewById(R.id.start);
        txtRs = (TextView) findViewById(R.id.txtRs);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ExamActivity.class);
                launcher.launch(intent);
            }
        });
//        getQuestions();
        // Lấy dữ liệu từ cơ sở dữ liệu
//        DataBase dataBase = new DataBase(this,"user.sqlite",null,1);
//        Cursor cursor = dataBase.QueryGetData("select * from Questions");
////        SQLiteDatabase db = dataBase.getReadableDatabase();
////
////        Cursor cursor = db.rawQuery("select * from Questions",null);
////
//// Lấy dữ liệu từ con trỏ
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(0);
//            String question = cursor.getString(1);
//            String ideaA = cursor.getString(2);
//            String ideaB = cursor.getString(3);
//            String ideaC = cursor.getString(4);
//            String ideaD = cursor.getString(5);
//            int answer = cursor.getInt(6);
//            Question ques = new Question(id,question,ideaA,ideaB,ideaC,ideaD,answer);
//            questions.add(ques);
//
//            // Làm gì đó với dữ liệu
//        }
//        Toast.makeText(this, questions.size()+"", Toast.LENGTH_SHORT).show();
//        for(int i=0;i<questions.size();i++){
//            Log.d("cccc", questions.get(i).getId()+" "+questions.get(i).getQuestion()+" "+questions.get(i).getIdeaA()+" "+questions.get(i).getIdeaB()+" "+questions.get(i).getIdeaC()+" "+questions.get(i).getIdeaD()+" "+questions.get(i).getAnswer());
//        }


    }

//
//    private void getQuestions() {
//        ApiService.apiService.questionCall().enqueue(new Callback<List<Question>>() {
//            @Override
//            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
//                List<Question> questions = response.body();
//                for(Question question : questions){
//                    Log.i("aaaaaaaaaaaa",question.toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Question>> call, Throwable t) {
//                Toast.makeText(HomeActivity.this, "!ok", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


}