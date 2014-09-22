package com.rc.app.response;

import com.google.gson.JsonObject;
import com.rc.app.constants.RequestType;
import com.rc.app.constants.ResponseReturnCode;
import com.rc.app.tools.JsonUtil;
import com.rc.app.tools.LogContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 回复对象父类
 * Created by michael on 2014/9/18.
 */
public abstract class BaseResponse {

    protected String protocol;
    protected String type;
    protected String userId;
    protected String returnCode = ResponseReturnCode.SUCCESS.getIndex();

    public BaseResponse(String protocol, String userId) {
        this.protocol = protocol;
        this.type = requestType() != null ? requestType().getIndex() : null;
        this.userId = userId;
    }

    public byte[] convert2ByteResult() {
        byte[] result = null;
        try {
            JsonObject response = new JsonObject();
            response.add("response", JsonUtil.bean2JsonTree(this));
            String responseJsonStr = response.toString();
            LogContext.instance().info(JsonUtil.formatJsonStr(responseJsonStr));
            if (StringUtils.isNotEmpty(responseJsonStr)) {
                result = responseJsonStr.getBytes("UTF-8");
            }
        } catch (Exception e) {
            LogContext.instance().error(e, "转化回复报文失败");
        } finally {
            LogContext.instance().info(requestType() + " REQUEST END ...");
        }
        return result;
    }

    protected abstract RequestType requestType();

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

}
