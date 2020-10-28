package com.jiangnan.artstudio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jiangnan.artstudio.db.User;
import com.jiangnan.artstudio.util.Constant;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends BaseActivity{
    private EditText phoneOrMail,account,password;
    private Button submit;
    private TextView login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        findViewId();
        //注册按钮点击事件
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmRegister();
            }
        });
        //已有账号，登陆点击事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //沉浸式状态栏
        initTitle();

        //状态栏字体变黑
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    public void findViewId(){
        phoneOrMail = findViewById(R.id.re_phoneOrMail);
        account = findViewById(R.id.re_account);
        password = findViewById(R.id.re_password);
        submit = findViewById(R.id.re_submit);
        login = findViewById(R.id.re_login);
    }

    /*
        注册确认
     */
    public void confirmRegister() {
        String myPhoneOrMail = phoneOrMail.getText().toString().trim();
        String myAccount = account.getText().toString().trim();
        String myPassword = password.getText().toString().trim();
        // 模板
        Pattern p_email = Pattern.compile(Constant.REG_MAIL);
        Pattern p_phone = Pattern.compile(Constant.REG_PHONE);
        Pattern p_user = Pattern.compile(Constant.REG_USER);
        Pattern p_pass = Pattern.compile(Constant.REG_PASS);

        // 匹配器
        Matcher m_email = p_email.matcher(myPhoneOrMail);
        Matcher m_phone = p_phone.matcher(myPhoneOrMail);
        Matcher m_user = p_user.matcher(myAccount);
        Matcher m_pass = p_pass.matcher(myPassword);

        //检查是否已经存在
        boolean check_user = false;
        List<User> user1 = DataSupport.where("username=?", myAccount).find(User.class);
        if(user1.size()>0) {
            check_user = true;
        } else {
            check_user = false;
        }

        if(m_user.find() && m_pass.find()) {
            if(check_user) {
                Toast.makeText(this,"该用户已经存在，请重新输入",Toast.LENGTH_LONG).show();
            } else if(m_phone.find()) {
                User user = new User(0, myPhoneOrMail,myAccount,myPassword);
                user.save();

                deliverInfoAndGo(myAccount, myPassword);

                //startActivity(new Intent(this, LoginActivity.class));
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            } else if(m_email.find()) {
                User user = new User(1, myPhoneOrMail,myAccount,myPassword);
                user.save();

                deliverInfoAndGo(myAccount, myPassword);

                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"输入非法，请检查输入是否正确",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"输入非法，请检查输入是否正确",Toast.LENGTH_SHORT).show();
        }
    }


    public void deliverInfoAndGo(String myAccount, String myPassword) {
        Intent intent = new Intent();
        intent.putExtra("username", myAccount);
        intent.putExtra("password", myPassword);
        intent.setClass(this, LoginActivity.class);
        this.startActivity(intent);
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
            Toast.makeText(RegisterActivity.this,"再按一次退出程序！", Toast.LENGTH_SHORT).show();
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
