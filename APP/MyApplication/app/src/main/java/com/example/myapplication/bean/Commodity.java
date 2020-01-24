package com.example.myapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 商品实体类
 * @author : strategist_614
 */
public class Commodity{

    //编号
    private Integer id;
    //标题
    private String title;
    //类别
    private String category;
    //价格
    private float price;
    //联系方式
    private String phone;
    //商品描述
    private String description;
    //用户学号
    private String stuId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }


}
