package com.maxi.weixiao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mingzhi.yuan on 3/21/16.
 */
public class Novel implements Serializable {

    private int id = 0;
    private String account = null;
    private String title = null;
    private String author = null;
    private String brief = null;
    private String picName = null;
    private String editTime = null;


    private List<Part> mParts = null;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public List<Part> getParts() {
        return mParts;
    }

    public void setParts(List<Part> mParts) {
        this.mParts = mParts;
    }


}
