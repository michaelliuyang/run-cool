package com.rc.test.biz;

import org.junit.Test;

public class TestGetArena extends BaseTest {

    @Test
    public void test() {
        try {
            runner.execute(ProtocolFileName.REQUEST_GET_ARENA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
