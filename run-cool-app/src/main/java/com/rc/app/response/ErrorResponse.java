package com.rc.app.response;

import com.rc.app.constants.RequestType;
import com.rc.app.constants.ResponseReturnCode;

/**
 * 出现异常后的通用错误回复
 * Created by michael on 2014/9/18.
 */
public class ErrorResponse extends BaseResponse {

    public ErrorResponse(String protocol, String userId) {
        super(protocol, userId);
        this.returnCode = ResponseReturnCode.SERVER_ERROR.getIndex();
    }

    @Override
    protected RequestType requestType() {
        return RequestType.ERROR;
    }
}
