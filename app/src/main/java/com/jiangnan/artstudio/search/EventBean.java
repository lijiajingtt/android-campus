package com.jiangnan.artstudio.search;


/**
 * Created by ljj.
 */
public class EventBean {


    private int evImg;
    private String evTitle;
    private String evTime;
    private String evBtnIsFinish;
    private String evBtnIsAdd;

    public EventBean() {

    }

    public EventBean(int evImg, String evTitle, String evTime, String evBtnIsFinish, String evBtnIsAdd) {
        this.evImg = evImg;
        this.evTitle = evTitle;
        this.evTime = evTime;
        this.evBtnIsFinish = evBtnIsFinish;
        this.evBtnIsAdd = evBtnIsAdd;
    }

    public int getEvImg() {
        return evImg;
    }

    public void setEvImg(int evImg) {
        this.evImg = evImg;
    }

    public String getEvTitle() {
        return evTitle;
    }

    public void setEvTitle(String evtitle) {
        this.evTitle = evTitle;
    }

    public String getEvTime() {
        return evTime;
    }

    public void setEvTime(String evTime) {
        this.evTime = evTime;
    }

    public String getEvBtnIsFinish() {
        return evBtnIsFinish;
    }

    public void setEvBtnIsFinish(String evBtnIsFinish) {
        this.evBtnIsFinish = evBtnIsFinish;
    }

    public String getEvBtnIsAdd() {
        return evBtnIsAdd;
    }

    public void setEvBtnIsAdd(String evBtnIsAdd) {
        this.evBtnIsAdd = evBtnIsAdd;
    }
}
