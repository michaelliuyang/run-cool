package com.rc.app.model;

import com.rc.app.constants.BattleResult;
import com.rc.app.constants.Constants;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * 用户
 * Created by michael on 14-9-18.
 */
public class User extends BaseModel {

    private String userId;
    private String mobilePhone;
    private String nickName;
    private String roleName;
    private String roleRank;
    private String petName;
    private String mountsName;
    private String mountsRank;
    private Integer score = 0;
    private Integer maxBattleScore = 0;
    private Integer winCount = 0;
    private Integer loseCount = 0;
    private Integer joinArenaCount = 0;
    private Boolean isContinueWin = false;

    public void generateUserId(String imei) throws Exception {
        setUserId(getUserIdByHash(imei));
    }

    public String getUserIdByHash(String imei) throws Exception {
        if (StringUtils.isBlank(imei)) {
            if (StringUtils.isBlank(this.nickName)
                    || StringUtils.isBlank(this.mobilePhone)) {
                throw new Exception("Nick name or mobile phone is empty and hash user id error");
            }
            return DigestUtils.md5Hex(UUID.randomUUID() + this.nickName + this.mobilePhone);
        } else {
            return DigestUtils.md5Hex(imei);
        }
    }

    public String formatUserBattleInfo() {
        return this.winCount + BattleResult.WIN.getDisplayName()
                + "," + this.loseCount + BattleResult.LOSE;
    }

    public boolean isAllowJoinArena() {
        return this.joinArenaCount < Constants.LIMIT_JOIN_ARENA;
    }

    public void updateJoinArenaCount() {
        if (this.joinArenaCount >= Constants.LIMIT_JOIN_ARENA)
            this.joinArenaCount = 1;
        else
            this.joinArenaCount += 1;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleRank() {
        return roleRank;
    }

    public void setRoleRank(String roleRank) {
        this.roleRank = roleRank;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getMountsName() {
        return mountsName;
    }

    public void setMountsName(String mountsName) {
        this.mountsName = mountsName;
    }

    public String getMountsRank() {
        return mountsRank;
    }

    public void setMountsRank(String mountsRank) {
        this.mountsRank = mountsRank;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getMaxBattleScore() {
        return maxBattleScore;
    }

    public void setMaxBattleScore(Integer maxBattleScore) {
        this.maxBattleScore = maxBattleScore;
    }

    public Integer getWinCount() {
        return winCount;
    }

    public void setWinCount(Integer winCount) {
        this.winCount = winCount;
    }

    public Integer getLoseCount() {
        return loseCount;
    }

    public void setLoseCount(Integer loseCount) {
        this.loseCount = loseCount;
    }

    public Integer getJoinArenaCount() {
        return joinArenaCount;
    }

    public void setJoinArenaCount(Integer joinArenaCount) {
        this.joinArenaCount = joinArenaCount;
    }

    public Boolean getIsContinueWin() {
        return isContinueWin;
    }

    public void setIsContinueWin(Boolean isContinueWin) {
        this.isContinueWin = isContinueWin;
    }
}
