package com.kai.api.common;

public enum ArticleType {

    issues("问题"),
    art("艺术"),
    fashion("时尚"),
    photography("照片"),
    video("视频"),
    about("关于"),
    submisstion("应用");


    private String type;

    ArticleType(String type){
        this.type=type;
    }


}
