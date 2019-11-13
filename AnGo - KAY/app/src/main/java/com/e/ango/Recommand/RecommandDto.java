package com.e.ango.Recommand;

public class RecommandDto {
    String request_msg;
    String weather_type;
    String token; //유저 토큰값

    public RecommandDto() { }

    public RecommandDto(String request_msg, String weather_type, String token) {
        this.request_msg = request_msg;
        this.weather_type = weather_type;
        this.token = token;
    }

    public String getRequest_msg() {
        return request_msg;
    }

    public void setRequest_msg(String request_msg) {
        this.request_msg = request_msg;
    }

    public String getWeather_type() {
        return weather_type;
    }

    public void setWeather_type(String weather_type) {
        this.weather_type = weather_type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
