package com.rc.app.vo;

/**
 * 排行榜信息VO
 * Created by michael on 14-9-20.
 */
public class RankingVO {

    private int ranking;
    private String userId;
    private String nickName;
    private RoleVO role;
    private int score;

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public RoleVO getRole() {
        return role;
    }

    public void setRoleVO(RoleVO role) {
        this.role = role;
    }
}
