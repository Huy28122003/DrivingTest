package com.example.drivingtest;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drivingtest.models.DataBase;

public class HomeActivity extends AppCompatActivity {
//    private ArrayList<Question> questions = new ArrayList<>();
//    DataBase dataBase = new DataBase(this,null,null,1);

    Button btnStart,btnTrain,btnHistory,btnMore;
    TextView txtRs,txtRsMax;

    DataBase dataBase;

    int userId;

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK){
                        Intent data = o.getData();
                        int rs = data.getIntExtra("result",0);
                        txtRs.setText("Result: "+rs);
                        dataBase.QuerySetData("insert into results(user_id,result) values("+userId+","+rs+")");
                    }
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        map();

        // Gọi database
        dataBase = new DataBase(this, "user.sqlite", null, 1);

        // Lấy dữ liệu từ người dùng đăng nhập
        getUserData();

        showResult();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ExamActivity.class);
                launcher.launch(intent);
            }
        });
        btnTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,TrainingActivity.class);
                startActivity(intent);
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,HistoryActivity.class);
                intent.putExtra("user_id",userId);
                startActivity(intent);
            }
        });
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://onthilaixe.vn/"));
                startActivity(intent);
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

    private void showResult() {
        // Tạo bảng lưu kết quả
        String createTBUser = "create table if not exists results (id integer primary key autoincrement, user_id integer REFERENCES users(id), result integer)";
        dataBase.QuerySetData(createTBUser);

        // Lấy dữ liệu từ bảng lưu kết quả
        String getDataUser = "select * from results where user_id = "+userId;
        Cursor cursor = dataBase.QueryGetData(getDataUser);
        int maxRs = 0;
        while (cursor.moveToNext()) {
            int result = cursor.getInt(2);
            if(result>maxRs){
                maxRs = result;
            }
        }
        txtRsMax.setText("Tốt nhất: "+maxRs);
    }

    private void getUserData() {
        Intent intent = this.getIntent();
        userId = intent.getIntExtra("user_id",0);
        Toast.makeText(this, userId+"", Toast.LENGTH_SHORT).show();
    }

    private void map(){
        btnStart = (Button) findViewById(R.id.start);
        txtRs = (TextView) findViewById(R.id.txtRs);
        txtRsMax = (TextView) findViewById(R.id.txtRsMax);
        btnTrain = (Button) findViewById(R.id.btnTrain);
        btnHistory = (Button) findViewById(R.id.btnHistory);
        btnMore = (Button) findViewById(R.id.btnMore);
    }





}