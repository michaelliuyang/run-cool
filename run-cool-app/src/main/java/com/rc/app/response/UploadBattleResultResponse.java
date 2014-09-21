package com.rc.app.response;

import com.rc.app.constants.RequestType;

/**
 * 上传对战结果回复
 * Created by michael on 14-9-20.
 */
public class UploadBattleResultResponse extends BaseResponse {

    public UploadBattleResultResponse(String protocol, String userId) {
        super(protocol, userId);
    }

    @Override
    protected RequestType requestType() {
        return RequestType.UPLOAD_BATTLE_RESULT;
    }
}
