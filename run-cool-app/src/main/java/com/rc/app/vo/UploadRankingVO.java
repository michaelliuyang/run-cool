package com.rc.app.vo;

/**
 * 上传结果回复时用的排名VO
 * Created by michael on 14-9-29.
 */
public class UploadRankingVO {

    private String userId;
    private int ranking;

    public UploadRankingVO() {
    }

    public UploadRankingVO(String userId, int ranking) {
        this.userId = userId;
        this.ranking = ranking;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
