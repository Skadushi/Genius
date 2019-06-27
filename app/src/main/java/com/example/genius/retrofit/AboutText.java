package com.example.genius.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutText {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("textptbr")
    @Expose
    private String textptbr;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextptbr() {
        return textptbr;
    }

    public void setTextptbr(String textptbr) {
        this.textptbr = textptbr;
    }

}