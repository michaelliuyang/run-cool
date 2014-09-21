package com.rc.app.response;

import com.rc.app.constants.RequestType;
import com.rc.app.model.Ranking;

import java.util.List;

/**
 * 获取排行榜信息回复
 * Created by michael on 14-9-20.
 */
public class GetRankingListResponse extends BaseResponse {

    private List<Ranking> scoreRankingList;
    private List<Ranking> battleRankingList;

    public GetRankingListResponse(String protocol, String userId) {
        super(protocol, userId);
    }

    @Override
    protected RequestType requestType() {
        return null;
    }

    public List<Ranking> getScoreRankingList() {
        return scoreRankingList;
    }

    public void setScoreRankingList(List<Ranking> scoreRankingList) {
        this.scoreRankingList = scoreRankingList;
    }

    public List<Ranking> getBattleRankingList() {
        return battleRankingList;
    }

    public void setBattleRankingList(List<Ranking> battleRankingList) {
        this.battleRankingList = battleRankingList;
    }
}
