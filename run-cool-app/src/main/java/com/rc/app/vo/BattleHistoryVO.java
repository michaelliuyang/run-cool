package com.rc.app.vo;

import com.rc.app.model.BattleHistory;

/**
 * 对战记录VO
 * Created by michael on 14-9-18.
 */
public class BattleHistoryVO extends BaseVO<BattleHistory> {

    private UserVO targetUser;
    private Long arenaId;
    private String result;
    private Integer score;

    public BattleHistoryVO() {
    }

    public BattleHistoryVO(BattleHistory model) {
        super(model);
    }

    @Override
    protected void parseFrom(BattleHistory model) {
        setArenaId(model.getArenaId());
        setResult(model.getResult().getDisplayName());
        setScore(model.getScore());
    }

    public UserVO getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(UserVO targetUser) {
        this.targetUser = targetUser;
    }

    public Long getArenaId() {
        return arenaId;
    }

    public void setArenaId(Long arenaId) {
        this.arenaId = arenaId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
