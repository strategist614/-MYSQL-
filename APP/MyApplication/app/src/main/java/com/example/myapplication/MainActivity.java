package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DBHelper;
import com.example.myapplication.adapter.AllCommodityAdapter;
import com.example.myapplication.bean.Commodity;
import com.example.myapplication.bean.Student;

import java.lang.ref.WeakReference;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageButton l_Use;
    private ImageButton el_Use;
    private ImageButton life_Use;
    private ImageButton sport_Use;
    private ImageButton homepage;
    private ImageButton addproduct;
    private ImageButton myhomepage;
    private  ListView lvAllCommodity;
    AllCommodityAdapter adapter;
    List<Commodity> allCommodities = new ArrayList<Commodity>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homepage = findViewById(R.id.ib_home_page);
        addproduct = findViewById(R.id.ib_add_product);
        myhomepage = findViewById(R.id.ib_personal_center);
        final Bundle bundle = this.getIntent().getExtras();
        final TextView tvStuNumber = findViewById(R.id.tv_student_number);
        String str = "";
        if(bundle != null)
        {
            str = "欢迎" + bundle.getString("username") +",你好！";
        }
        tvStuNumber.setText(str);
//        //当前登录的学生账号
       final String stuNum = tvStuNumber.getText().toString().substring(2, tvStuNumber.getText().length() - 4);
       //跳转到增加闲置的界面
        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(MainActivity.this,Add_product.class);
                        String id = "";
                        id = DBHelper.GetId(stuNum);
                        if(bundle != null)
                        {
                            bundle.putString("username_id",stuNum);
                            i.putExtras(bundle);
                        }
                        if(bundle != null)
                        {
                            bundle.putString("ID",id);
                            i.putExtras(bundle);
                        }
                        startActivity(i);
                    }
                }).start();
            }
        });
        //跳转到个人界面
        myhomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Personalcenter.class);
                if (bundle != null) {
                    //获取学生学号
                    bundle.putString("username1", stuNum);
                    intent.putExtras(bundle);
                }
                startActivity(intent);
            }
        });
        //为每个item设置点击事件
        lvAllCommodity = findViewById(R.id.lv_all_commodity);
        adapter = new AllCommodityAdapter(getApplicationContext());
       // allCommodities = DBHelper.Adapter();
        //adapter.setData(allCommodities);
        //lvAllCommodity.setAdapter(adapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                allCommodities = DBHelper.Adapter();
                System.out.println("asd:" + allCommodities.size());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setData(allCommodities);
                        lvAllCommodity.setAdapter(adapter);
                    }
                });

            }
        }).start();
        //学习物品
        l_Use = findViewById(R.id.ib_learning_use);
        el_Use = findViewById(R.id.ib_electric_product);
        life_Use = findViewById(R.id.ib_daily_use);
        sport_Use = findViewById(R.id.ib_sports_good);
        final Bundle bundle2 = new Bundle();
        l_Use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle2.putInt("status",1);
                Intent i = new Intent(MainActivity.this,CommonType.class);
                i.putExtras(bundle2);
                startActivity(i);
            }
        });
        //电子物品
        el_Use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle2.putInt("status",2);
                Intent i = new Intent(MainActivity.this,CommonType.class);
                i.putExtras(bundle2);
                startActivity(i);
            }
        });
        //生活物品
        life_Use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle2.putInt("status",3);
                Intent i = new Intent(MainActivity.this,CommonType.class);
                i.putExtras(bundle2);
                startActivity(i);
            }
        });
        //体育物品
        sport_Use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle2.putInt("status",4);
                Intent i = new Intent(MainActivity.this,CommonType.class);
                i.putExtras(bundle2);
                startActivity(i);
            }
        });
    }

}
