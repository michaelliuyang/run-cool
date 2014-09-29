package com.rc.app.service;

import com.rc.app.constants.BattleResult;
import com.rc.app.model.Arena;
import com.rc.app.model.User;
import com.rc.app.request.UploadBattleResultRequest;
import com.rc.app.tools.LogContext;
import com.rc.app.vo.UploadRankingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 上传事件服务类
 * Created by michael on 14-9-23.
 */
@Service
public class UploadResultService {

    @Autowired
    private BattleHistoryService battleHistoryService;
    @Autowired
    private ArenaService arenaService;
    @Autowired
    private UserService userService;

    /**
     * 更新对战结果
     *
     * @param request 上传对战结果请求对象
     * @param user    用户对象
     * @throws Exception
     */
    @Transactional
    public void uploadResult(UploadBattleResultRequest request, User user) throws Exception {
        LogContext.instance().debug("Upload result");
        try {
            battleHistoryService.insert(request);
            int rewardScore = 0;
            if (BattleResult.WIN.equals(request.getResult())) {
                LogContext.instance().debug("Result is win,get reward score");
                Arena arena = arenaService.findById(request.getArenaId());
                rewardScore = arena.getRewardScore();
            }
            userService.updateUserScoreInfo(user, rewardScore, request);
        } catch (Exception e) {
            LogContext.instance().error(e, "Upload result error");
            throw e;
        }
    }

    /**
     * 获取用户和对战用户的排名MAP
     *
     * @param userId       用户ID
     * @param targetUserId 对战用户ID
     * @return 排名MAP
     */
    public Map<String, Integer> getRankingMap(String userId, String targetUserId) {
        Map<String, Integer> rankingMap = new HashMap<String, Integer>();
        try {
            List<UploadRankingVO> result = userService.
                    getBattleScoreRankingByUserIds(userId, targetUserId);
            if (result != null && result.size() > 0) {
                for (UploadRankingVO vo : result) {
                    rankingMap.put(vo.getUserId(), vo.getRanking());
                }
            }
        } catch (Exception e) {
            LogContext.instance().error(e, "Get ranking map error");
        }
        return rankingMap;
    }

}
