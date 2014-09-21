package com.rc.app.response;

import com.rc.app.constants.RequestType;
import com.rc.app.model.Arena;
import com.rc.app.model.Prop;

import java.util.List;

/**
 * 获取竞技场回复
 * Created by michael on 14-9-20.
 */
public class GetArenaResponse extends BaseResponse {

    private String allow;
    private int joinedCount;
    private List<Prop> props;
    private List<Arena> arenas;

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

    public List<Prop> getProps() {
        return props;
    }

    public void setProps(List<Prop> props) {
        this.props = props;
    }

    public List<Arena> getArenas() {
        return arenas;
    }

    public void setArenas(List<Arena> arenas) {
        this.arenas = arenas;
    }
}
