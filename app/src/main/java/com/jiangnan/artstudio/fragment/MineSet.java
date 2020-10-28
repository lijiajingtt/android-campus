package com.jiangnan.artstudio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jiangnan.artstudio.LoginActivity;
import com.jiangnan.artstudio.MainActivity;
import com.jiangnan.artstudio.R;

public class MineSet extends BaseFragment {
    private TextView set_clear,set_exit,set_cache;
    private RelativeLayout zhgl;
    private ImageView back;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_set,container,false);
        findViewId(view);

        onClick();
        initTitle(view);

        return view;
    }

    public void onClick() {
        zhgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        //替换为TwoFragment
                        .replace(R.id.main_content,new MineSetUse())
                        .commit();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        //替换为TwoFragment
                        .replace(R.id.main_content,new MineFragment())
                        .commit();
            }
        });
        set_clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String xx = set_cache.getText().toString().trim();
                Toast.makeText(getActivity(),"成功清除" + xx,Toast.LENGTH_LONG).show();
                set_cache.setText("0.00MB");
            }
        });
        set_exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"成功退出",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }

    public void findViewId(View view) {
        zhgl = view.findViewById(R.id.zhgl);
        back = view.findViewById(R.id.set_back);
        set_clear = view.findViewById(R.id.set_clear);
        set_exit = view.findViewById(R.id.set_exit);
        set_cache = view.findViewById(R.id.set_cache);
    }
}
