package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myapplication.DBHelper;
import java.sql.*;

public class loginActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private Button bt_Login;
    private Button bt_Cancel;
    private Button bt_register;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bt_Login = findViewById(R.id.bnt_1);
        bt_Cancel = findViewById(R.id.bnt_2);
        bt_register = findViewById(R.id.bnt_3);
        et_username = findViewById(R.id.et_1);
        et_password = findViewById(R.id.et_2);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginActivity.this,Register.class);
                startActivity(i);
            }
        });
        bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckInput()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String user = et_username.getText().toString();
                            String password = et_password.getText().toString();
                            boolean flag = DBHelper.linkMysql(user, password);
                            if (flag) {
                                Intent intent = new Intent(loginActivity.this, MainActivity.class);
                                Bundle bundle = new Bundle();
                                username = et_username.getText().toString();
                                bundle.putString("username", username);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                            else
                            {
                                Looper.prepare();
                                Toast.makeText(loginActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                                Looper.loop();
                            }
                        }
                    }).start();
            }
            }
        });
        bt_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public boolean CheckInput () {
        String StuNumber = et_username.getText().toString();
        String StuPwd = et_password.getText().toString();
        if (StuNumber.trim().equals("")) {
            Toast.makeText(loginActivity.this, "学号不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (StuPwd.trim().equals("")) {
            Toast.makeText(loginActivity.this, "密码不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
