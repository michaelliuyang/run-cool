package com.rc.app.model;

import com.rc.app.constants.BattleResult;
import com.rc.app.tools.DateUtil;

/**
 * 对战记录
 * Created by michael on 14-9-18.
 */
public class BattleHistory extends BaseModel {

    private Long userId;
    private Long targetUserId;
    private Long arenaId;
    private BattleResult result;
    private Integer score;
    private String createDate = DateUtil.format(createTime, DateUtil.YYYYMMDD);

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
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
