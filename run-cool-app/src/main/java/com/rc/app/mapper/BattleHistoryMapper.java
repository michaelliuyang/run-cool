package com.rc.app.mapper;

import com.rc.app.model.BattleHistory;

import java.util.List;

/**
 * 对战记录mapper
 * Created by michael on 14-9-18.
 */
public interface BattleHistoryMapper {

    List<BattleHistory> findRecentByUserId(String userId);

    void insertBattleHistory(BattleHistory battleHistory);

}
