package com.rc.app.service;

import com.rc.app.constants.RequestType;
import com.rc.app.mapper.UserMapper;
import com.rc.app.model.User;
import com.rc.app.request.BaseRequest;
import com.rc.app.request.UploadBattleResultRequest;
import com.rc.app.tools.LogContext;
import com.rc.app.tools.NumberUtil;
import com.rc.app.vo.MountsVO;
import com.rc.app.vo.PetVO;
import com.rc.app.vo.RoleVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户业务类
 * Created by michael on 14-9-20.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 更加user id获取用户
     *
     * @param userId 用户ID
     * @return 用户对象
     * @throws Exception
     */
    public User findByUserId(String userId) throws Exception {
        User user = null;
        try {
            user = userMapper.findByUserId(userId);
        } catch (Exception e) {
            LogContext.instance().error(e, "Find user by user id error");
            throw e;
        }
        return user;
    }

    /**
     * 检查用户
     * 如果发现DB没有,创建
     * 如果发现有读取出来,并更新基本信息
     *
     * @param request 请求对象
     * @return 检查后的用户对象
     * @throws Exception
     */
    public User checkUser(BaseRequest request) throws Exception {
        User resultUser = null;
        if (StringUtils.isBlank(request.getUserId())) {
            LogContext.instance().debug("User id is empty");
            User user = new User();
            user.setNickName(request.getNickName());
            user.setMobilePhone(request.getMobilePhone());
            String hashUserId = user.getUserIdByHash(request.getImei());
            resultUser = findOrSaveUser(request, hashUserId);
        } else {
            LogContext.instance().debug("User id is not empty");
            resultUser = findOrSaveUser(request, request.getUserId());
        }
        return resultUser;
    }

    /**
     * 获取对战对手
     *
     * @param isContinueWin  是否是连胜
     * @param maxBattleScore 用户最高得分
     * @param userId         用户ID
     * @return 对战对手
     */
    public User getTargetUser(boolean isContinueWin, int maxBattleScore, String userId) {
        double leftPercent = isContinueWin ? 0.1d : 0.15d;
        double rightPercent = isContinueWin ? 0.1d : 0.05d;
        double left = (1 - leftPercent) * maxBattleScore;
        double right = (1 + rightPercent) * maxBattleScore;
        List<User> userList = findByScoreAndUserId(NumberUtil.formatNumber(left),
                NumberUtil.formatNumber(right), userId);
        if (userList.isEmpty()) {
            LogContext.instance().info("Get a random user from db");
            return userMapper.findRandom(userId);
        }
        long random = NumberUtil.getRandomNum(0, userList.size() - 1);
        return userList.get((int) random);
    }

    /**
     * 更新用户得分情况信息
     *
     * @param user        用户对象
     * @param rewardScore 奖励积分
     * @param request     上传对战结果请求对象
     * @throws Exception
     */
    public void updateUserScoreInfo(User user, int rewardScore,
                                    UploadBattleResultRequest request) throws Exception {
        try {
            user.updateScore(rewardScore);
            user.updateJoinArenaCount();
            user.setIsContinueWin(request.isContinueWin());
            if (user.getMaxBattleScore() < request.getBattleScore()) {
                LogContext.instance().debug("Max battle update");
                user.setMaxBattleScore(request.getBattleScore());
            }
            userMapper.updateUser(user);
        } catch (Exception e) {
            LogContext.instance().error(e, "Update user score info error");
            throw e;
        }
    }

    private List<User> findByScoreAndUserId(int lowScore, int highScore, String userId) {
        List<User> userList = new ArrayList<User>();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("lowScore", lowScore);
            params.put("highScore", highScore);
            params.put("userId", userId);
            userList = userMapper.findByScoreAndUserId(params);
        } catch (Exception e) {
            LogContext.instance().error(e, "Find user list by score error");
        }
        return userList;
    }

    private User findOrSaveUser(BaseRequest request, String userId) throws Exception {
        User resultUser = null;
        User userFromDB = findByUserId(userId);
        if (userFromDB == null) {
            LogContext.instance().debug("User is not exist and create new user");
            resultUser = insertUser(request);
        } else {
            LogContext.instance().debug("User is exist");
            resultUser = userFromDB;
            if (!RequestType.UPLOAD_BATTLE_RESULT.equals(request.getType())) {
                LogContext.instance().debug("Is not upload battle result request,update user basic info");
                updateUserBasicInfo(request, resultUser);
            }
        }
        return resultUser;
    }

    private void updateUserBasicInfo(BaseRequest request, User user) throws Exception {
        try {
            user.setMobilePhone(request.getMobilePhone());
            user.setNickName(request.getNickName());
            user.setRoleName(request.getRole().getName());
            user.setRoleRank(request.getRole().getRank());
            user.setPetName(request.getPet().getName());
            user.setMountsName(request.getMounts().getName());
            user.setMountsRank(request.getMounts().getRank());
            userMapper.updateUserBasicInfo(user);
        } catch (Exception e) {
            LogContext.instance().error(e, "Update user basic info error");
            throw e;
        }
    }

    private User insertUser(BaseRequest request) throws Exception {
        RoleVO role = request.getRole();
        PetVO pet = request.getPet();
        MountsVO mounts = request.getMounts();
        if (role == null || pet == null || mounts == null) {
            LogContext.instance().error("role or pet or mounts is empty");
            throw new Exception("role or pet or mounts is empty,insert user error");
        }
        User user = new User();
        try {
            user.setMobilePhone(request.getMobilePhone());
            user.setNickName(request.getNickName());
            user.setRoleName(role.getName());
            user.setRoleRank(role.getRank());
            user.setMountsName(mounts.getName());
            user.setMountsRank(mounts.getRank());
            user.setPetName(pet.getName());
            user.generateUserId(request.getImei());
            userMapper.insertUser(user);
        } catch (Exception e) {
            LogContext.instance().error(e, "Insert user error");
            throw e;
        }
        return user;
    }

}
