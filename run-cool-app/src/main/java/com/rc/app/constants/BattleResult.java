package com.rc.app.constants;

/**
 * 对战结果
 * Created by michael on 14-9-18.
 */
public enum BattleResult {

    WIN("W"), LOSE("L");

    private String displayName;

    private BattleResult(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public static BattleResult getTypeByDisplayName(String displayName) {
        for (BattleResult type : BattleResult.values()) {
            if (type.getDisplayName().equals(displayName))
                return type;
        }
        return null;
    }

}
