package com.rc.test.core;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProtocolUtil {

    public final static String DEFAULT_TEST_DIR = "/usr/local/rc/test";
    public final static String FILE_SUFFIX = ".resp";

    private String testDir;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTestDir() {
        return testDir;
    }

    public void setTestDir(String testDir) {
        this.testDir = testDir;
    }

    public byte[] getData(String fileName) throws Exception {
        byte[] result = null;
        String fullFileName = getFullFileName(fileName);
        System.out.println("fullFileName:" + fullFileName);
        File file = new File(fullFileName);
        InputStream in = new FileInputStream(file);
        result = IOUtils.toByteArray(in);
        in.close();
        return result;
    }

    public void saveData(String fileName, byte[] bytes) throws Exception {
        String fullFileName = getFullFileName(fileName);
        fullFileName += FILE_SUFFIX;
        File file = new File(fullFileName);
        if (file.exists()) {
            File backupDir = new File(file.getParent(), "backup");
            if (!backupDir.exists())
                backupDir.mkdirs();
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
            File backUpFile = new File(backupDir, file.getName() + "." + timestamp);
            file.renameTo(backUpFile);
        }
        OutputStream out = new FileOutputStream(file);
        IOUtils.write(bytes, out);
        out.flush();
        out.close();
    }

    private String getFullFileName(String fileName) {
        String fullFileName = null;
        if (testDir == null) {
            fullFileName = DEFAULT_TEST_DIR;
        } else {
            fullFileName = this.testDir;
        }
        if (!fullFileName.endsWith(File.separator)) {
            fullFileName += File.separator;
        }
        fullFileName += this.version;
        if (!fullFileName.endsWith(File.separator)) {
            fullFileName += File.separator;
        }
        fullFileName += fileName;
        return fullFileName;
    }

}
