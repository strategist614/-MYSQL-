package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Add_product extends AppCompatActivity {
    private Button Bnt_back,Bnt_publish;
    TextView tvStuId;
    EditText etTitle,etPrice,etPhone,etDescription;
    Spinner spType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Bnt_back = findViewById(R.id.btn_back);
        tvStuId = findViewById(R.id.tv_student_id);
        tvStuId.setText(this.getIntent().getStringExtra("user_id"));
        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        final String StuID = bundle.getString("ID");
        System.out.println(StuID);
        //返回
        Bnt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 finish();
            }
        });
        // 提交确定
        String[] ctype = new String[]{"学习用品", "电子用品", "生活用品" ,"体育用品"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        Spinner spinner = super.findViewById(R.id.spn_type);
        spinner.setAdapter(adapter);
        etTitle = findViewById(R.id.et_title);
        etPrice = findViewById(R.id.et_price);
        etPhone = findViewById(R.id.et_phone);
        etDescription = findViewById(R.id.et_description);
        spType = findViewById(R.id.spn_type);
        Bnt_publish = findViewById(R.id.btn_publish);
        Bnt_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String title = etTitle.getText().toString();
                        String price = etPrice.getText().toString();
                        String type = spType.getSelectedItem().toString();
                        //String phone = etPhone.getText().toString();
                        String description = etDescription.getText().toString();
                        String type_add = "";
                        float pr = Float.parseFloat(price);
                        int ID = Integer.parseInt(StuID);
                        System.out.println();
                        if(type.equals("学习用品")) type_add = "S";
                        if(type.equals("电子产品")) type_add = "E";
                        if(type.equals("生活用品")) type_add = "L";
                        if(type.equals("体育用品")) type_add = "P";
                        int result = DBHelper.Publish(title,ID,pr,description,type_add);
                        System.out.println(result);
                    }
                }).start();
            }
        });
    }
    /**
     * 检查输入是否合法
     */
    public boolean CheckInput() {
        String title = etTitle.getText().toString();
        String price = etPrice.getText().toString();
        String type = spType.getSelectedItem().toString();
        String phone = etPhone.getText().toString();
        String description = etDescription.getText().toString();
        if (title.trim().equals("")) {
            Toast.makeText(this,"商品标题不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (price.trim().equals("")) {
            Toast.makeText(this,"商品价格不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (type.trim().equals("请选择类别")) {
            Toast.makeText(this,"商品类别未选择!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phone.trim().equals("")) {
            Toast.makeText(this,"手机号码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (description.trim().equals("")) {
            Toast.makeText(this,"商品描述不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
