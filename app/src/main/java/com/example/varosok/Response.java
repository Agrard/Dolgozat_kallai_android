package com.example.varosok;

public class Response {
    private int responseCode;
    private String content;

    public Response(int responseCode, String content) {
        this.responseCode = responseCode;
        this.content = content;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getContent() {
        return content;
    }
}
