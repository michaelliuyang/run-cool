package com.rc.app.mapper;

import com.rc.app.model.BattleHistory;
import com.rc.app.vo.BattleHistoryVO;

import java.util.List;

/**
 * 对战记录mapper
 * Created by michael on 14-9-18.
 */
public interface BattleHistoryMapper {

    List<BattleHistoryVO> findRecentByUserId(String userId);

    void insertBattleHistory(BattleHistory battleHistory);

}
