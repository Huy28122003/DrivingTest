package com.example.drivingtest;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.drivingtest.models.DataBase;

public class LostPassActivity extends AppCompatActivity {

    EditText edtName, edtResult;
    Button btnCofirm;

    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_pass);

        edtName = findViewById(R.id.edtName);
        edtResult = findViewById(R.id.edtResult);
        btnCofirm = findViewById(R.id.btnLostPass);
        dataBase = new DataBase(this,"user.sqlite",null,1);

        btnCofirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String result = edtResult.getText().toString();
                if (name.isEmpty()) {
                    edtName.setError("Bạn phải nhập đủ");
                    edtName.requestFocus();
                    return;
                }
                else if (result.isEmpty()) {
                    edtResult.setError("Bạn phải nhập đủ");
                    edtResult.requestFocus();
                    return;
                }else{
                    int res = Integer.decode(result);
                    String query = "select * from users where name = '" + name + "'";
                    int rs = 0;
                    Cursor cursorName = dataBase.QueryGetData(query);
                    if(cursorName.getCount() == 0){
                        Toast.makeText(LostPassActivity.this, "Tên không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        while (cursorName.moveToNext()){
                            rs = cursorName.getInt(0);
                        }
                    }

                    String queryRs = "select * from results where user_id = '" + rs + "'";
                    Cursor cursorResult = dataBase.QueryGetData(queryRs);
                    if(cursorResult.getCount() > 0){
                        while (cursorResult.moveToNext()){
                            if(res == cursorResult.getInt(2)){
                                ChangePass();
                                break;
                            }

                            Log.i("aaaaaaaaaaaaaaaaa",cursorResult.getInt(2)+"");
                        }

                    }
                }
            }
        });

    }

    private void ChangePass() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.form_update_pass);
        EditText edtPass = dialog.findViewById(R.id.edtPassChange);
        EditText edtPassA = dialog.findViewById(R.id.edtPassChangeAgain);
        Button btnOk = dialog.findViewById(R.id.btnChangeOk);
        Button btnCancel = dialog.findViewById(R.id.btnChangeCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = edtPass.getText().toString();
                String passA = edtPassA.getText().toString();

                if(pass.equals(passA)){
                    String query = "update users set password = '" + pass + "' where name = '" + edtName.getText().toString() + "'";
                    dataBase.QuerySetData(query);
                    startActivity(new Intent(LostPassActivity.this, MainActivity.class));
                }
                else{
                    Toast.makeText(LostPassActivity.this, "Mật khẩu nhập lại không đúng", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog.show();
    }
}