package com.rc.app.response;

import com.rc.app.constants.RequestType;
import com.rc.app.vo.ArenaVO;
import com.rc.app.vo.BattleHistoryVO;
import com.rc.app.vo.PropVO;

import java.util.List;

/**
 * 获取竞技场回复
 * Created by michael on 14-9-20.
 */
public class GetArenaResponse extends BaseResponse {

    private String allow;
    private int joinedCount;
    private List<PropVO> props;
    private List<ArenaVO> arenas;
    private List<BattleHistoryVO> battleHistorys;

    public GetArenaResponse(String protocol, String userId) {
        super(protocol, userId);
    }

    @Override
    protected RequestType requestType() {
        return RequestType.GET_ARENA;
    }

    public String getAllow() {
        return allow;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }

    public int getJoinedCount() {
        return joinedCount;
    }

    public void setJoinedCount(int joinedCount) {
        this.joinedCount = joinedCount;
    }

    public List<PropVO> getProps() {
        return props;
    }

    public void setProps(List<PropVO> props) {
        this.props = props;
    }

    public List<ArenaVO> getArenas() {
        return arenas;
    }

    public void setArenas(List<ArenaVO> arenas) {
        this.arenas = arenas;
    }

    public List<BattleHistoryVO> getBattleHistorys() {
        return battleHistorys;
    }

    public void setBattleHistorys(List<BattleHistoryVO> battleHistorys) {
        this.battleHistorys = battleHistorys;
    }
}
