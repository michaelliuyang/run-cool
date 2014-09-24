package com.rc.app.vo;

/**
 * 角色VO
 * Created by michael on 14-9-18.
 */
public class RoleVO {

    private String name;
    private String rank;

    public RoleVO() {
    }

    public RoleVO(String name, String rank) {
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
