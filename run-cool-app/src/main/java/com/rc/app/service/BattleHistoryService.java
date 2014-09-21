package com.rc.app.service;

import com.rc.app.mapper.BattleHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 对战历史业务类
 * Created by michael on 14-9-21.
 */
@Service
public class BattleHistoryService {

    @Autowired
    private BattleHistoryMapper battleHistoryMapper;

    public Integer getCountByUserId(String userId) {
        return battleHistoryMapper.getCountByUserId(userId);
    }

}
