package com.rc.app.service;

import com.rc.app.mapper.UserMapper;
import com.rc.app.model.Mounts;
import com.rc.app.model.Pet;
import com.rc.app.model.Role;
import com.rc.app.model.User;
import com.rc.app.request.BaseRequest;
import com.rc.app.tools.LogContext;
import com.rc.app.tools.NumberUtil;
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

    public User getTargetUser(boolean isContinueWin, int maxBattleScore, String userId) {
        double leftPercent = isContinueWin ? 0.1d : 0.15d;
        double rightPercent = isContinueWin ? 0.1d : 0.05d;
        double left = (1 - leftPercent) * maxBattleScore;
        double right = (1 + rightPercent) * maxBattleScore;
        List<User> userList = findByScoreAndUserId(NumberUtil.formatNumber(left),
                NumberUtil.formatNumber(right), userId);
        if (userList.isEmpty())
            return null;
        long random = NumberUtil.getRandomNum(0, userList.size() - 1);
        return userList.get((int) random);
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
            resultUser = userFromDB;
            updateUserBasicInfo(request, resultUser);
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
        Role role = request.getRole();
        Pet pet = request.getPet();
        Mounts mounts = request.getMounts();
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
