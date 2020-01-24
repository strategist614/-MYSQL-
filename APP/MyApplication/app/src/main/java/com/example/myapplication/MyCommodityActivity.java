package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.adapter.AllCommodityAdapter;
import com.example.myapplication.adapter.MyCommodityAdapter;
import com.example.myapplication.bean.Commodity;

import java.util.ArrayList;
import java.util.List;

public class MyCommodityActivity extends AppCompatActivity {
    private Button bnt_1;
    private  ListView lvAllCommodity;
    AllCommodityAdapter adapter;
    List<Commodity> allCommodities = new ArrayList<Commodity>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_commodity);
        //返回
        bnt_1 = findViewById(R.id.bnt_back);
        bnt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lvAllCommodity = findViewById(R.id.lv_my_commodity);
        adapter = new AllCommodityAdapter(getApplicationContext());
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
    }
}
