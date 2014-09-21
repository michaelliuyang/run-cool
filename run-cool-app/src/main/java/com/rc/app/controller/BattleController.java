package com.rc.app.controller;

import com.rc.app.constants.Constants;
import com.rc.app.constants.ProtocolConstants;
import com.rc.app.constants.RequestType;
import com.rc.app.constants.ResponseReturnCode;
import com.rc.app.model.Arena;
import com.rc.app.model.Prop;
import com.rc.app.model.User;
import com.rc.app.request.BaseRequest;
import com.rc.app.request.GetArenaRequest;
import com.rc.app.response.BaseResponse;
import com.rc.app.response.GetArenaResponse;
import com.rc.app.service.ArenaService;
import com.rc.app.service.UserService;
import com.rc.app.tools.LogContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 关于对战的controller
 * Created by michael on 14-9-20.
 */
@Controller
@RequestMapping("/battle")
public class BattleController {

    @Autowired
    private UserService userService;
    @Autowired
    private ArenaService arenaService;

    @RequestMapping(value = "/get_arena", method = RequestMethod.POST)
    @ResponseBody
    public byte[] getArena(HttpServletRequest request) throws Exception {
        LogContext logContext = LogContext.instance();
        try {
            GetArenaRequest getArenaRequest = new GetArenaRequest();
            getArenaRequest.parse(request);
            GetArenaResponse getArenaResponse = new GetArenaResponse(ProtocolConstants.PROTOCOL_V1_0,
                    getArenaRequest.getUserId());
            if (!isRightRequest(getArenaRequest, RequestType.GET_ARENA)) {
                logContext.warn("Illegal get product list request");
                getArenaResponse.setReturnCode(ResponseReturnCode.ILLEGAL_REQUEST.getIndex());
                return getArenaResponse.convert2ByteResult();
            }
            User user = dealCommonBiz(getArenaRequest, getArenaResponse);
            List<Prop> propList = arenaService.getPropList();
            List<Arena> arenaList = arenaService.findAll();
            getArenaResponse.setProps(propList);
            getArenaResponse.setArenas(arenaList);
            getArenaResponse.setJoinedCount(user.getJoinArenaCount());
            getArenaResponse.setAllow(user.isAllowJoinArena() ?
                    Constants.RESPONSE_BOOLEAN_VALUE_YES : Constants.RESPONSE_BOOLEAN_VALUE_NO);
            if (getArenaRequest.isGetBattleInfo()) {
                //TODO 是否下发具体对战记录
            }
            return getArenaResponse.convert2ByteResult();
        } catch (Exception e) {
            logContext.error(e, "Get arena error");
            throw e;
        }
    }

    @RequestMapping(value = "/get_target", method = RequestMethod.POST)
    @ResponseBody
    public byte[] getTarget(HttpServletRequest request) throws Exception {
        return null;
    }

    @RequestMapping(value = "/upload_result", method = RequestMethod.POST)
    @ResponseBody
    public byte[] uploadResult(HttpServletRequest request) throws Exception {
        return null;
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.POST)
    @ResponseBody
    public byte[] getRanking(HttpServletRequest request) throws Exception {
        return null;
    }

    private boolean isRightRequest(BaseRequest request, RequestType requestType) {
        return requestType.equals(request.getType());
    }

    private User dealCommonBiz(BaseRequest request, BaseResponse response) throws Exception {
        User user = userService.checkUser(request);
        response.setUserId(user.getUserId());
        response.setBattleResult(user.formatUserBattleInfo());
        return user;
    }

}
