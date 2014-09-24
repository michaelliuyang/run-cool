package com.rc.app.service;

import com.rc.app.mapper.UserMapper;
import com.rc.app.tools.LogContext;
import com.rc.app.vo.RankingVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 排名业务类
 * Created by michael on 14-9-23.
 */
@Service
public class RankingService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户积分排名集合
     *
     * @param userId 用户ID
     * @return 排名集合
     */
    public List<RankingVO> getScoreRankingList(String userId) {
        LogContext.instance().debug("Get score ranking");
        if (StringUtils.isBlank(userId)) {
            LogContext.instance().warn("User id is empty,can not get score ranking list");
            return new ArrayList<RankingVO>();
        }
        return userMapper.getScoreRanking(userId);
    }

    /**
     * 获取用户得分排名集合
     *
     * @param userId 用户ID
     * @return 排名集合
     */
    public List<RankingVO> getBattleScoreRankingList(String userId) {
        LogContext.instance().debug("Get battle score ranking");
        if (StringUtils.isBlank(userId)) {
            LogContext.instance().warn("User id is empty,can not get battle score ranking list");
            return new ArrayList<RankingVO>();
        }
        return userMapper.getBattleScoreRanking(userId);
    }

}
