package com.testsample.dto;

/**
 * Created by Ananth kumar on 29-06-2017.
 */

public class Modelurl {
    String urllink, playstatus;

    public String getUrllink() {
        return urllink;
    }

    public void setUrllink(String urllink) {
        this.urllink = urllink;
    }

    public Modelurl(String urllink, String playstatus) {

        this.playstatus = playstatus;
        this.urllink = urllink;
    }

    public String getPlaystatus() {
        return playstatus;
    }

    public void setPlaystatus(String playstatus) {
        this.playstatus = playstatus;
    }
}
