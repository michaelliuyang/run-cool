package com.rc.app.request;

import com.rc.app.constants.LoggerNameConstants;
import org.json.JSONObject;

/**
 * 获取竞技场请求
 * Created by michael on 14-9-20.
 */
public class GetArenaRequest extends BaseRequest {

    private static final String JSON_NAME_IS_GET_BATTLE_INFO = "isGetBattleInfo";

    private boolean isGetBattleInfo;

    @Override
    protected void setOtherField(JSONObject requestJsonObject) {
        JSONObject requestContent = getRequestJsonObject(requestJsonObject);
        if (requestContent == null)
            return;
        this.isGetBattleInfo = "1".equals(getJsonString(requestContent, JSON_NAME_IS_GET_BATTLE_INFO));
    }

    @Override
    protected String requestLoggerName() {
        return LoggerNameConstants.GET_ARENA_LOGGER;
    }

    public boolean isGetBattleInfo() {
        return isGetBattleInfo;
    }

    public void setGetBattleInfo(boolean isGetBattleInfo) {
        this.isGetBattleInfo = isGetBattleInfo;
    }
}
