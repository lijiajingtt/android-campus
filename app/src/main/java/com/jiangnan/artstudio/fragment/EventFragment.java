package com.jiangnan.artstudio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jiangnan.artstudio.R;
import com.jiangnan.artstudio.adapter.EventAdapter;
import com.jiangnan.artstudio.SearchActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventFragment extends BaseFragment {
    private Button btn2;

    private EventMessageFragment emf;
    private PublishDemandFragment pdf;
    private ListView listView;
    private Button addBtn;
    private ImageView messageBtn,searchBtn;
    private List<Map<String,Object>> dataList;
    private int[] icon={R.drawable.event_img_1,R.drawable.event_img_2,R.drawable.event_img_3};
    private String[] iconName={"QQJOY主kt海报设计","一封情酥","市井潮"};
    private String[] iconTime={"截止时间2020.10.25","截止时间2020.10.29","截止时间2020.10.29"};
    private String[] iconFinish={"已完成","已完成","未开始"};
    private String[] iconAdd={"","","申请加入"};
    private EventAdapter adapter;
    private FragmentManager fManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event,container,false);

        findViewId(view);
        initListView();

        onClick();
        // 初始化状态栏，以View替代
        initTitle(view);
        return view;
    }

    public void initListView() {
        dataList=new ArrayList<>();
//        adapter=new SimpleAdapter(getActivity(),getData(), R.layout.list_item_event,
//                new String[]{"title","img","time","event_flag_isFinish","event_flag_isAdd"},
//                new int[]{R.id.title,R.id.img,R.id.time,R.id.event_flag_isFinish,R.id.event_flag_isAdd});

        adapter = new EventAdapter(getContext(),getData());

        listView.setAdapter(adapter);
        hideScroll(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
//                Toast.makeText(getActivity(),"hello",Toast.LENGTH_LONG).show();

            }
        });
    }


    public void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if(emf!=null)fragmentTransaction.hide(emf);
        if(pdf!=null)fragmentTransaction.hide(pdf);
    }

    public void onClick() {
        fManager = getActivity().getSupportFragmentManager();
        addBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentTransaction fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                pdf = new PublishDemandFragment();
                fTransaction.add(R.id.main_content,pdf).commit();
            }
        });
        messageBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentTransaction fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                emf = new EventMessageFragment();
                fTransaction.add(R.id.main_content,emf).commit();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
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

    public void findViewId(View view) {
        listView = view.findViewById(R.id.ev_list);
        addBtn = view.findViewById(R.id.event_right_add);
        messageBtn = view.findViewById(R.id.event_message_btn);
        searchBtn = view.findViewById(R.id.event_title_search);
    }

}
