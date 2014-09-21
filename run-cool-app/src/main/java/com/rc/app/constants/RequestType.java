package com.rc.app.constants;

/**
 * Created by michael 2014/9/18.
 */
public enum RequestType {

    GET_ARENA("1"), GET_BATTLE_TARGET("2"),
    UPLOAD_BATTLE_RESULT("3"), GET_RANKING_LIST("4"), ERROR("-1");

    private String index;

    private RequestType(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    public void setTypeIndex(String index) {
        this.index = index;
    }

    public static RequestType getTypeByIndex(String index) {
        for (RequestType requestType : RequestType.values()) {
            if (requestType.getIndex().equals(index))
                return requestType;
        }
        return null;
    }
}
