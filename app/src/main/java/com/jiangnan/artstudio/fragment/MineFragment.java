package com.jiangnan.artstudio.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiangnan.artstudio.R;

public class MineFragment extends BaseFragment {
    private TextView fs,gz;
    private ImageView set,head;
    private LinearLayout wdxm,wdgzs,wdsc,wdzp,cygl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,container,false);

        findViewId(view);
        String str1 = "300<br><font color='#FFFFFF'>粉丝</font>";
        fs.setText(Html.fromHtml(str1));
        String str2 = "15<br><font color='#FFFFFF'>关注</font>";
        gz.setText(Html.fromHtml(str2));
        // 沉浸式状态栏
        initTitle(view);

        myOnClick();
        return view;
    }

    private void myOnClick() {
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        //替换为TwoFragment
                        .replace(R.id.main_content,new MineSet())
                        .commit();
            }
        });
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        //替换为TwoFragment
                        .replace(R.id.main_content,new MineSetUse())
                        .commit();
            }
        });
        wdxm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        //替换为TwoFragment
                        .replace(R.id.main_content,new MineWdxm())
                        .commit();
            }
        });
        wdgzs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        //替换为TwoFragment
                        .replace(R.id.main_content,new MineWdgzs())
                        .commit();
            }
        });
        wdsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        //替换为TwoFragment
                        .replace(R.id.main_content,new MineWdsc())
                        .commit();
            }
        });
        wdzp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        //替换为TwoFragment
                        .replace(R.id.main_content,new Mine_Wdzp())
                        .commit();
            }
        });
        cygl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        //替换为TwoFragment
                        .replace(R.id.main_content,new MineCyglMainFragment())
                        .commit();
            }
        });


    }

    public void findViewId(View view) {
        head = view.findViewById(R.id.mine_head);
        fs = view.findViewById(R.id.fs);
        gz = view.findViewById(R.id.gz);
        set = view.findViewById(R.id.mine_set);
        wdxm = view.findViewById(R.id.wdxm);
        wdgzs = view.findViewById(R.id.wdgzs);
        wdsc = view.findViewById(R.id.wdsc);
        wdzp = view.findViewById(R.id.wdzp);
        cygl = view.findViewById(R.id.cygl);
    }
}
