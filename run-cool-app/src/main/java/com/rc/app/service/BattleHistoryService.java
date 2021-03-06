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

    /**
     * 获取某用户的最近5场的对战历史记录集合
     *
     * @param userId 用户ID
     * @return 对战记录集合
     */
    public List<BattleHistoryVO> getRecentBattleHistoryList(String userId) {
        LogContext.instance().debug("Get recent battle history list");
        List<BattleHistoryVO> result = new ArrayList<BattleHistoryVO>();
        try {
            result = battleHistoryMapper.findRecentByUserId(userId);
        } catch (Exception e) {
            LogContext.instance().error(e, "Get recent battle history error");
        }
        return result;
    }

    /**
     * 加入对战记录
     *
     * @param request 上传对战结果请求对象
     * @throws Exception
     */
    public void insert(UploadBattleResultRequest request) throws Exception {
        LogContext.instance().debug("Insert battle history");
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
