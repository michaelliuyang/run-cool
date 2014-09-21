package com.rc.app.constants;

/**
 * 道具枚举
 * Created by michael on 14-9-20.
 */
public enum PropConfig {

    SPRINT("S", 2), REVIVE("R", 3);

    private String name;
    private int limit;

    private PropConfig(String name, int limit) {
        this.name = name;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public static PropConfig getTypeByName(String name) {
        for (PropConfig type : PropConfig.values()) {
            if (type.getName().equals(name))
                return type;
        }
        return null;
    }

}
