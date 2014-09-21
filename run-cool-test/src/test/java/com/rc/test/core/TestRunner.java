package com.rc.test.core;

public class TestRunner {

    private String sUrl;
    private String testDir;
    private String version;

    public TestRunner(String sUrl, String testDir, String version) {
        this.sUrl = sUrl;
        this.testDir = testDir;
        this.version = version;
    }

    public void execute(String fileName, String sUrl) throws Exception {
        ProtocolUtil protocolUtil = new ProtocolUtil();
        protocolUtil.setTestDir(testDir);
        protocolUtil.setVersion(version);
        byte[] bytes = protocolUtil.getData(fileName);
        protocolUtil.saveData(fileName, new HttpUtil().doPost(sUrl, bytes));
    }

    public void execute(String fileName) throws Exception {
        execute(fileName, sUrl);
    }

}
