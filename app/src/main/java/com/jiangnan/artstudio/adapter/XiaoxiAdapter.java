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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ljj.
 */

public class XiaoxiAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private  List<Map<String,Object>> mData;
    private Resources mResources;

    public XiaoxiAdapter(Context context, List<Map<String,Object>> data) {
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
            convertView = mInflater.inflate(R.layout.list_item_xiaoxi,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.head = convertView.findViewById(R.id.head);
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.schoolClass = convertView.findViewById(R.id.school);
            viewHolder.permit = convertView.findViewById(R.id.xiaoxi_isPermit);
            viewHolder.img_1 = convertView.findViewById(R.id.img1);
            viewHolder.img_2 = convertView.findViewById(R.id.img2);
            viewHolder.img_3 = convertView.findViewById(R.id.img3);
            viewHolder.img_4 = convertView.findViewById(R.id.img4);
            viewHolder.go_message = convertView.findViewById(R.id.xiaoxi_go_txt);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Map<String,Object> itemMap = mData.get(position);

        // 头像
            viewHolder.head.setImageResource((int) itemMap.get("head"));

        // 名字
        viewHolder.name.setText((String)itemMap.get("name"));

        // 班级
        viewHolder.schoolClass.setText((String)itemMap.get("school"));

        // 是否同意
        String permitText = (String)itemMap.get("permit");
        viewHolder.permit.setText(permitText);

        if(permitText.equals("同意") || permitText==null) {
            viewHolder.permit.setBackgroundResource(R.drawable.canzhiri);
        } else {
            viewHolder.permit.setBackgroundResource(R.drawable.cannotzhiri);
        }

        // 作品集
        int[] ints = (int[]) itemMap.get("imgList");
        viewHolder.img_1.setImageResource(ints[0]);
        viewHolder.img_2.setImageResource(ints[1]);
        viewHolder.img_3.setImageResource(ints[2]);
        viewHolder.img_4.setImageResource(ints[3]);

        // 描述
        viewHolder.go_message.setText((String)itemMap.get("message"));

//
//
//        viewHolder.titleTv.setText((String)itemMap.get("title"));
//        viewHolder.timeTv.setText((String)itemMap.get("time"));
//        Integer iconResId = (Integer) itemMap.get("img");
//        if (iconResId != null) {
//            viewHolder.iconIv.setImageResource(iconResId);
//        }
//        String eventFlagText = (String) itemMap.get("event_flag_isFinish");
//        viewHolder.eventFlagBtn.setText(eventFlagText);
//        //已完成和未完成资源背景指定
//        viewHolder.eventFlagBtn.setBackgroundResource(R.drawable.event_button_finish);
//        viewHolder.eventFlagBtn.setEnabled(false);
//        //使用了存在的颜色资源
//        if ("已完成".equals(eventFlagText)){
//            //字体颜色
//            viewHolder.eventFlagBtn.setTextColor(mResources.getColor(R.color.highGray));
//        }else {
//            viewHolder.eventFlagBtn.setTextColor(mResources.getColor(R.color.btnBlue));
//        }
//
//        String eventAddText = (String) itemMap.get("event_flag_isAdd");
//        if (eventAddText == null || "".equals(eventAddText)){
//            viewHolder.eventAddBtn.setVisibility(View.GONE);
//        }else {
//            viewHolder.eventAddBtn.setVisibility(View.VISIBLE);
//        }
        return convertView;
    }

    public class ViewHolder{
        private ImageView head;
        private TextView name;
        private TextView schoolClass;
        private Button permit;
        private ImageView img_1;
        private ImageView img_2;
        private ImageView img_3;
        private ImageView img_4;
        private TextView go_message;

        //查看,喜欢,评论未添加
    }
}