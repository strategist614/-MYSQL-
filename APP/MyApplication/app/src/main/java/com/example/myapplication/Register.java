package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText tvStuNumber,tvStuPwd,tvStuConfirmPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btnCancel = findViewById(R.id.btn_cancel);
        //返回到登录界面
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvStuNumber = findViewById(R.id.et_username);
        tvStuPwd = findViewById(R.id.et_password);
        tvStuConfirmPwd = findViewById(R.id.et_confirm_password);
        //注册点击事件
        Button btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首先确保不为空
                if(CheckInput()) {
                    //销毁当前界面
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String stuname = tvStuNumber.getText().toString();
                            String password = tvStuPwd.getText().toString();
                            System.out.println(stuname);
                            boolean tmp = DBHelper.CheckInputStuName(stuname);
                            if (tmp){
                                int id = DBHelper.register(stuname, password);
                                System.out.println(id);
                                int result = DBHelper.Insert(id, stuname);
                                System.out.println(result);
                                if (result != 0) {
                                    Looper.prepare();
                                    Toast.makeText(Register.this, "恭喜你注册成功!", Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
                            }
                            else
                            {
                                Looper.prepare();
                                Toast.makeText(Register.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }
                    }).start();
                    finish();
                }
            }
        });
    }
    //判断输入是否符合规范
    public boolean CheckInput() {
        String username = tvStuNumber.getText().toString();
        String password = tvStuPwd.getText().toString();
        String confirm_password = tvStuConfirmPwd.getText().toString();
        if(username.trim().equals("")) {
            Toast.makeText(Register.this,"用户名不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.trim().equals("")) {
            Toast.makeText(Register.this,"密码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(confirm_password.trim().equals("")) {
            Toast.makeText(Register.this,"确认密码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!password.trim().equals(confirm_password.trim())) {
            Toast.makeText(Register.this,"两次密码输入不一致!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
