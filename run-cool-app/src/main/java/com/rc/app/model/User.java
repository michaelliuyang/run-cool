package com.rc.app.model;

import com.rc.app.constants.Constants;
import com.rc.app.tools.DateUtil;
import com.rc.app.tools.LogContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.UUID;

/**
 * 用户
 * Created by michael on 14-9-18.
 */
public class User extends BaseModel {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 手机号
     */
    private String mobilePhone;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色等级
     */
    private String roleRank;
    /**
     * 宠物名称
     */
    private String petName;
    /**
     * 坐骑名称
     */
    private String mountsName;
    /**
     * 坐骑等级
     */
    private String mountsRank;
    /**
     * 积分
     */
    private Integer score = 0;
    /**
     * 最高对战得分
     */
    private Integer maxBattleScore = 0;
    /**
     * 参加的场次数
     */
    private Integer joinArenaCount = 0;
    /**
     * 是否是连胜 0:不是,1:是
     */
    private Boolean isContinueWin = false;

    /**
     * 生成并set
     *
     * @param imei 手机设备号
     * @throws Exception
     */
    public void generateUserId(String imei) throws Exception {
        setUserId(getUserIdByHash(imei));
    }

    /**
     * 产生userId
     *
     * @param imei 手机设备号
     * @return userId
     * @throws Exception
     */
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

    /**
     * 是否可以参加竞技场对战
     *
     * @return 是/否
     */
    public boolean isAllowJoinArena() {
        return (this.joinArenaCount < Constants.LIMIT_JOIN_ARENA) && isAllowDate();
    }

    /**
     * 是否是可以参加竞技场对战的时间(12:00 - 23:59:59)
     *
     * @return 是/否
     */
    public boolean isAllowDate() {
        boolean result;
        try {
            Date nowDate = new Date();
            String nowDateStr = DateUtil.format(nowDate, DateUtil.YYYYMMDD);
            Date begin = DateUtil.df.parse(nowDateStr + " 12:00:00");
            Date end = DateUtil.df.parse(nowDateStr + " 23:59:59");
            result = nowDate.after(begin) && nowDate.before(end);
        } catch (Exception e) {
            result = false;
            LogContext.instance().error(e, "Is allow date error");
        }
        return result;
    }

    /**
     * 更新用户参见对战场次的数量
     */
    public void updateJoinArenaCount() {
        if (this.joinArenaCount >= Constants.LIMIT_JOIN_ARENA)
            this.joinArenaCount = 1;
        else
            this.joinArenaCount += 1;
    }

    public void updateScore(int rewardScore) {
        this.score += rewardScore;
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
