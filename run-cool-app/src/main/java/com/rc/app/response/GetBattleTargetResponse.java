package com.rc.app.response;

import com.rc.app.constants.RequestType;
import com.rc.app.model.User;

/**
 * 获取对战玩家信息回复
 * Created by michael on 14-9-20.
 */
public class GetBattleTargetResponse extends BaseResponse {

    private User battleTargetUser;

    public GetBattleTargetResponse(String protocol, String userId) {
        super(protocol, userId);
    }

    @Override
    protected RequestType requestType() {
        return RequestType.GET_BATTLE_TARGET;
    }

    public User getBattleTargetUser() {
        return battleTargetUser;
    }

    public void setBattleTargetUser(User battleTargetUser) {
        this.battleTargetUser = battleTargetUser;
    }
}
