package com.vlaser.demo.domain;

import java.util.Date;

public class Info {

    private Date time;
    private String ip;

    public Info() {}

    public Info(Date time, String ip) {
        this.time = time;
        this.ip = ip;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Info{" +
                "time=" + time +
                ", ip='" + ip + '\'' +
                '}';
    }
}
