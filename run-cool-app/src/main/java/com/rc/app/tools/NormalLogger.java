package com.rc.app.tools;


import org.apache.log4j.Logger;

public class NormalLogger {

    private static final String SEPARATOR = " - ";

    public static void log(String loggerName, String type, String userId,
                           String methodName, String time) {
        Logger logger = Logger.getLogger(loggerName);
        format(logger, type, userId, methodName, time);
    }

    private static String format(Object... messages) {
        StringBuilder ftMessage = new StringBuilder();
        if (messages == null || messages.length < 1)
            return ftMessage.toString();
        int count = 0;
        for (Object msg : messages) {
            ftMessage.append(msg);
            if (count < messages.length - 1)
                ftMessage.append(SEPARATOR);
            count++;
        }
        return ftMessage.toString();
    }

}
