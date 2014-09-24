package com.rc.test.biz;

import org.junit.Test;

public class TestRanking extends BaseTest {

    @Test
    public void test() {
        try {
            runner.execute(ProtocolFileName.REQUEST_RANKING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
