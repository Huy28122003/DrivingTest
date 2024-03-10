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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new ArrayList<>();

        dataBase = new DataBase(this, "user.sqlite", null, 1);
        String createDB = "create table if not exists users (id integer primary key autoincrement, name varchar(20), password pass(8))";
        dataBase.QuerySetData(createDB);

        //dataBase.QuerySetData("insert into users(id,name,password) values (null,'huy','1')");
//        for(int i = 6; i < 8; i++){
//            dataBase.QuerySetData("delete from users where id = "+i);
//        }


        LoadData();

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
                       Toast.makeText(MainActivity.this, "Xin chào"+users.get(i).getName().toString(), Toast.LENGTH_SHORT).show();
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

    private void LoadData(){
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
                    LoadData();
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