package com.example.studiekoll;

import java.io.Serializable;

public class CourseClass implements Serializable {
    Integer mImageResource;
    String mTitle;
    Integer mHours;
    String mString;

    public CourseClass(Integer imageResource, String title, Integer hours, String exampleString){
        mImageResource=imageResource;
        mTitle=title;
        mHours=hours;
        mString=exampleString;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setHours(Integer mHours) {
        this.mHours = mHours;
    }

    public void changeTitle(String text){
        mTitle = text;
    }

    public Integer getImageResource() {
        return mImageResource;
    }

    public String getTitle() {
        return mTitle;
    }

    public Integer getHours() {
        return mHours;
    }

    public String getExampleString() {
        return mString;
    }
}
