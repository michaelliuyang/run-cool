package com.rc.app.constants;

/**
 * Created by michael 2014/9/18.
 */
public enum ResponseReturnCode {

    SUCCESS("0"), ILLEGAL_REQUEST("1"), SERVER_ERROR("2");

    private String index;

    private ResponseReturnCode(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    public void setTypeIndex(String index) {
        this.index = index;
    }

    public static ResponseReturnCode getReturnCodeByIndex(String index) {
        for (ResponseReturnCode returnCode : ResponseReturnCode.values()) {
            if (returnCode.getIndex().equals(index))
                return returnCode;
        }
        return null;
    }
}
