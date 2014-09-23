package com.rc.app.vo;

import com.rc.app.model.User;

/**
 * 用户VO
 * Created by michael on 14-9-18.
 */
public class UserVO extends BaseVO<User> {

    private String userId;
    private String mobilePhone;
    private String nickName;
    private String roleName;
    private String roleRank;
    private String petName;
    private String mountsName;
    private String mountsRank;
    private Integer score;
    private Integer maxBattleScore;

    public UserVO() {
    }

    public UserVO(User model) {
        super(model);
    }

    @Override
    protected void parseFrom(User model) {
        setUserId(model.getUserId());
        setMobilePhone(model.getMobilePhone());
        setNickName(model.getNickName());
        setRoleName(model.getRoleName());
        setRoleRank(model.getRoleRank());
        setPetName(model.getPetName());
        setMountsName(model.getMountsName());
        setMountsRank(model.getMountsRank());
        setScore(model.getScore());
        setMaxBattleScore(model.getMaxBattleScore());
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

}
