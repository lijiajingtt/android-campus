package com.jiangnan.artstudio.db.tempData;

import android.content.Context;
import android.content.SharedPreferences;

import com.jiangnan.artstudio.GuideActivity;
import com.jiangnan.artstudio.db.tempData.bean.CheckBean;

/**
 * 用于存储关于数据库是否第一次创建的Flag
 * 李家劲
 */
public class Check {
    private static Check instance;

    private Check() {
    }

    public static Check getInstance() {
        if (instance == null) {
            instance = new Check();
        }
        return instance;
    }


    /**
     * 保存自动登录的用户信息
     */
    public void saveCheckInfo(Context context, int adminFlag) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        //Context.MODE_PRIVATE表示SharePrefences的数据只有自己应用程序能访问。
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("AdminFlag", adminFlag);
        editor.commit();
    }


    /**
     * 获取用户信息model
     *
     * @param context
     * @param
     * @param
     */
    public CheckBean getCheckInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        CheckBean checkBean = new CheckBean();
        checkBean.setRegisterAdminFlag(sp.getInt("AdminFlag", 0));
        return checkBean;
    }


    /**
     * checkBean中是否为默认值
     * @param context
     */
    public boolean isDefaultValue(GuideActivity context) {
        CheckBean checkBean = getCheckInfo(context);
        if (checkBean != null) {
            if ( checkBean.getRegisterAdminFlag() == 0 ) {// 仍然为默认值
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
