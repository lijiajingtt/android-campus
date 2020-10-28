package com.jiangnan.artstudio.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.jiangnan.artstudio.R;
import com.jiangnan.artstudio.adapter.EventAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XiangMu extends BaseFragment {
    private List<Map<String,Object>> dataList;
    private int[] icon={R.drawable.event_img_1,R.drawable.event_img_2,R.drawable.event_img_3};
    private String[] iconName={"QQJOY主kt海报设计","一封情酥","市井潮"};
    private String[] iconTime={"截止时间2020.10.25","截止时间2020.10.29","截止时间2020.10.29"};
    private String[] iconFinish={"已完成","已完成","未开始"};
    private String[] iconAdd={"","","申请加入"};
    private EventAdapter evAdapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xiangmu,container,false);

        findViewId(view);
        initListView();

        return view;
    }
    public void findViewId(View view) {
        listView = view.findViewById(R.id.xm_list);
    }
    public void initListView() {
        dataList=new ArrayList<>();
//        adapter=new SimpleAdapter(getActivity(),getData(), R.layout.list_item_event,
//                new String[]{"title","img","time","event_flag_isFinish","event_flag_isAdd"},
//                new int[]{R.id.title,R.id.img,R.id.time,R.id.event_flag_isFinish,R.id.event_flag_isAdd});

        evAdapter = new EventAdapter(getContext(),getData());

        listView.setAdapter(evAdapter);
        hideScroll(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Toast.makeText(getActivity(),"hello",Toast.LENGTH_LONG).show();

            }
        });
    }

    public List<Map<String,Object>> getData(){
        for(int i=0;i<icon.length;i++){
            Map<String,Object>map=new HashMap<>();
            map.put("title",iconName[i]);
            map.put("img",icon[i]);
            map.put("time",iconTime[i]);
            map.put("event_flag_isFinish",iconFinish[i]);
            map.put("event_flag_isAdd",iconAdd[i]);
            dataList.add(map);
        }
        return dataList;
    }
}