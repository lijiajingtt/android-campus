package com.jiangnan.artstudio.db;


import org.litepal.crud.DataSupport;

/**
 * LitePal数据表User，用于存储user数据，如若发生改变，请更改litepal.xml内的version版本号，+1即可
 * 李家劲
 */
public class User extends DataSupport {
    private int id;
    private String phoneNumber;
    private String mailbox;
    private String username;
    private String password;

    public User(int flag, String phoneOrMail, String username, String password) {
        // 如果flag==0 则为电话号码,否则为电子邮箱
        if(flag == 0) {
            this.phoneNumber = phoneOrMail;
            this.mailbox = "";
        } else {
            this.mailbox = phoneOrMail;
            this.phoneNumber = "";
        }
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
