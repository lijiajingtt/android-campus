package com.jiangnan.artstudio.fragment;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.jiangnan.artstudio.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JianJie extends BaseFragment {
    private TextView jieshao;
    private ImageView jiantou;
    private int infoLineCount;
    private boolean isOpen = true;

    private GridView gridViewDuty,gridViewGroup;

    private List<Map<String,Object>> dataListDuty,dataListGroup;

    private int[] dutyHead = {R.drawable.head1,R.drawable.head2,R.drawable.head3,R.drawable.head4};
    private int[] groupHead = {R.drawable.head5,R.drawable.head6,R.drawable.head7,R.drawable.head8,R.drawable.head9,R.drawable.head10,R.drawable.head11,R.drawable.head12,R.drawable.head13,R.drawable.head14};

    private String[] groupName = {"刘大","许二","张三","李四","王五","赵六","柳七","徐八","BOBBY","汪九"};

    private SimpleAdapter simpleAdapterDuty,simpleAdapterGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jianjie,container,false);
        findViewId(view);

        initDutyGridView();

        initGroupGridView();

        onClick();
        return view;
    }

    public void onClick() {
        jieshao.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                infoLineCount = jieshao.getLineCount();   //TextView高度wrap_content；首先保留内容总行数
                Log.e("行数", String.valueOf(infoLineCount));
                jieshao.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if(jieshao.getLineCount()>0){
                    jieshao.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
        //默认保留4行内容
        jieshao.setHeight(3 * jieshao.getLineHeight());
        //点击展示和隐藏
        jiantou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen == true){
                    jieshao.setHeight(infoLineCount * jieshao.getLineHeight());
                    jiantou.setImageResource(R.drawable.jianjie_up);
                    isOpen = false;
                } else {
                    jieshao.setHeight(3 * jieshao.getLineHeight());
                    jiantou.setImageResource(R.drawable.jianjie_down);
                    isOpen = true;
                }

            }
        });
    }

    private void initDutyGridView() {
        dataListDuty = new ArrayList<>();
        simpleAdapterDuty =new SimpleAdapter(getActivity(),getDataDuty(),R.layout.grid_item_duty,
                new String[]{"img"},
                new int[]{R.id.jianjie_duty_headImg});
        gridViewDuty.setAdapter(simpleAdapterDuty);
    }

    public List<Map<String,Object>> getDataDuty(){
        for(int i=0;i<dutyHead.length;i++){
            Map<String,Object>map=new HashMap<>();
            map.put("img",dutyHead[i]);
            dataListDuty.add(map);
        }
        return dataListDuty;
    }

    private void initGroupGridView() {
        dataListGroup = new ArrayList<>();
        simpleAdapterGroup =new SimpleAdapter(getActivity(),getDataGroup(),R.layout.grid_item_group,
                new String[]{"img","name"},
                new int[]{R.id.jianjie_group_headImg,R.id.jianjie_group_name});
        gridViewGroup.setAdapter(simpleAdapterGroup);
    }

    public List<Map<String,Object>> getDataGroup(){
        for(int i=0;i<groupHead.length;i++){
            Map<String,Object>map=new HashMap<>();
            map.put("img",groupHead[i]);
            map.put("name",groupName[i]);
            dataListGroup.add(map);
        }
        return dataListGroup;
    }

    private void findViewId(View view) {
        jieshao = view.findViewById(R.id.jieshao);
        jiantou = view.findViewById(R.id.jiantou);

        gridViewDuty = view.findViewById(R.id.jianjie_grid_duty);
        gridViewGroup = view.findViewById(R.id.jianjie_grid_group);
    }
}
