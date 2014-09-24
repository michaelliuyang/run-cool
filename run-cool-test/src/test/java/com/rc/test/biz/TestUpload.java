package com.rc.test.biz;

import org.junit.Test;

public class TestUpload extends BaseTest {

    @Test
    public void test() {
        try {
            runner.execute(ProtocolFileName.REQUEST_UPLOAD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
