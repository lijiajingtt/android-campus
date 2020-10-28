package com.jiangnan.artstudio.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiangnan.artstudio.R;

public class WorkRoomMainFragment extends BaseFragment {

    private ImageView jjImg,xmImg,dtImg;
    private TextView jj,xm,dt;
    private FragmentManager fManager;
    private DongTai dongTai;
    private JianJie jianJie;
    private XiangMu xiangMu;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.workroom_main,container,false);

        findViewId(view);
        initTitle(view);
        fManager = getFragmentManager();

        onClick();
        jj.performClick();
        return view;
    }

    public void setNormal(){
        dt.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        xm.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        jj.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
    }

    public void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(dongTai != null)fragmentTransaction.hide(dongTai);
        if(xiangMu != null)fragmentTransaction.hide(xiangMu);
        if(jianJie != null)fragmentTransaction.hide(jianJie);
    }

    public void findViewId(View v) {
        jj = v.findViewById(R.id.jianjie);
        xm = v.findViewById(R.id.xiangmu);
        dt = v.findViewById(R.id.dongtai);

        jjImg = v.findViewById(R.id.jianjie_arrow);
        xmImg = v.findViewById(R.id.xiangmu_arrow);
        dtImg = v.findViewById(R.id.dongtai_arrow);
    }

    public void onClick() {
        jj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                jianJie = new JianJie();
                setNormal();
                jj.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                fTransaction.add(R.id.workroom_content,jianJie).commit();

                setVisible(View.VISIBLE,View.INVISIBLE,View.INVISIBLE);
            }
        });
        xm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                xiangMu = new XiangMu();
                setNormal();
                xm.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                fTransaction.add(R.id.workroom_content,xiangMu).commit();

                setVisible(View.INVISIBLE,View.VISIBLE,View.INVISIBLE);
            }
        });
        dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                dongTai = new DongTai();
                setNormal();
                dt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                fTransaction.add(R.id.workroom_content,dongTai).commit();

                setVisible(View.INVISIBLE,View.INVISIBLE,View.VISIBLE);
            }
        });

    }

    public void setVisible(int a, int b, int c) {
        jjImg.setVisibility(a);
        xmImg.setVisibility(b);
        dtImg.setVisibility(c);
    }
}
