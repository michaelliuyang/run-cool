package com.rc.app.vo;

import com.rc.app.constants.ArenaLevel;
import com.rc.app.constants.ArenaType;
import com.rc.app.model.Arena;

/**
 * 竞技场VO
 * Created by michael on 14-9-18.
 */
public class ArenaVO extends BaseVO<Arena> {

    private Long arenaId;
    private ArenaType type;
    private ArenaLevel level;
    private Integer consumeMoney;
    private Integer rewardMoney;
    private Integer rewardScore;
    private Double continueWinAddPercent;

    public ArenaVO(Arena model) {
        super(model);
    }

    @Override
    protected void parseFrom(Arena model) {
        setArenaId(model.getId());
        setType(model.getType());
        setLevel(model.getLevel());
        setConsumeMoney(model.getConsumeMoney());
        setRewardMoney(model.getRewardMoney());
        setRewardScore(model.getRewardScore());
        setContinueWinAddPercent(model.getContinueWinAddPercent());
    }

    public Long getArenaId() {
        return arenaId;
    }

    public void setArenaId(Long arenaId) {
        this.arenaId = arenaId;
    }

    public ArenaType getType() {
        return type;
    }

    public void setType(ArenaType type) {
        this.type = type;
    }

    public ArenaLevel getLevel() {
        return level;
    }

    public void setLevel(ArenaLevel level) {
        this.level = level;
    }

    public Integer getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(Integer consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public Integer getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(Integer rewardMoney) {
        this.rewardMoney = rewardMoney;
    }

    public Integer getRewardScore() {
        return rewardScore;
    }

    public void setRewardScore(Integer rewardScore) {
        this.rewardScore = rewardScore;
    }

    public Double getContinueWinAddPercent() {
        return continueWinAddPercent;
    }

    public void setContinueWinAddPercent(Double continueWinAddPercent) {
        this.continueWinAddPercent = continueWinAddPercent;
    }
}