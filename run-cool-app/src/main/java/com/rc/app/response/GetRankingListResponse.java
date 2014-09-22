package com.rc.app.response;

import com.rc.app.constants.RequestType;
import com.rc.app.vo.RankingVO;

import java.util.List;

/**
 * 获取排行榜信息回复
 * Created by michael on 14-9-20.
 */
public class GetRankingListResponse extends BaseResponse {

    private List<RankingVO> scoreRankingVOList;
    private List<RankingVO> battleRankingVOList;

    public GetRankingListResponse(String protocol, String userId) {
        super(protocol, userId);
    }

    @Override
    protected RequestType requestType() {
        return null;
    }

    public List<RankingVO> getScoreRankingVOList() {
        return scoreRankingVOList;
    }

    public void setScoreRankingVOList(List<RankingVO> scoreRankingVOList) {
        this.scoreRankingVOList = scoreRankingVOList;
    }

    public List<RankingVO> getBattleRankingVOList() {
        return battleRankingVOList;
    }

    public void setBattleRankingVOList(List<RankingVO> battleRankingVOList) {
        this.battleRankingVOList = battleRankingVOList;
    }
}
