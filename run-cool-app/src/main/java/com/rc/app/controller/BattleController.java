package com.rc.app.controller;

import com.rc.app.constants.BattleResult;
import com.rc.app.constants.ProtocolConstants;
import com.rc.app.constants.RequestType;
import com.rc.app.constants.ResponseReturnCode;
import com.rc.app.model.Arena;
import com.rc.app.model.User;
import com.rc.app.request.GetBattleTargetRequest;
import com.rc.app.request.UploadBattleResultRequest;
import com.rc.app.response.GetBattleTargetResponse;
import com.rc.app.response.UploadBattleResultResponse;
import com.rc.app.service.ArenaService;
import com.rc.app.service.BattleHistoryService;
import com.rc.app.tools.LogContext;
import com.rc.app.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BattleHistoryService battleHistoryService;
    @Autowired
    private ArenaService arenaService;

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
            }
            getBattleTargetResponse.setBattleTargetUser(new UserVO(targetUser));
            return getBattleTargetResponse.convert2ByteResult();
        } catch (Exception e) {
            logContext.error(e, "Get battle target error");
            throw e;
        }
    }

    @RequestMapping(value = "/upload_result", method = RequestMethod.POST)
    @ResponseBody
    public byte[] uploadResult(HttpServletRequest request) throws Exception {
        LogContext logContext = LogContext.instance();
        try {
            UploadBattleResultRequest uploadBattleResultRequest = new UploadBattleResultRequest();
            uploadBattleResultRequest.parse(request);
            UploadBattleResultResponse uploadBattleResultResponse =
                    new UploadBattleResultResponse(ProtocolConstants.PROTOCOL_V1_0,
                            uploadBattleResultRequest.getUserId());
            if (!isRightRequest(uploadBattleResultRequest, RequestType.GET_BATTLE_TARGET)) {
                logContext.warn("Illegal get battle target request");
                uploadBattleResultResponse.setReturnCode(ResponseReturnCode.ILLEGAL_REQUEST
                        .getIndex());
                return uploadBattleResultResponse.convert2ByteResult();
            }
            User user = dealCommonBiz(uploadBattleResultRequest, uploadBattleResultResponse);
            battleHistoryService.insert(uploadBattleResultRequest);
            int rewardScore = 0;
            if (BattleResult.WIN.equals(uploadBattleResultRequest.getResult())) {
                Arena arena = arenaService.findById(uploadBattleResultRequest.getArenaId());
                rewardScore = arena.getRewardScore();
            }
            userService.updateUserScoreInfo(user, rewardScore, uploadBattleResultRequest);
            return uploadBattleResultResponse.convert2ByteResult();
        } catch (Exception e) {
            logContext.error(e, "Upload battle result error");
            throw e;
        }
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.POST)
    @ResponseBody
    public byte[] getRanking(HttpServletRequest request) throws Exception {
        return null;
    }

}
