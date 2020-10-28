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
import com.jiangnan.artstudio.search.EventBean;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ljj.
 */
public class EventSearchAdapter extends BaseAdapter {
    /**
     * 上下文
     */
    private Context mContext;

    private Resources mResources;

    private LayoutInflater mInflater;
    /**
     * 数据源
     */
    private List<EventBean> mDatas;


    /**
     * 构造函数
     *
     * @param context
     * @param datas
     */
    public EventSearchAdapter(Context context, List<EventBean> datas) {
        this.mInflater = LayoutInflater.from(context);
        this.mResources = context.getResources();
        mContext = context;
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if (null == view) {
            view = mInflater.inflate(R.layout.list_event_search_item,viewGroup,false);
            vh = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            vh.evImg = view.findViewById(R.id.search_img);
            vh.evTitle = view.findViewById(R.id.search_title);
            vh.evTime = view.findViewById(R.id.search_time);
            vh.evBtnFinish = view.findViewById(R.id.search_flag_isFinish);
            vh.evBtnAdd = view.findViewById(R.id.search_flag_isAdd);


            EventBean bean = (EventBean) getItem(position);
            if (null != bean) {
                vh.evImg.setImageResource(bean.getEvImg());
                vh.evTitle.setText(bean.getEvTitle());
                vh.evTime.setText(bean.getEvTime());
                vh.evBtnFinish.setText(bean.getEvBtnIsFinish());
                vh.evBtnAdd.setText(bean.getEvBtnIsAdd());
            }

            String eventAddText = bean.getEvBtnIsAdd();

            String eventFlagText = bean.getEvBtnIsFinish();

            if ("已完成".equals(eventFlagText)){
                vh.evBtnFinish.setTextColor(mResources.getColor(R.color.highGray));//字体颜色
            }else {
                vh.evBtnFinish.setTextColor(mResources.getColor(R.color.btnBlue));
            }

            if (eventAddText == null || "".equals(eventAddText)){
                vh.evBtnAdd.setVisibility(View.GONE);
            }else {
                vh.evBtnAdd.setVisibility(View.VISIBLE);
            }
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        return view;
    }


    /**
     * vh
     */
    class ViewHolder {
        ImageView evImg;
        TextView evTitle;
        TextView evTime;
        Button evBtnFinish;
        Button evBtnAdd;
    }
}
