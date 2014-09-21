package com.rc.app.aop;

import com.rc.app.constants.LoggerNameConstants;
import com.rc.app.tools.LogContext;
import com.rc.app.tools.NormalLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

/**
 * Created by michael on 2014/9/18.
 */
public class TimerCalculator {

    public Object calculateTime(ProceedingJoinPoint pjp) throws Throwable {
        Object object = null;
        String methodName = null;
        long startTime = System.currentTimeMillis();
        try {
            Signature signature = pjp.getSignature();
            if (signature != null) {
                methodName = signature.getName();
                object = pjp.proceed();
            }
        } catch (Throwable e) {
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            long runningTime = endTime - startTime;
            LogContext logContext = LogContext.instance();
            NormalLogger.log(LoggerNameConstants.TIME_CALCULATOR_LOGGER, logContext.getRequestTypeIndex(),
                    logContext.getUserId(), methodName, runningTime + "");
        }
        return object;
    }

}
