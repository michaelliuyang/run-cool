package com.rc.app.model;

/**
 * 道具
 * Created by michael on 14-9-21.
 */
public class Prop {

    private String name;
    private int limit;

    public Prop(String name, int limit) {
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
