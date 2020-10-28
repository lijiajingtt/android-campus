package com.jiangnan.artstudio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.jiangnan.artstudio.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class WorkRoomFragment extends BaseFragment {
    private GridView gridView;
    private List<Map<String,Object>>dataList;
    private int[] icon={R.drawable.workroom_1,R.drawable.workroom_2,R.drawable.workroom_3
            ,R.drawable.workroom_4,R.drawable.workroom_5,R.drawable.workroom_6,R.drawable.workroom_7,R.drawable.workroom_8};
    private String[] iconName={"校园创意工作室","产品造型工作室","图形创意工作室","动画数字媒体工作室","摄影工作室","环艺设计工作室","服装设计工作室","室内设计工作室"};
    private String[] iconBtn={"+关注","√已关注","+关注","+关注","+关注","+关注","+关注","+关注"};
    private SimpleAdapter adapter;
    private FragmentManager fManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workroom,container,false);

        findViewId(view);

        initGridView();
        //沉浸式状态栏
        initTitle(view);
        return view;
    }

    public void initGridView() {
        dataList=new ArrayList<>();
        adapter=new SimpleAdapter(getActivity(),getData(), R.layout.grid_item_workroom,new String[]{"image","text","btn"},
                new int[]{R.id.image_workroom,R.id.text_workroom,R.id.btn_workroom});
        gridView.setAdapter(adapter);
        hideScroll(gridView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                WorkRoomMainFragment workRoomMainFragment = new WorkRoomMainFragment();
                fm.beginTransaction()
                        //替换为TwoFragment
                        .replace(R.id.main_content,workRoomMainFragment)
                        .commit();
            }
        });
    }

    public List<Map<String,Object>> getData(){
        for(int i=0;i<icon.length;i++){
            Map<String,Object>map=new HashMap<>();
            map.put("image",icon[i]);
            map.put("text",iconName[i]);
            map.put("btn",iconBtn[i]);
            dataList.add(map);
        }
        return dataList;
    }
    public void findViewId(View view) {
        gridView = view.findViewById(R.id.wr_grid);
    }
}
