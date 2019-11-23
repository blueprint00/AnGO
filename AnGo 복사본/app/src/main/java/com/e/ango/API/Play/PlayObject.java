package com.e.ango.API.Play;

public class PlayObject {
    public String addr1;
    public String addr2;
    public long areacode;
    public String cat1;
    public String cat2;
    public String cat3;
    public long contentid;
    public long contenttypeid;
    public long createdtime;
    public long dist;
    public String firstimage2;
    public String title;

    public PlayObject() { }

    public PlayObject(String addr1, String addr2, long areacode, String cat1, String cat2, String cat3, long contentid, long contenttypeid, long createdtime, long dist, String firstimage2, String title) {
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.areacode = areacode;
        this.cat1 = cat1;
        this.cat2 = cat2;
        this.cat3 = cat3;
        this.contentid = contentid;
        this.contenttypeid = contenttypeid;
        this.createdtime = createdtime;
        this.dist = dist;
        this.firstimage2 = firstimage2;
        this.title = title;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public void setAreacode(long areacode) {
        this.areacode = areacode;
    }

    public void setCat1(String cat1) {
        this.cat1 = cat1;
    }

    public void setCat2(String cat2) {
        this.cat2 = cat2;
    }

    public void setCat3(String cat3) {
        this.cat3 = cat3;
    }

    public void setContentid(long contentid) {
        this.contentid = contentid;
    }

    public void setContenttypeid(long contenttypeid) {
        this.contenttypeid = contenttypeid;
    }

    public void setCreatedtime(long createdtime) {
        this.createdtime = createdtime;
    }

    public void setDist(long dist) {
        this.dist = dist;
    }

    public void setFirstimage2(String firstimage2) {
        this.firstimage2 = firstimage2;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddr1() {
        return addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public long getAreacode() {
        return areacode;
    }

    public String getCat1() {
        return cat1;
    }

    public String getCat2() {
        return cat2;
    }

    public String getCat3() {
        return cat3;
    }

    public long getContentid() {
        return contentid;
    }

    public long getContenttypeid() {
        return contenttypeid;
    }

    public long getCreatedtime() {
        return createdtime;
    }

    public long getDist() {
        return dist;
    }

    public String getFirstimage2() {
        return firstimage2;
    }

    public String getTitle() {
        return title;
    }
}
