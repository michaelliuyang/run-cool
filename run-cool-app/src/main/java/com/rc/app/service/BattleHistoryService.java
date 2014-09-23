package com.rc.app.service;

import com.rc.app.mapper.BattleHistoryMapper;
import com.rc.app.model.BattleHistory;
import com.rc.app.request.UploadBattleResultRequest;
import com.rc.app.tools.LogContext;
import com.rc.app.vo.BattleHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 对战历史业务类
 * Created by michael on 14-9-21.
 */
@Service
public class BattleHistoryService {

    @Autowired
    private BattleHistoryMapper battleHistoryMapper;

    public List<BattleHistoryVO> getRecentBattleHistoryList(String userId) {
        List<BattleHistoryVO> result = new ArrayList<BattleHistoryVO>();
        try {
            result = battleHistoryMapper.findRecentByUserId(userId);
        } catch (Exception e) {
            LogContext.instance().error(e, "Get recent battle history error");
        }
        return result;
    }

    public void insert(UploadBattleResultRequest request) throws Exception {
        try {
            BattleHistory battleHistory = new BattleHistory();
            battleHistory.setUserId(request.getUserId());
            battleHistory.setResult(request.getResult());
            battleHistory.setArenaId(request.getArenaId());
            battleHistory.setScore(request.getBattleScore());
            battleHistory.setTargetUserId(request.getTargetUserId());
            battleHistoryMapper.insertBattleHistory(battleHistory);
        } catch (Exception e) {
            LogContext.instance().error(e, "Insert battle history error");
            throw e;
        }
    }

}
