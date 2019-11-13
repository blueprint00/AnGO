package com.e.ango.Recommand;

import java.util.ArrayList;

public class ServerResponse {
    String response_msg;// : “RecommendCategory_fail”
    ArrayList<Category_list> category_list;
    String category_id;
    String category_name;
    String total;

    public ServerResponse(String response_msg, ArrayList<Category_list> category_list, String category_id, String category_name, String total) {
        this.response_msg = response_msg;
        this.category_list = category_list;
        this.category_id = category_id;
        this.category_name = category_name;
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

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
