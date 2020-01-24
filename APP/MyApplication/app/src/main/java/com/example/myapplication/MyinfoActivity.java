package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MyinfoActivity extends AppCompatActivity {
    private Button bnt_1;
    private Button bnt_2;
    private TextView etxm,etzy,etzh,etlxfs,etqq,etss;
    private static String TAG ="MyinfoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        //从bundle中获取学号
        final TextView tvUserNumber = findViewById(R.id.user_hao);
        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        final String Stu = bundle.getString("stu_id");
        System.out.println(Stu);
        //tvUserNumber.setText(Stu);
        etzh = findViewById(R.id.ttv_stuuser);
        etxm = findViewById(R.id.ttv_name);
        etzy = findViewById(R.id.ttv_zy);
        etlxfs = findViewById(R.id.ttv_lxfs);
        etqq = findViewById(R.id.ttv_qq);
        etss = findViewById(R.id.ttv_ss);
        //放置数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String,String> mp = new HashMap<String, String>();
                mp = DBHelper.SetUser(Stu);
                System.out.println(mp.get("Nick"));
                etzh.setText(mp.get("User"));
                etxm.setText(mp.get("Nick"));
                etzy.setText(mp.get("Pro"));
                etlxfs.setText(mp.get("Phone"));
                etqq.setText(mp.get("Q"));
                etss.setText(mp.get("Dormitory"));
            }
        }).start();
        //修改按键
        bnt_1 = findViewById(R.id.myinfobnt1);
        bnt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyinfoActivity.this,ModifyInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("stu_number2",Stu);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        //返回按键
        bnt_2 = findViewById(R.id.myinfobnt2);
        bnt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
