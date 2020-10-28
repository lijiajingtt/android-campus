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

public class EventAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private  List<Map<String,Object>> mData;
    private Resources mResources;

    public EventAdapter(Context context, List<Map<String,Object>> data) {
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
            convertView = mInflater.inflate(R.layout.list_item_event,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.titleTv = convertView.findViewById(R.id.title);
            viewHolder.timeTv = convertView.findViewById(R.id.time);
            viewHolder.iconIv = convertView.findViewById(R.id.img);
            viewHolder.eventFlagBtn = convertView.findViewById(R.id.event_flag_isFinish);
            viewHolder.eventAddBtn = convertView.findViewById(R.id.event_flag_isAdd);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Map<String,Object> itemMap = mData.get(position);
        viewHolder.titleTv.setText((String)itemMap.get("title"));
        viewHolder.timeTv.setText((String)itemMap.get("time"));
        Integer iconResId = (Integer) itemMap.get("img");
        if (iconResId != null) {
            viewHolder.iconIv.setImageResource(iconResId);
        }
        String eventFlagText = (String) itemMap.get("event_flag_isFinish");
        viewHolder.eventFlagBtn.setText(eventFlagText);
        //已完成和未完成资源背景指定
        viewHolder.eventFlagBtn.setBackgroundResource(R.drawable.event_button_finish);
        viewHolder.eventFlagBtn.setEnabled(false);
        //使用了存在的颜色资源
        if ("已完成".equals(eventFlagText)){
            //字体颜色
            viewHolder.eventFlagBtn.setTextColor(mResources.getColor(R.color.highGray));
        }else {
            viewHolder.eventFlagBtn.setTextColor(mResources.getColor(R.color.btnBlue));
        }

        String eventAddText = (String) itemMap.get("event_flag_isAdd");
        if (eventAddText == null || "".equals(eventAddText)){
            viewHolder.eventAddBtn.setVisibility(View.GONE);
        }else {
            viewHolder.eventAddBtn.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    public class ViewHolder{
        public TextView titleTv;
        public TextView timeTv;
        public ImageView iconIv;
        public Button eventFlagBtn;
        public Button eventAddBtn;
        //查看,喜欢,评论未添加
    }
}