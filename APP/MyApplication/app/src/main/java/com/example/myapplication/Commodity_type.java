package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Commodity_type extends AppCompatActivity {
    private Button Bnt_back;
    TextView tvCommodityType;
    ListView lvCommodityType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_type);
        //返回
        Bnt_back = findViewById(R.id.tv_back);
        Bnt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        int status = this.getIntent().getIntExtra("status",0);
        if(status == 1) {
            tvCommodityType.setText("学习用品");
        }else if(status == 2) {
            tvCommodityType.setText("电子用品");
        }else if(status == 3) {
            tvCommodityType.setText("生活用品");
        }else if(status == 4) {
            tvCommodityType.setText("体育用品");
        }

    }
}
