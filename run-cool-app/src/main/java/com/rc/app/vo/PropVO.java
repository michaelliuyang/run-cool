package com.rc.app.vo;

/**
 * 道具
 * Created by michael on 14-9-21.
 */
public class PropVO {

    private String name;
    private int limit;

    public PropVO(String name, int limit) {
        this.name = name;
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
