package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Personalcenter extends AppCompatActivity {
    //@Override
    private Button Bnt1;
    private Button Bnt2;
    private Button Bnt3;
    private Button Bnt4;
    private Button Bnt5;
    private TextView TvStuNumber;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalcenter);
        Bnt1 = findViewById(R.id.btn_user_info);
        Bnt2 = findViewById(R.id.btn_my_goods);
        Bnt3 = findViewById(R.id.btn_about_app);
        Bnt4 = findViewById(R.id.btn_logout);
        Bnt5 = findViewById(R.id.btn_back);
        TvStuNumber = findViewById(R.id.Txt_1);
        //String StuNumber = this.getIntent().getStringExtra("username1");
        //System.out.println(StuNumber);
        String StuNumber = this.getIntent().getStringExtra("username1");
        System.out.println(StuNumber);
        TvStuNumber.setText(StuNumber);
        //个人信息按键
        Bnt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Personalcenter.this,MyinfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("stu_id",TvStuNumber.getText().toString());
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        //我的发布按键
        Bnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Personalcenter.this,MyCommodityActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("stu_id",TvStuNumber.getText().toString());
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        //关于系统按键
        Bnt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Personalcenter.this,AboutApp.class);
                startActivity(i);
            }
        });
        //退出登录
        Bnt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Personalcenter.this);
                builder.setTitle("提示:").setMessage("确认退出系统吗?").setIcon(R.drawable.icon_user).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //跳转到登录界面
                        Intent intent = new Intent(getApplicationContext(),loginActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });
        //返回按键
        Bnt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
