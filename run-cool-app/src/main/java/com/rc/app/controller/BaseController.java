package com.rc.app.controller;

import com.rc.app.constants.RequestType;
import com.rc.app.model.User;
import com.rc.app.request.BaseRequest;
import com.rc.app.response.BaseResponse;
import com.rc.app.service.UserService;
import com.rc.app.tools.LogContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 父controller
 * Created by michael on 14-9-21.
 */
public abstract class BaseController {

    @Autowired
    protected UserService userService;

    protected boolean isRightRequest(BaseRequest request, RequestType requestType) {
        return requestType.equals(request.getType());
    }

    protected User dealCommonBiz(BaseRequest request, BaseResponse response) throws Exception {
        User user = userService.checkUser(request);
        if (StringUtils.isBlank(request.getUserId())) {
            request.setUserId(user.getUserId());
            LogContext.instance().setUserId(user.getUserId());
        }
        response.setUserId(user.getUserId());
        return user;
    }

}
