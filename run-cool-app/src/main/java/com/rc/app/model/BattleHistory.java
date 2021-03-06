package com.rc.app.model;

import com.rc.app.constants.BattleResult;
import com.rc.app.tools.DateUtil;

/**
 * 对战记录
 * Created by michael on 14-9-18.
 */
public class BattleHistory extends BaseModel {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 对手的ID
     */
    private String targetUserId;
    /**
     * 竞技场ID
     */
    private Long arenaId;
    /**
     * 对战结果 分为WIN LOSE
     */
    private BattleResult result;
    /**
     * 对战的得分
     */
    private Integer score;
    /**
     * 对战的日期字符串
     */
    private String createDate = DateUtil.format(createTime, DateUtil.YYYYMMDD);

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    public Long getArenaId() {
        return arenaId;
    }

    public void setArenaId(Long arenaId) {
        this.arenaId = arenaId;
    }

    public BattleResult getResult() {
        return result;
    }

    public void setResult(BattleResult result) {
        this.result = result;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
