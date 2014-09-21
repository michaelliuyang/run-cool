package com.rc.app.tools;

import com.rc.app.constants.LoggerNameConstants;
import org.apache.log4j.Logger;

public class LogContext {

    private static final String SEPARATOR = " - ";
    private static final String DEBUG_LEVEL = "debug";
    private static final String INFO_LEVEL = "info";
    private static final String WARN_LEVEL = "warn";
    private static final String ERROR_LEVEL = "error";
    private static final String FATAL_LEVEL = "fatal";
    private static final String TRACE_LEVEL = "trace";

    private static final ThreadLocal<LogContext> LOCAL = new ThreadLocal<LogContext>();

    private String loggerName;
    private String requestTypeIndex;
    private String userId;
    private String requestUUID;

    private LogContext() {
    }

    /**
     * 获取LogContext对象实例;如果在线程绑定变量中没有，则返回一个新的对象，同时 设置想成绑定变量。
     *
     * @return LogContext 对象引用
     */
    public static LogContext instance() {
        LogContext context = LOCAL.get();
        if (context == null) {
            context = new LogContext();
            LOCAL.set(context);
        }
        return context;
    }

    public void clear() {
        loggerName = null;
        requestTypeIndex = null;
        userId = null;
        requestUUID = null;
    }

    public void debug(Object... messages) {
        writeLog(DEBUG_LEVEL, null, messages);
    }

    public void debug(Throwable t, Object... messages) {
        writeLog(DEBUG_LEVEL, t, messages);
    }

    public void info(Object... messages) {
        writeLog(INFO_LEVEL, null, messages);
    }

    public void info(Throwable t, Object... messages) {
        writeLog(INFO_LEVEL, t, messages);
    }

    public void warn(Object... messages) {
        writeLog(WARN_LEVEL, null, messages);
    }

    public void warn(Throwable t, Object... messages) {
        writeLog(WARN_LEVEL, t, messages);
    }

    public void error(Object... messages) {
        writeLog(ERROR_LEVEL, null, messages);
    }

    public void error(Throwable t, Object... messages) {
        writeLog(ERROR_LEVEL, t, messages);
    }

    public void fatal(Object... messages) {
        writeLog(FATAL_LEVEL, null, messages);
    }

    public void fatal(Throwable t, Object... messages) {
        writeLog(FATAL_LEVEL, t, messages);
    }

    public void trace(Object... messages) {
        writeLog(TRACE_LEVEL, null, messages);
    }

    public void trace(Throwable t, Object... messages) {
        writeLog(TRACE_LEVEL, t, messages);
    }

    private void writeLog(String level, Throwable t, Object... messages) {
        Logger logger = getLogger();
        if (DEBUG_LEVEL.equals(level)) {
            if (t == null)
                logger.debug(formatMessage(messages));
            else
                logger.debug(formatMessage(messages), t);
        } else if (INFO_LEVEL.equals(level)) {
            if (t == null)
                logger.info(formatMessage(messages));
            else
                logger.info(formatMessage(messages), t);
        } else if (WARN_LEVEL.equals(level)) {
            if (t == null)
                logger.warn(formatMessage(messages));
            else
                logger.warn(formatMessage(messages), t);
        } else if (ERROR_LEVEL.equals(level)) {
            if (t == null)
                logger.error(formatMessage(messages));
            else
                logger.error(formatMessage(messages), t);
        } else if (FATAL_LEVEL.equals(level)) {
            if (t == null)
                logger.fatal(formatMessage(messages));
            else
                logger.fatal(formatMessage(messages), t);
        } else if (TRACE_LEVEL.equals(level)) {
            if (t == null)
                logger.trace(formatMessage(messages));
            else
                logger.trace(formatMessage(messages), t);
        }
    }

    private String formatMessage(Object... messages) {
        StringBuilder ftMessage = new StringBuilder();
        ftMessage.append(requestUUID).append(SEPARATOR).
                append(requestTypeIndex).append(SEPARATOR).append(userId);
        if (messages == null || messages.length < 1)
            return ftMessage.toString();
        int count = 0;
        Exception ex = new Exception();
        StackTraceElement[] elements = ex.getStackTrace();
        ftMessage.append(SEPARATOR).append(elements[3].getFileName()).append(":")
                .append(elements[3].getLineNumber()).append(SEPARATOR);
        for (Object msg : messages) {
            ftMessage.append(msg);
            if (count < messages.length - 1)
                ftMessage.append(SEPARATOR);
            count++;
        }
        return ftMessage.toString();
    }

    private Logger getLogger() {
        Logger logger = Logger.getLogger(loggerName);
        if (logger == null) {
            logger = Logger.getLogger(LoggerNameConstants.DEFAULT_LOGGER);
        }
        return logger;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getRequestTypeIndex() {
        return requestTypeIndex;
    }

    public void setRequestTypeIndex(String requestTypeIndex) {
        this.requestTypeIndex = requestTypeIndex;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRequestUUID() {
        return requestUUID;
    }

    public void setRequestUUID(String requestUUID) {
        this.requestUUID = requestUUID;
    }

}
