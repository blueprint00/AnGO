package com.e.ango.Login;

public class LoginResponse {
    String response_msg;
    public String token;
    int availability;

    public LoginResponse() { }

    public LoginResponse(String response_msg){
        this.response_msg = response_msg;
    }
    public LoginResponse(String response_msg, String token, int availability) {
        this.response_msg = response_msg;
        this.token = token;
        this.availability = availability;
    }

    public String getResponse_msg() {
        return response_msg;
    }

    public void setResponse_msg(String response_msg) {
        this.response_msg = response_msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
