package com.rc.app.mapper;

import com.rc.app.model.User;

import java.util.List;
import java.util.Map;

/**
 * 用户mapper
 * Created by michael on 14-9-18.
 */
public interface UserMapper {

    void insertUser(User user);

    User findByUserId(String userId);

    void updateUserBasicInfo(User user);

    void updateUserScoreInfo(User user);

    List<User> findByScoreAndUserId(Map<String, Object> params);

    User findRandom(String userId);

}
