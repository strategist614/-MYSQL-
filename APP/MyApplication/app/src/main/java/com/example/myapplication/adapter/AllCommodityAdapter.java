package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.bean.Commodity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 主界面所有商品列表的适配器
 * @author strategist_614
 */
public class AllCommodityAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    private List<Commodity> commodities = new ArrayList<>();
    //对每一个item保存其位置
    HashMap<Integer,View> location = new HashMap<>();

    public AllCommodityAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<Commodity> commodities) {
        this.commodities = commodities;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return commodities.size();
    }

    @Override
    public Object getItem(int position) {
        return commodities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(location.get(position) == null){
            convertView = layoutInflater.inflate(R.layout.layout_all_commodity,null);
            Commodity commodity = (Commodity) getItem(position);
            holder = new ViewHolder(convertView,commodity);
            //保存view的位置position
            location.put(position,convertView);
            convertView.setTag(holder);
        }else{
            convertView = location.get(position);
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    //定义静态类,包含每一个item的所有元素
    static class ViewHolder {
        TextView tvTitle,tvType,tvPrice;
        public ViewHolder(View itemView,Commodity commodity) {
            tvTitle = itemView.findViewById(R.id.tv_name);
            tvType = itemView.findViewById(R.id.tv_type);
            tvPrice = itemView.findViewById(R.id.tv_price);
           // tvD = itemView.findViewById(R.id.)
            tvTitle.setText(commodity.getTitle());
            tvPrice.setText(String.valueOf(commodity.getPrice())+"元");
            tvType.setText(commodity.getCategory());
        }
    }
}
