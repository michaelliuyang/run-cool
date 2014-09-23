package com.rc.app.response;

import com.rc.app.constants.RequestType;
import com.rc.app.vo.RankingVO;

import java.util.List;

/**
 * 获取排行榜信息回复
 * Created by michael on 14-9-20.
 */
public class GetRankingListResponse extends BaseResponse {

    private List<RankingVO> scoreRankingList;
    private List<RankingVO> battleRankingList;

    public GetRankingListResponse(String protocol, String userId) {
        super(protocol, userId);
    }

    @Override
    protected RequestType requestType() {
        return RequestType.GET_RANKING_LIST;
    }

    public List<RankingVO> getScoreRankingList() {
        return scoreRankingList;
    }

    public void setScoreRankingList(List<RankingVO> scoreRankingList) {
        this.scoreRankingList = scoreRankingList;
    }

    public List<RankingVO> getBattleRankingList() {
        return battleRankingList;
    }

    public void setBattleRankingList(List<RankingVO> battleRankingList) {
        this.battleRankingList = battleRankingList;
    }
}
