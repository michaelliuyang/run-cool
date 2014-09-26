package com.rc.app.controller;

import com.rc.app.constants.Constants;
import com.rc.app.constants.RequestType;
import com.rc.app.constants.ResponseReturnCode;
import com.rc.app.model.Arena;
import com.rc.app.model.User;
import com.rc.app.request.GetArenaRequest;
import com.rc.app.response.GetArenaResponse;
import com.rc.app.service.ArenaService;
import com.rc.app.service.BattleHistoryService;
import com.rc.app.tools.LogContext;
import com.rc.app.vo.ArenaVO;
import com.rc.app.vo.BattleHistoryVO;
import com.rc.app.vo.PropVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 竞技场controller
 * Created by michael on 14-9-21.
 */
@Controller
@RequestMapping("/arena")
public class ArenaController extends BaseController {

    @Autowired
    private ArenaService arenaService;
    @Autowired
    private BattleHistoryService battleHistoryService;

    @RequestMapping(value = "/get_list", method = RequestMethod.POST)
    @ResponseBody
    public byte[] getArena(HttpServletRequest request) throws Exception {
        LogContext logContext = LogContext.instance();
        try {
            GetArenaRequest getArenaRequest = new GetArenaRequest();
            getArenaRequest.parse(request);
            GetArenaResponse getArenaResponse = new GetArenaResponse(getArenaRequest.getProtocol(),
                    getArenaRequest.getUserId());
            if (!isRightRequest(getArenaRequest, RequestType.GET_ARENA)) {
                logContext.warn("Illegal request");
                getArenaResponse.setReturnCode(ResponseReturnCode.ILLEGAL_REQUEST.getIndex());
                return getArenaResponse.convert2ByteResult();
            }
            User user = dealCommonBiz(getArenaRequest, getArenaResponse);
            List<PropVO> propList = arenaService.getPropList();
            List<Arena> arenaList = arenaService.getArenaList();
            getArenaResponse.setProps(propList);
            getArenaResponse.setArenas(convert2ArenaVOList(arenaList));
            getArenaResponse.setJoinedCount(user.getJoinArenaCount());
            getArenaResponse.setAllow(user.isAllowJoinArena() ?
                    Constants.RESPONSE_BOOLEAN_VALUE_YES : Constants.RESPONSE_BOOLEAN_VALUE_NO);
            if (getArenaRequest.isGetBattleInfo()) {
                List<BattleHistoryVO> battleHistoryVOList = battleHistoryService.
                        getRecentBattleHistoryList(user.getUserId());
                getArenaResponse.setBattleHistorys(battleHistoryVOList);
            }
            return getArenaResponse.convert2ByteResult();
        } catch (Exception e) {
            logContext.error(e, "Get arena request error");
            throw e;
        }
    }

    private List<ArenaVO> convert2ArenaVOList(List<Arena> arenaList) {
        if (arenaList == null || arenaList.isEmpty())
            return new ArrayList<ArenaVO>();
        int listSize = arenaList.size();
        List<ArenaVO> voList = new ArrayList<ArenaVO>(listSize);
        for (Arena arena : arenaList) {
            voList.add(new ArenaVO(arena));
        }
        return voList;
    }

}
