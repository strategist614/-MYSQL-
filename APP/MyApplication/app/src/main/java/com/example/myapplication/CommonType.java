package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.adapter.AllCommodityAdapter;
import com.example.myapplication.bean.Commodity;

import java.util.ArrayList;
import java.util.List;

public class CommonType extends AppCompatActivity {
    private Button bnt_back;
    private ListView lvAllCommodity;
    AllCommodityAdapter adapter;
    List<Commodity> allCommodities = new ArrayList<Commodity>();
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_type);
        //返回
        bnt_back = findViewById(R.id.tv_back);
        bnt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lvAllCommodity = findViewById(R.id.list_commodity);
        adapter = new AllCommodityAdapter(getApplicationContext());
        final Bundle bundle = this.getIntent().getExtras();
        int tmp = bundle.getInt("status");
        String type = "";
        if(tmp == 1)
        {
            type = "S";
        }
        else if(tmp == 2)
        {
            type = "E";
        }
        else if(tmp == 3)
        {
            type = "L";
        }
        else if(tmp == 4)
        {
            type = "P";
        }
        tv = findViewById(R.id.tv_type);
        if(tmp == 1){
            tv.setText("学习用品");
        }
        else if(tmp == 2)
        {
            tv.setText("电子产品");
        }
        else if(tmp ==3 )
        {
            tv.setText("生活用品");
        }
        else if(tmp == 4){
            tv.setText("体育用品");
        }
        final String finalType = type;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                allCommodities = DBHelper.Search(finalType);
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
    }
}
