package com.rc.app.model;

import com.rc.app.constants.ArenaLevel;
import com.rc.app.constants.ArenaType;

/**
 * 竞技场
 * Created by michael on 14-9-18.
 */
public class Arena extends BaseModel {

    /**
     * 类型 分为金币和钻石币
     */
    private ArenaType type = ArenaType.GOLDEN;
    /**
     * 等级 分为1、2、3
     */
    private ArenaLevel level = ArenaLevel.ONE;

    /**
     * 消耗的金钱数量
     */
    private Integer consumeMoney = 0;
    /**
     * 奖励的金钱数量
     */
    private Integer rewardMoney = 0;
    /**
     * 奖励的积分
     */
    private Integer rewardScore = 0;
    /**
     * 连胜的加成百分比
     */
    private Double continueWinAddPercent;

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
