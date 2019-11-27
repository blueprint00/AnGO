package com.e.ango.RecycleView;

import com.e.ango.R;

public class CurrentLocationPlay {
    String address;
    String category_name;
    String title;

    //int playImage;

    public CurrentLocationPlay(String address, String category_name, String title) {//, int playImage) {
        this.address = address;
        this.category_name = category_name;
        this.title = title;
//        this.playImage = playImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public int getPlayImage() {
//        return playImage;
//    }
//
//    public void setPlayImage(int playImage) {
//        this.playImage = playImage;
//    }
}
