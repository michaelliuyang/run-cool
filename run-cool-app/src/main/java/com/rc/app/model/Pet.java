package com.rc.app.model;

/**
 * 宠物
 * Created by liuyang on 14-9-18.
 */
public class Pet {

    private String name;

    public Pet() {
    }

    public Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
