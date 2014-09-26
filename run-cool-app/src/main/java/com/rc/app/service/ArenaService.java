package com.rc.app.service;

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

    /**
     * 获取竞技场集合
     *
     * @return 竞技场集合
     */
    public List<Arena> getArenaList() {
        List<Arena> arenas = new ArrayList<Arena>();
        try {
            LogContext.instance().debug("Get arenas");
            arenas = arenaMapper.findAll();
        } catch (Exception e) {
            LogContext.instance().error(e, "获取竞技场集合失败");
        }
        return arenas;
    }

    /**
     * 通过ID获取竞技场对象
     *
     * @param id 竞技场ID
     * @return 竞技场对象
     */
    public Arena findById(long id) {
        return arenaMapper.findById(id);
    }

    /**
     * 获取竞技场道具集合
     *
     * @return 竞技场道具集合
     */
    public List<PropVO> getPropList() {
        LogContext.instance().debug("Get prop list");
        PropConfig[] propConfigs = PropConfig.values();
        List<PropVO> propVOList = new ArrayList<PropVO>(propConfigs.length);
        for (PropConfig pc : propConfigs) {
            PropVO propVO = new PropVO(pc.getName(), pc.getLimit());
            propVOList.add(propVO);
        }
        return propVOList;
    }

}
