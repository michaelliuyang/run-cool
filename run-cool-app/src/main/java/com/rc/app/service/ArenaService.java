package com.rc.app.service;

import com.rc.app.constants.ArenaLevel;
import com.rc.app.constants.PropConfig;
import com.rc.app.mapper.ArenaMapper;
import com.rc.app.model.Arena;
import com.rc.app.tools.LogContext;
import com.rc.app.vo.PropVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 竞技场业务类
 * Created by michael on 14-9-21.
 */
@Service
public class ArenaService {

    @Autowired
    private ArenaMapper arenaMapper;

    public List<Arena> getArenaList(ArenaLevel level) {
        List<Arena> arenas = new ArrayList<Arena>();
        try {
            if (!ArenaLevel.NOT_JOIN.equals(level))
                arenas = arenaMapper.findByLevel(level);
        } catch (Exception e) {
            LogContext.instance().error(e, "获取竞技场集合失败");
        }
        return arenas;
    }

    public Arena findById(long id) {
        return arenaMapper.findById(id);
    }

    public List<PropVO> getPropList() {
        PropConfig[] propConfigs = PropConfig.values();
        List<PropVO> propVOList = new ArrayList<PropVO>(propConfigs.length);
        for (PropConfig pc : propConfigs) {
            PropVO propVO = new PropVO(pc.getName(), pc.getLimit());
            propVOList.add(propVO);
        }
        return propVOList;
    }

}
