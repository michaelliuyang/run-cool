package com.rc.app.service;

import com.rc.app.mapper.UserMapper;
import com.rc.app.model.User;
import com.rc.app.tools.LogContext;
import com.rc.app.vo.RankingVO;
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

    public static final String RANKING_SCORE_TYPE = "scoreRanking";
    public static final String RANKING_BATTLE_SCORE_TYPE = "battleScoreRanking";

    @Autowired
    private UserMapper userMapper;

    public List<RankingVO> getRankingList(User user, String rankType) {
        List<RankingVO> result = new ArrayList<RankingVO>();
        try {
            List<RankingVO> rankingList;
            if (RANKING_SCORE_TYPE.equals(rankType)) {
                rankingList = userMapper.getScoreRanking();
            } else if (RANKING_BATTLE_SCORE_TYPE.equals(rankType)) {
                rankingList = userMapper.getBattleScoreRanking();
            } else {
                return new ArrayList<RankingVO>();
            }
            if (rankingList == null) {
                rankingList = new ArrayList<RankingVO>(1);
                rankingList.add(buildVO(user, 1));
                return rankingList;
            }
            if (rankingList.isEmpty()) {
                rankingList.add(buildVO(user, 1));
                return rankingList;
            }
            if (!isContains(rankingList, user)) {
                updateRankingList(rankingList, result, user);
            }
        } catch (Exception e) {
            LogContext.instance().error(e, "Get ranking list error");
        }
        return result;
    }

    private boolean isContains(List<RankingVO> voList, User user) {
        boolean isContains = false;
        for (RankingVO vo : voList) {
            if (user.getUserId().equals(vo.getUserId())) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }

    private RankingVO buildVO(User user, int rankingNo) {
        RankingVO userRankingVO = new RankingVO();
        userRankingVO.setRanking(rankingNo);
        userRankingVO.setNickName(user.getNickName());
        userRankingVO.setUserId(user.getUserId());
        userRankingVO.setScore(user.getScore());
        return userRankingVO;
    }

    private void updateRankingList(List<RankingVO> voList,
                                   List<RankingVO> newVoList,
                                   User user) {
        int rankingNo = 0;
        boolean isInsert = false;
        for (RankingVO vo : voList) {
            rankingNo++;
            if (user.getScore() > vo.getScore() && !isInsert) {
                newVoList.add(buildVO(user, rankingNo));
                rankingNo++;
                isInsert = true;
            } else if (user.getScore() == vo.getScore() && !isInsert) {
                newVoList.add(buildVO(user, rankingNo));
                isInsert = true;
            }
            vo.setRanking(rankingNo);
            newVoList.add(vo);
        }
        if (!isInsert) {
            newVoList.add(buildVO(user, rankingNo + 1));
        }
    }

}
