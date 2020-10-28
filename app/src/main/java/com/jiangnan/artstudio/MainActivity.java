package com.jiangnan.artstudio;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jiangnan.artstudio.db.User;
import com.jiangnan.artstudio.fragment.EventFragment;
import com.jiangnan.artstudio.fragment.MineFragment;
import com.jiangnan.artstudio.fragment.SceneFragment;
import com.jiangnan.artstudio.fragment.WorkRoomFragment;


import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {
    private LinearLayout event,workroom,scene,mine;
    private EventFragment ef;
    private WorkRoomFragment wf;
    private SceneFragment sf;
    private MineFragment mf;
    private FragmentManager fManager;

    public static String myAccount,myPassword,myPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // 获取到登录的信息，并将该信息从数据库解析出myAccount,myPassword,myPhoneNumber
        getLoginInfo();

        findViewId();

        initTitle();

        fManager = getSupportFragmentManager();
        onClick();

        // 暂时解决登录后项目页面状态栏会出现沉浸式开启失败的情况，下次有问题在改
        event.performClick();
        workroom.performClick();
        event.performClick();
    }

    public void findViewId() {
        event = findViewById(R.id.main_event);
        workroom = findViewById(R.id.main_workroom);
        scene = findViewById(R.id.main_scene);
        mine = findViewById(R.id.main_mine);
    }

    public void setSelected(){
        event.setSelected(false);
        workroom.setSelected(false);
        scene.setSelected(false);
        mine.setSelected(false);
    }
    public void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(ef != null)fragmentTransaction.hide(ef);
        if(wf != null)fragmentTransaction.hide(wf);
        if(sf != null)fragmentTransaction.hide(sf);
        if(mf != null)fragmentTransaction.hide(mf);
    }
    public void onClick() {
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                event.setSelected(true);
                ef = new EventFragment();
                fTransaction.add(R.id.main_content,ef).commit();
            }
        });
        workroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                workroom.setSelected(true);
                wf = new WorkRoomFragment();
                fTransaction.add(R.id.main_content,wf).commit();
            }
        });
        scene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                scene.setSelected(true);
                sf = new SceneFragment();
                fTransaction.add(R.id.main_content,sf).commit();
            }
        });
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                mine.setSelected(true);
                mf = new MineFragment();
                fTransaction.add(R.id.main_content,mf).commit();
            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK){
            exit2();
        }
        return false;
    }

    private boolean isExit=false;
    private void exit2() {
        if (isExit==false){
            isExit=true;
            Timer time=new Timer();
            Toast.makeText(MainActivity.this,"再按一次退出程序！", Toast.LENGTH_SHORT).show();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit=false;
                }
            },2000);
        }else {
            finish();
            System.exit(0);
        }
    }

    public void getLoginInfo() {
        Intent intent = getIntent();
        String getUsername = intent.getStringExtra("username");
        String getPassword = intent.getStringExtra("password");
        List<User> user = DataSupport.where("username=?",getUsername).find(User.class);
        for(User user_s : user) {
            myAccount = user_s.getUsername();
            myPassword = user_s.getPassword();
            myPhoneNumber = user_s.getPhoneNumber();
        }
    }
}
