package com.rc.app.request;

import com.rc.app.constants.LoggerNameConstants;
import org.json.JSONObject;

/**
 * 获取对战玩家信息请求
 * Created by liuyang on 14-9-20.
 */
public class GetBattleTargetRequest extends BaseRequest {

    private static final String JSON_NAME_ARENA_ID = "arenaId";

    private long arenaId;

    @Override
    protected void setOtherField(JSONObject requestJsonObject) {
        JSONObject requestContent = getRequestJsonObject(requestJsonObject);
        if (requestContent == null)
            return;
        this.arenaId = getJsonLong(requestJsonObject, JSON_NAME_ARENA_ID);
    }

    @Override
    protected String requestLoggerName() {
        return LoggerNameConstants.GET_BATTLE_TARGET_LOGGER;
    }

    public long getArenaId() {
        return arenaId;
    }

    public void setArenaId(long arenaId) {
        this.arenaId = arenaId;
    }
}
