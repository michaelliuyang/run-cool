package com.rc.test.biz;

import org.junit.Test;

public class TestGetBattleTarget extends BaseTest {

    @Test
    public void test() {
        try {
            runner.execute(ProtocolFileName.REQUEST_GET_BATTLE_TARGET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
