package com.ptit.management.util;

public class Messages {
    private String param;
    private String content;

    public Messages(String param, String content) {
        this.param = param;
        this.content = content;
    }

    public Messages() {
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
