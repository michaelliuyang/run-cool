package com.rc.app.request;

import com.rc.app.constants.BattleResult;
import com.rc.app.constants.Constants;
import com.rc.app.constants.LoggerNameConstants;
import org.json.JSONObject;

/**
 * 上传对战结果请求
 * Created by michael on 14-9-20.
 */
public class UploadBattleResultRequest extends BaseRequest {

    private static final String JSON_NAME_ARENA_ID = "arenaId";
    private static final String JSON_NAME_SCORE = "score";
    private static final String JSON_NAME_RESULT = "result";
    private static final String JSON_NAME_IS_CONTINUE_WIN = "isContinueWin";

    private long arenaId;
    private int score;
    private BattleResult result;
    private boolean isContinueWin;
//    private target target;

    @Override
    protected void setOtherField(JSONObject requestJsonObject) {
        JSONObject requestContent = getRequestJsonObject(requestJsonObject);
        if (requestContent == null)
            return;
        this.arenaId = getJsonLong(requestContent, JSON_NAME_ARENA_ID);
        this.score = getJsonInt(requestContent, JSON_NAME_SCORE);
        this.result = BattleResult.getTypeByDisplayName(getJsonString(requestContent, JSON_NAME_RESULT));
        this.isContinueWin = Constants.RESPONSE_BOOLEAN_VALUE_YES.
                equals(getJsonString(requestContent, JSON_NAME_IS_CONTINUE_WIN));
    }

    public long getArenaId() {
        return arenaId;
    }

    public void setArenaId(long arenaId) {
        this.arenaId = arenaId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public BattleResult getResult() {
        return result;
    }

    public void setResult(BattleResult result) {
        this.result = result;
    }

    public boolean isContinueWin() {
        return isContinueWin;
    }

    public void setContinueWin(boolean isContinueWin) {
        this.isContinueWin = isContinueWin;
    }

    @Override
    protected String requestLoggerName() {
        return LoggerNameConstants.UPLOAD_BATTLE_RESULT_LOGGER;
    }
}
