package com.rc.app.response;

import com.rc.app.constants.RequestType;

/**
 * 上传对战结果回复
 * Created by michael on 14-9-20.
 */
public class UploadBattleResultResponse extends BaseResponse {

    private int userRanking;
    private int targetRanking;

    public UploadBattleResultResponse(String protocol, String userId) {
        super(protocol, userId);
    }

    @Override
    protected RequestType requestType() {
        return RequestType.UPLOAD_BATTLE_RESULT;
    }

    public int getUserRanking() {
        return userRanking;
    }

    public void setUserRanking(int userRanking) {
        this.userRanking = userRanking;
    }

    public int getTargetRanking() {
        return targetRanking;
    }

    public void setTargetRanking(int targetRanking) {
        this.targetRanking = targetRanking;
    }
}
