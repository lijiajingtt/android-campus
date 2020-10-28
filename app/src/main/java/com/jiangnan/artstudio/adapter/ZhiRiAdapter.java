package com.jiangnan.artstudio.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiangnan.artstudio.R;

import java.util.List;
import java.util.Map;

/**
 * Created by ljj.
 */

public class ZhiRiAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private  List<Map<String,Object>> mData;
    private Resources mResources;

    public ZhiRiAdapter(Context context, List<Map<String,Object>> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mResources = context.getResources();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_zhir,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.zhiImg = convertView.findViewById(R.id.head);
            viewHolder.zhiName = convertView.findViewById(R.id.name);
            viewHolder.zhiClass = convertView.findViewById(R.id.school);
            viewHolder.zhiBtn = convertView.findViewById(R.id.isZhiri);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Map<String,Object> itemMap = mData.get(position);
        viewHolder.zhiName.setText((String)itemMap.get("name"));
        viewHolder.zhiClass.setText((String)itemMap.get("school"));
        Integer iconResId = (Integer) itemMap.get("head");
        if (iconResId != null) {
            viewHolder.zhiImg.setImageResource(iconResId);
        }

        if(itemMap.get("name").equals("金泰亨") || itemMap.get("name").equals("TOP")) {
            viewHolder.zhiBtn.setBackgroundResource(R.drawable.canzhiri);
        }
        return convertView;
    }

    public class ViewHolder{
        public ImageView zhiImg;
        public TextView zhiName;
        public TextView zhiClass;
        public Button zhiBtn;
        //查看,喜欢,评论未添加
    }
}