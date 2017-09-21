package com.example.mlab.firebasestorage;

/**
 * Created by mLab on 2017/09/20.
 */

public class Uploads {
    public String name,url;
    public Uploads(){

    }

    public Uploads(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
