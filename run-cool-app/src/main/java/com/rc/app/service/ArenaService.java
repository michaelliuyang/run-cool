package com.rc.app.service;

import com.rc.app.constants.PropConfig;
import com.rc.app.mapper.ArenaMapper;
import com.rc.app.model.Arena;
import com.rc.app.model.Prop;
import com.rc.app.tools.LogContext;
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

    public List<Arena> findAll() {
        List<Arena> arenas = new ArrayList<Arena>();
        try {
            arenas = arenaMapper.findAll();
        } catch (Exception e) {
            LogContext.instance().error(e, "获取竞技场集合失败");
        }
        return arenas;
    }

    public List<Prop> getPropList() {
        PropConfig[] propConfigs = PropConfig.values();
        List<Prop> propList = new ArrayList<Prop>(propConfigs.length);
        for (PropConfig pc : propConfigs) {
            Prop prop = new Prop(pc.getName(), pc.getLimit());
            propList.add(prop);
        }
        return propList;
    }

}
