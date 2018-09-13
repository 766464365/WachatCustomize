package com.xuwei.wachatcustomize.main.fragments.data;

public class DataBean {
    private int imageId;        //头像id
    private String idName;      //名字
    private String text;        //最后一条消息
    private String time;        //时间

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

