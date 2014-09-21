package com.rc.app.model;

/**
 * 角色
 * Created by michael on 14-9-18.
 */
public class Role {

    private String name;
    private String rank;

    public Role() {
    }

    public Role(String name, String rank) {
        this.name = name;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
