package com.jiangnan.artstudio.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.jiangnan.artstudio.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DongTai extends BaseFragment {
    private ListView listView;
    private List<Map<String,Object>> dataList;
    private int[] iconHead={R.drawable.head1,R.drawable.head2,R.drawable.head3,R.drawable.head4};
    private int[] icon={R.drawable.scene_content_img1,R.drawable.scene_content_img2,R.drawable.scene_content_img3,R.drawable.scene_content_img4};
    private String[] iconName={"Oliver Sister","Girlsareawe","Ken Robert","Martin"};
    private String[] iconTime={"10分钟前","16分钟前","50分钟前","1小时前"};
    private String[] iconContent={"双十一到了，购物节还是光棍节，貌似和我无关，我只是个加班写代码的程序员……",
            "一组插画设计欣赏，最近风款爱上了这种风格……",
            "零零落落零零落落零零落落了大时代……",
            "双十二到了，购物节，貌似和我无关，我只是个加班写代码的程序员……"};
    private SimpleAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dongtai,container,false);
        findViewId(view);

        initListView();
        return view;
    }
    public void initListView() {
        dataList=new ArrayList<>();
        adapter=new SimpleAdapter(getActivity(),getData(), R.layout.list_item_scene,
                new String[]{"head","name","time","content","img"},
                new int[]{R.id.head_scene,R.id.name_scene,R.id.time_scene,R.id.content_scene,R.id.img_scene});
        listView.setAdapter(adapter);
        hideScroll(listView);
    }


    public List<Map<String,Object>> getData(){
        for(int i=0;i<icon.length;i++){
            Map<String,Object>map=new HashMap<>();
            map.put("head",iconHead[i]);
            map.put("name",iconName[i]);
            map.put("time",iconTime[i]);
            map.put("content",iconContent[i]);
            map.put("img",icon[i]);
            dataList.add(map);
        }
        return dataList;
    }
    public void findViewId(View view) {
        listView = view.findViewById(R.id.dt_list);
    }
}
