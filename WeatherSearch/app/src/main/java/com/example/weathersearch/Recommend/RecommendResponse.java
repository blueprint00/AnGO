package com.example.weathersearch.Recommend;

import java.util.ArrayList;

public class RecommendResponse {
    String response_msg;// : “RecommendCategory_fail”
    ArrayList<Category_list> category_list;
    int total;

    public RecommendResponse() {
    }

    public RecommendResponse(String response_msg, ArrayList<Category_list> category_list, int total) {
        this.response_msg = response_msg;
        this.category_list = category_list;
        this.total = total;
    }

    public String getResponse_msg() {
        return response_msg;
    }

    public void setResponse_msg(String response_msg) {
        this.response_msg = response_msg;
    }

    public ArrayList<Category_list> getCategory_list() {
        return category_list;
    }

    public void setCategory_list(ArrayList<Category_list> category_list) {
        this.category_list = category_list;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
