package com.rc.app.service;

import com.rc.app.mapper.UserMapper;
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

    public List<RankingVO> getScoreRankingList(String userId) {
        if (StringUtils.isBlank(userId))
            return new ArrayList<RankingVO>();
        return userMapper.getScoreRanking(userId);
    }

    public List<RankingVO> getBattleScoreRankingList(String userId) {
        if (StringUtils.isBlank(userId))
            return new ArrayList<RankingVO>();
        return userMapper.getBattleScoreRanking(userId);
    }

}
