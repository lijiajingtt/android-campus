package com.jiangnan.artstudio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jiangnan.artstudio.db.User;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends BaseActivity {
    private ImageView headPortrait;
    private EditText account,password,verification,account_edit_top;
    private Button getVerification,login;
    private TextView change, account_top;
    private LinearLayout viewPassword,viewVerification;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        findViewId();

        // 获取由注册传递过来的信息
        getRegisterInfo();

        onClick();

        //沉浸式状态栏
        initTitle();

        //状态栏字体变黑
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void onClick() {
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPassword.getVisibility()==View.GONE){
                    change.setText("用短信验证码登陆");
                    viewPassword.setVisibility(View.VISIBLE);
                    viewVerification.setVisibility(View.GONE);
                    account_top.setText("账号");
                    account_edit_top.setHint("请填写账号");
                }else{
                    change.setText("用密码登陆");
                    viewVerification.setVisibility(View.VISIBLE);
                    viewPassword.setVisibility(View.GONE);
                    account_top.setText("手机号");
                    account_edit_top.setHint("请填写手机号");
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmLogin();
                //finish();
            }
        });
    }

    public void getRegisterInfo() {
        Intent intent = getIntent();
        String getUsername = intent.getStringExtra("username");
        String getPassword = intent.getStringExtra("password");
        account.setText(getUsername);
        password.setText(getPassword);
    }

    public void findViewId(){
        headPortrait = findViewById(R.id.lg_headPortrait);
        account = findViewById(R.id.lg_account_edit_top);
        password = findViewById(R.id.lg_password);
        verification = findViewById(R.id.lg_verification);
        getVerification = findViewById(R.id.lg_getVerification);
        login = findViewById(R.id.lg_login);
        change = findViewById(R.id.lg_change);
        viewPassword = findViewById(R.id.lg_viewPassword);
        viewVerification = findViewById(R.id.lg_viewVerification);

        account_top = findViewById(R.id.lg_account_top);
        account_edit_top = findViewById(R.id.lg_account_edit_top);
    }

    public void confirmLogin() {
        String myUsername = account.getText().toString().trim();
        String myPassword = password.getText().toString().trim();

        //数据库查询
        boolean check_username = false;
        boolean check_password = false;
        List<User> user = DataSupport.where("username=?",myUsername).find(User.class);
        if(user.isEmpty()) {
            check_username = false;
        } else {
            check_username = true;
            for(User user_s : user) {
                if(myPassword.equals( user_s.getPassword() ) ) {
                    check_password = true;
                } else {
                    check_password = false;
                }
            }
        }
        if(check_username && check_password) {
            deliverInfoAndGo(myUsername,myPassword);
            Toast.makeText(this,"登入成功",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"账号或密码错误",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK){
            exit2();
        }
        return false;
    }

    public void deliverInfoAndGo(String myAccount, String myPassword) {
        Intent intent = new Intent();
        intent.putExtra("username", myAccount);
        intent.putExtra("password", myPassword);
        intent.setClass(this, MainActivity.class);
        this.startActivity(intent);
    }

    private boolean isExit=false;
    private void exit2() {
        if (isExit==false){
            isExit=true;
            Timer time=new Timer();
            Toast.makeText(LoginActivity.this,"再按一次退出程序！", Toast.LENGTH_SHORT).show();
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
