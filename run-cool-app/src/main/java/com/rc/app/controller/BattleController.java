package com.rc.app.controller;

import com.rc.app.constants.ProtocolConstants;
import com.rc.app.constants.RequestType;
import com.rc.app.constants.ResponseReturnCode;
import com.rc.app.model.User;
import com.rc.app.request.GetBattleTargetRequest;
import com.rc.app.request.GetRankingListRequest;
import com.rc.app.request.UploadBattleResultRequest;
import com.rc.app.response.GetBattleTargetResponse;
import com.rc.app.response.GetRankingListResponse;
import com.rc.app.response.UploadBattleResultResponse;
import com.rc.app.service.RankingService;
import com.rc.app.service.UploadResultService;
import com.rc.app.tools.LogContext;
import com.rc.app.vo.RankingVO;
import com.rc.app.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 对战的controller
 * Created by michael on 14-9-20.
 */
@Controller
@RequestMapping("/battle")
public class BattleController extends BaseController {

    @Autowired
    private UploadResultService uploadResultService;
    @Autowired
    private RankingService rankingService;

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
                logContext.warn("Illegal request");
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
            logContext.error(e, "Get battle target request error");
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
            if (!isRightRequest(uploadBattleResultRequest, RequestType.UPLOAD_BATTLE_RESULT)) {
                logContext.warn("Illegal request");
                uploadBattleResultResponse.setReturnCode(ResponseReturnCode.ILLEGAL_REQUEST
                        .getIndex());
                return uploadBattleResultResponse.convert2ByteResult();
            }
            User user = dealCommonBiz(uploadBattleResultRequest, uploadBattleResultResponse);
            uploadResultService.uploadResult(uploadBattleResultRequest, user);
            return uploadBattleResultResponse.convert2ByteResult();
        } catch (Exception e) {
            logContext.error(e, "Upload battle result request error");
            throw e;
        }
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.POST)
    @ResponseBody
    public byte[] getRanking(HttpServletRequest request) throws Exception {
        LogContext logContext = LogContext.instance();
        try {
            GetRankingListRequest getRankingListRequest = new GetRankingListRequest();
            getRankingListRequest.parse(request);
            GetRankingListResponse getRankingListResponse =
                    new GetRankingListResponse(ProtocolConstants.PROTOCOL_V1_0,
                            getRankingListRequest.getUserId());
            if (!isRightRequest(getRankingListRequest, RequestType.GET_RANKING_LIST)) {
                logContext.warn("Illegal request");
                getRankingListResponse.setReturnCode(ResponseReturnCode.ILLEGAL_REQUEST
                        .getIndex());
                return getRankingListResponse.convert2ByteResult();
            }
            User user = dealCommonBiz(getRankingListRequest, getRankingListResponse);
            List<RankingVO> scoreRankingList = rankingService.
                    getRankingList(user, RankingService.RANKING_SCORE_TYPE);
            List<RankingVO> battleScoreRankingList = rankingService.
                    getRankingList(user, RankingService.RANKING_BATTLE_SCORE_TYPE);
            getRankingListResponse.setScoreRankingList(scoreRankingList);
            getRankingListResponse.setBattleRankingList(battleScoreRankingList);
            return getRankingListResponse.convert2ByteResult();
        } catch (Exception e) {
            logContext.error(e, "Get ranking list request error");
            throw e;
        }
    }

}
