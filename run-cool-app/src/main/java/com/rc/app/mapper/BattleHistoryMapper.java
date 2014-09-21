package com.rc.app.mapper;

/**
 * 对战记录mapper
 * Created by michael on 14-9-18.
 */
public interface BattleHistoryMapper {

    Integer getCountByUserId(String userId);

}
