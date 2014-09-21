package com.rc.app.controller;

import com.rc.app.constants.ProtocolConstants;
import com.rc.app.constants.RequestType;
import com.rc.app.constants.ResponseReturnCode;
import com.rc.app.model.User;
import com.rc.app.request.GetBattleTargetRequest;
import com.rc.app.response.GetBattleTargetResponse;
import com.rc.app.tools.LogContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 对战的controller
 * Created by michael on 14-9-20.
 */
@Controller
@RequestMapping("/battle")
public class BattleController extends BaseController {

    @RequestMapping(value = "/get_target", method = RequestMethod.POST)
    @ResponseBody
    public byte[] getTarget(HttpServletRequest request) throws Exception {
        LogContext logContext = LogContext.instance();
        try {
            GetBattleTargetRequest getBattleTargetRequest = new GetBattleTargetRequest();
            getBattleTargetRequest.parse(request);
            GetBattleTargetResponse getBattleTargetResponse = new GetBattleTargetResponse(ProtocolConstants.PROTOCOL_V1_0,
                    getBattleTargetRequest.getUserId());
            if (!isRightRequest(getBattleTargetRequest, RequestType.GET_BATTLE_TARGET)) {
                logContext.warn("Illegal get battle target request");
                getBattleTargetResponse.setReturnCode(ResponseReturnCode.ILLEGAL_REQUEST.getIndex());
                return getBattleTargetResponse.convert2ByteResult();
            }
            User user = dealCommonBiz(getBattleTargetRequest, getBattleTargetResponse);
            User targetUser = new User();
            if (user.isAllowJoinArena()) {
                targetUser = userService.getTargetUser(user.getIsContinueWin(),
                        user.getMaxBattleScore(), user.getUserId());
                //TODO 下发对战信息
            }
            getBattleTargetResponse.setBattleTargetUser(targetUser);
            return getBattleTargetResponse.convert2ByteResult();
        } catch (Exception e) {
            logContext.error(e, "Get battle target error");
            throw e;
        }
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

}
