package com.rc.app.mapper;

import com.rc.app.constants.ArenaLevel;
import com.rc.app.model.Arena;

import java.util.List;

/**
 * 竞技场mapper
 * Created by michael on 14-9-18.
 */
public interface ArenaMapper {

    Arena findById(long id);

    List<Arena> findByLevel(ArenaLevel level);

}
