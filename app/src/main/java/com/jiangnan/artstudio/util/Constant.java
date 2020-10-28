package com.jiangnan.artstudio.util;

/**
 * 李家劲
 * 用于存储需要用到的常量
 */
public class Constant {
    // 正则表达式常量
    public static final String REG_USER = "^[a-zA-Z]\\w{3,21}$";//以a-z,A-Z开头，最少4个最多20个的字符串
    public static final String REG_PASS = "^[0-9a-zA-Z]\\w{5,21}$";//以a-z,A-Z，0-9开头，最少6个最多20个的字符串
    public static final String REG_MAIL = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:" +
            "[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?"; // 符合邮箱格式即可
    public static final String REG_PHONE = "1[3578]\\d{9}"; // 符合1开头，3578作为第二位数，总共11位即可
}
