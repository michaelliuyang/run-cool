package com.rc.test.biz;

import com.rc.test.core.TestRunner;
import org.junit.Before;

import java.io.InputStream;
import java.util.Properties;

public class BaseTest {

    protected String url;
    protected String testDir;
    protected String version;
    protected TestRunner runner;

    public final static String KEY_TEST_URL = "test.url";
    public final static String KEY_TEST_DIR = "test.dir";
    public final static String KEY_TEST_VERSION = "test.version";

    public void getConfig() throws Exception {
        InputStream in = BaseTest.class.getClassLoader().getResourceAsStream("config.properties");
        Properties props = new Properties();
        props.load(in);
        testDir = props.getProperty(KEY_TEST_DIR);
        url = props.getProperty(KEY_TEST_URL);
        version = props.getProperty(KEY_TEST_VERSION);
        in.close();
    }

    @Before
    public void init() throws Exception {
        getConfig();
        runner = new TestRunner(url, testDir, version);
    }

    public class ProtocolFileName {
        public final static String REQUEST_GET_ARENA = "getArena.json";
        public final static String REQUEST_GET_BATTLE_TARGET = "getBattleTarget.json";
        public final static String REQUEST_UPLOAD = "upload.json";
        public final static String REQUEST_RANKING = "ranking.json";
    }
}
