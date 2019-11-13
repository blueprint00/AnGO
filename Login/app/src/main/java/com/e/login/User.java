package com.e.login;

public class User {

    String request_msg;
    String id;
    String pw;
    String nm;

    public User(){}
    public User(String userId, String request_msg){
        this.id = userId;
        this.request_msg = request_msg;
    }
    public User(String userId, String userPass, String request_msg) {
        this.request_msg = request_msg;
        this.id = userId;
        this.pw = userPass;
    }

    public User(String userId, String userPass, String userName, String request_msg) {
        this.request_msg = request_msg;
        this.id = userId;
        this.pw = userPass;
        this.nm = userName;
    }

    public String getUserPass() {
        return pw;
    }

    public void setUserPass(String userPass) {
        this.pw = userPass;
    }

    public String getUserId() {
        return id;
    }

    public void setUserId(String userId) {
        this.id = userId;
    }

    public String getUserName() {
        return nm;
    }

    public void setUserName(String name) {
        this.nm  = nm;
    }

    public String getRequest_msg() {
        return request_msg;
    }

    public void setRequest_msg(String request_msg) {
        this.request_msg = request_msg;
    }
}
