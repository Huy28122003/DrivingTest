package com.example.drivingtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtPass;
    Button btnLogin,btnSignUp;
    DataBase dataBase;

    private ArrayList<User> users;
    private ArrayList<Question> questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new ArrayList<>();
        questions = new ArrayList<>();

        dataBase = new DataBase(this, "user.sqlite", null, 1);

        String createTBUser = "create table if not exists users (id integer primary key autoincrement, name varchar(20), password pass(8))";
        dataBase.QuerySetData(createTBUser);

        String createTableQues = "create table if not exists questions (id integer primary key autoincrement, question varchar(200) not null, ideaA varchar(200) not null, ideaB varchar(200) not null, ideaC varchar(200) not null, ideaD varchar(200) not null, answer integer not null)";
        dataBase.QuerySetData(createTableQues);

//        String insertData = "insert into questions values " +
//                "(null, 'Biển báo nào dưới đây chỉ dẫn cho người lái xe phải giảm tốc độ?', 'Biển báo giới hạn tốc độ', 'Biển báo cấm vượt', 'Biển báo nguy hiểm', 'Biển báo chỉ dẫn hướng', 1)," +
//                "(null, 'Khi lái xe trên đường cao tốc, người lái xe phải giữ khoảng cách an toàn với xe phía trước ít nhất là bao nhiêu mét?', '50 mét', '100 mét', '150 mét', '200 mét', 2)," +
//                "(null, 'Khi muốn chuyển làn đường, người lái xe phải thực hiện thao tác nào trước tiên?', 'Bật đèn xi nhan', 'Kiểm tra gương chiếu hậu', 'Nhìn trước, nhìn sau', 'Cả A, B và C', 4)," +
//                "(null, 'Khi gặp đèn đỏ, người lái xe phải dừng xe ở đâu?', 'Trước vạch dừng', 'Sau vạch dừng', 'Trên vạch dừng', 'Bất kỳ vị trí nào trên đường', 1)," +
//                "(null, 'Khi lái xe trong điều kiện thời tiết xấu, người lái xe nên làm gì?', 'Giảm tốc độ', 'Tăng tốc độ', 'Giữ nguyên tốc độ', 'Phanh gấp', 1)," +
//                "(null, 'Khi gặp chướng ngại vật trên đường, người lái xe nên làm gì?', 'Phanh gấp', 'Đánh lái tránh chướng ngại vật', 'Cả A và B', 'Không làm gì cả', 3)," +
//                "(null, 'Khi lái xe trên đường đèo, người lái xe nên làm gì?', 'Giảm tốc độ', 'Tăng tốc độ', 'Giữ nguyên tốc độ', 'Phanh gấp', 1)," +
//                "(null, 'Khi lái xe trên đường đèo, người lái xe nên sử dụng số nào?', 'Số 1', 'Số 2', 'Số 3', 'Số 4', 1)," +
//                "(null, 'Khi lái xe trên đường đèo, người lái xe nên chú ý điều gì?', 'Các phương tiện giao thông khác', 'Tình trạng mặt đường', 'Biển báo giao thông', 'Cả A, B và C', 4)," +
//                "(null, 'Khi lái xe trên đường đèo, người lái xe nên làm gì khi gặp xe đi ngược chiều?', 'Bấm còi', 'Nhường đường', 'Tăng tốc độ', 'Phanh gấp', 2);";
//        dataBase.QuerySetData(insertData);



        //dataBase.QuerySetData("insert into users(id,name,password) values (null,'huy','1')");
//        for(int i = 6; i < 8; i++){
//            dataBase.QuerySetData("delete from users where id = "+i);
//        }


        LoadDataUser();
        LoadDataQuestion();
        Toast.makeText(this, questions.size()+"", Toast.LENGTH_SHORT).show();
        for(int i = 0; i < questions.size(); i++){
            Log.d("qqqqq",questions.get(i).getId()+" "+questions.get(i).getQuestion()+" "+questions.get(i).getIdeaA()+" "+questions.get(i).getIdeaB()+" "+questions.get(i).getIdeaC()+" "+questions.get(i).getIdeaD()+" "+questions.get(i).getAnswer());

        }

        edtName = findViewById(R.id.edtNameLogin);
        edtPass = findViewById(R.id.edtPassLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String name = edtName.getText().toString();
               String pass = edtPass.getText().toString();
               for(int i = 0; i < users.size(); i++){
                   if(users.get(i).getName().toString().equals(name) && users.get(i).getPassword().toString().equals(pass)){
                       Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                       startActivity(intent);
                      // Toast.makeText(MainActivity.this, "Xin chào"+users.get(i).getName().toString(), Toast.LENGTH_SHORT).show();
                   }
                   Log.d("aaa",users.get(i).getId()+" "+users.get(i).getName().toString()+" "+users.get(i).getPassword().toString());
               }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });
    }

    private void LoadDataUser(){
        Cursor dataUser = dataBase.QueryGetData("select * from users");
        users.clear();
        while(dataUser.moveToNext()){
            int id = dataUser.getInt(0);
            String name = dataUser.getString(1);
            String pass = dataUser.getString(2);
            User user = new User(id,name,pass);
            users.add(user);
        }
    }
    public void LoadDataQuestion(){
        Cursor data = dataBase.QueryGetData("select * from Questions");
        questions.clear();
        while(data.moveToNext()){
            int id = data.getInt(0);
            String question = data.getString(1);
            String ideaA = data.getString(2);
            String ideaB = data.getString(3);
            String ideaC = data.getString(4);
            String ideaD = data.getString(5);
            int answer = data.getInt(6);
            Question ques = new Question(id,question,ideaA,ideaB,ideaC,ideaD,answer);
            questions.add(ques);
        }
    }
    private void SignUp(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.form_sign_up);
        EditText edtNameSU = dialog.findViewById(R.id.edtNameSignUp);
        EditText edtPassSU = dialog.findViewById(R.id.edtPasswordSignUp);
        Button btnOk = dialog.findViewById(R.id.btnOkSignUp);
        Button btnCancel = dialog.findViewById(R.id.btnCancelSignUp);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtNameSU.getText().toString();
                String pass = edtPassSU.getText().toString();
                int count = users.size();
                boolean check = false;
                if(name.equals("") || pass.equals("")){
                    Toast.makeText(MainActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                for(int i = 0; i < users.size(); i++){
                    if(users.get(i).getName().toString().equals(name)){
                        check = true;

                        Toast.makeText(MainActivity.this, "Tên đã tồn tại", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if(check==false){
                    dataBase.QuerySetData("insert into users(id,name,password) values (null,'"+name+"','"+pass+"')");
                    LoadDataUser();
                }
                if(count<users.size()){
                    Toast.makeText(MainActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
        dialog.show();
    }

}