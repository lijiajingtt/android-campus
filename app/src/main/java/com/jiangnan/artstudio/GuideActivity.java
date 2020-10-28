package com.jiangnan.artstudio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jiangnan.artstudio.db.User;
import com.jiangnan.artstudio.db.tempData.Check;

import org.litepal.tablemanager.Connector;

import java.util.Timer;
import java.util.TimerTask;

public class GuideActivity extends BaseActivity {
    private TextView reg;
    private Button login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        // 数据库创建
        this.CreateDatabase();


        findViewId();

        //登陆点击事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(GuideActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //注册点击事件
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(GuideActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //沉浸式状态栏
        initTitle();
    }

    public void findViewId(){
        reg = findViewById(R.id.register);
        login = findViewById(R.id.login);
    }

    public void CreateDatabase() {
        // 判断是否是第一次进入
        if(Check.getInstance().isDefaultValue(this)) {
            // 建立数据库
            Connector.getDatabase();
            // 插入Admin数据
            User user = new User(0,"15666666666", "admin", "123456");
            if(user.save()) {
                Check.getInstance().saveCheckInfo(this, 1);
                System.out.println("***************");
                System.out.println("管理员账号读入成功");
                System.out.println("***************");
            } else {
                System.out.println("***************");
                System.out.println("管理员账号读入失败");
                System.out.println("***************");
            }
        } else {
            System.out.println("已经存在数据库且存储值目前为*********" + Check.getInstance().getCheckInfo(this).getRegisterAdminFlag());

        }
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
            Toast.makeText(GuideActivity.this,"再按一次退出程序！", Toast.LENGTH_SHORT).show();
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
}
