package com.jiangnan.artstudio;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jiangnan.artstudio.adapter.EventSearchAdapter;
import com.jiangnan.artstudio.search.CommolySearchView;
import com.jiangnan.artstudio.search.EventBean;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends BaseActivity {
    private int[] icon={R.drawable.event_img_1,R.drawable.event_img_2,R.drawable.event_img_3};
    private String[] iconName={"QQJOY主kt海报设计","一封情酥","市井潮"};
    private String[] iconTime={"截止时间2020.10.25","截止时间2020.10.29","截止时间2020.10.29"};
    private String[] iconFinish={"已完成","已完成","未开始"};
    private String[] iconAdd={"","","申请加入"};

    private EventBean eventBean = null;
    /**
     * TAG
     */
    private static final String TAG = "MainActivity";
    /**
     * 数据显示listview
     */
    private ListView mListView;
    /**
     * 三国通用搜索框
     */
    private CommolySearchView<EventBean> mSGCommolySearchView;
    /**
     * 三国数据源
     */
    private List<EventBean> mSGDatas;
    /**
     * 三国适配器
     */
    private EventSearchAdapter eventSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);

        initDataI();
        initViewI();

        // 初始化状态栏
        initTitle();
        // 状态栏字体变黑
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    /**
     * 初始化数据
     */
    private void initDataI() {
        mSGDatas = new ArrayList<EventBean>();
        for(int i=0; i<icon.length; i++) {
            eventBean = new EventBean(icon[i],iconName[i],iconTime[i],iconFinish[i],iconAdd[i]);
            mSGDatas.add(eventBean);
        }
    }

    /**
     * 初始化控件
     */
    private void initViewI() {
        mSGCommolySearchView = findViewById(R.id.csv_show);
        mListView = (ListView) findViewById(R.id.lv_show);
        eventSearchAdapter = new EventSearchAdapter(this, mSGDatas);
        mListView.setAdapter(eventSearchAdapter);
        // 设置数据源
        mSGCommolySearchView.setDatas(mSGDatas);
        // 设置适配器
        mSGCommolySearchView.setAdapter(eventSearchAdapter);
        // 设置筛选数据
        mSGCommolySearchView.setSearchDataListener(new CommolySearchView.SearchDatas<EventBean>() {
            @Override
            public List<EventBean> filterDatas(List<EventBean> datas, List<EventBean> filterdatas, String inputstr) {
                for (int i = 0; i < datas.size(); i++) {
                    // 筛选条件
                    if ((datas.get(i).getEvTitle()).contains(inputstr) || datas.get(i).getEvTime().contains(inputstr) || datas.get(i).getEvBtnIsFinish().contains(inputstr)) {
                        filterdatas.add(datas.get(i));
                    }
                }
                return filterdatas;
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SearchActivity.this, mSGCommolySearchView.getFilterDatas().get(i).getEvTitle() + "字" + mSGCommolySearchView.getFilterDatas().get(i).getEvTime() + "\n" + mSGCommolySearchView.getFilterDatas().get(i).getEvBtnIsFinish(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}