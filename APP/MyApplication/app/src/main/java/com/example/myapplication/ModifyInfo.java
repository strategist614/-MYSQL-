package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class ModifyInfo extends AppCompatActivity {
    private Button bnt_1;
    private Button bnt_2;
    EditText etStuName,etMajor,etPhone,etQq,etAddress;
    private TextView tv_my;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);
        tv_my = findViewById(R.id.tv_stu_number);
        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        final String Stu = bundle.getString("stu_number2");
        tv_my.setText(Stu);
        //保存按键
        bnt_1 = findViewById(R.id.modifyinfo_bnt_1);
        etStuName = findViewById(R.id.et_stu_name);
        etMajor = findViewById(R.id.et_stu_major);
        etPhone = findViewById(R.id.et_stu_phone);
        etQq = findViewById(R.id.et_stu_qq);
        etAddress = findViewById(R.id.et_stu_address);
        bnt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  if(CheckInput())
                  {
                      new Thread(new Runnable() {
                          @Override
                          public void run() {
                              String StuName = etStuName.getText().toString();
                              String StuMajor = etMajor.getText().toString();
                              String StuPhone = etPhone.getText().toString();
                              String StuQq = etQq.getText().toString();
                              String StuAddress = etAddress.getText().toString();
                              DBHelper.modify(Stu,StuName,StuMajor,StuPhone,StuQq,StuAddress);
                          }
                      }).start();
                  }
            }
        });
        //返回按键
        bnt_2 = findViewById(R.id.modifyinfo_bnt_2);
        bnt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //修改数据

    }
    //检查输入是否为空
    public boolean CheckInput() {
        String StuName = etStuName.getText().toString();
        String StuMajor = etMajor.getText().toString();
        String StuPhone = etPhone.getText().toString();
        String StuQq = etQq.getText().toString();
        String StuAddress = etAddress.getText().toString();
        if(StuName.trim().equals("")) {
            Toast.makeText(this,"姓名不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StuMajor.trim().equals("")) {
            Toast.makeText(this,"专业不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StuPhone.trim().equals("")) {
            Toast.makeText(this,"联系方式不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StuQq.trim().equals("")) {
            Toast.makeText(this,"QQ号不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StuAddress.trim().equals("")) {
            Toast.makeText(this,"地址不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
