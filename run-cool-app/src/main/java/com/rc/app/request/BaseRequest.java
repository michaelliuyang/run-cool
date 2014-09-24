package com.rc.app.request;

import com.rc.app.constants.RequestType;
import com.rc.app.tools.DateUtil;
import com.rc.app.tools.JsonUtil;
import com.rc.app.tools.LogContext;
import com.rc.app.tools.NumberUtil;
import com.rc.app.vo.MountsVO;
import com.rc.app.vo.PetVO;
import com.rc.app.vo.RoleVO;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 请求对象父类
 * Created by michael on 2014/9/18.
 */
public abstract class BaseRequest {

    protected static final String JSON_NAME_REQUEST = "request";
    protected static final String JSON_NAME_PROTOCOL = "protocol";
    protected static final String JSON_NAME_REQUEST_TYPE = "type";
    protected static final String JSON_NAME_UID_ID = "uid";
    protected static final String JSON_NAME_MOBILE_PHONE = "mobilePhone";
    protected static final String JSON_NAME_NICK_NAME = "nickName";
    protected static final String JSON_NAME_ROLE = "role";
    protected static final String JSON_NAME_PET = "pet";
    protected static final String JSON_NAME_MOUNTS = "mounts";
    protected static final String JSON_NAME_NAME = "name";
    protected static final String JSON_NAME_RANK = "rank";
    protected static final String JSON_NAME_IMEI = "imei";

    protected String protocol;
    protected RequestType type;
    protected String userId;
    protected String mobilePhone;
    protected String nickName;
    protected RoleVO role;
    protected MountsVO mounts;
    protected PetVO pet;
    protected String imei;

    public void parse(HttpServletRequest request) throws Exception {
        LogContext logContext = LogContext.instance();
        logContext.clear();
        logContext.setRequestUUID(getRequestUUID());
        logContext.setLoggerName(requestLoggerName());
        try {
            byte[] requestData = IOUtils.toByteArray(request.getInputStream());
            if (requestData == null || requestData.length <= 0) {
                logContext.error("requestData为空,转化请求报文失败");
                return;
            }
            String requestString = new String(requestData, "UTF-8");
            if (StringUtils.isBlank(requestString)) {
                logContext.error("requestString为空,转化请求报文失败");
                return;
            }
            JSONObject requestJsonObject = JsonUtil.jsonStr2JsonObject(requestString);
            setBaseField(requestJsonObject);
            setOtherField(requestJsonObject);
            logContext.setUserId(this.userId);
            if (this.type != null)
                logContext.setRequestTypeIndex(this.type.getIndex());
            logContext.info(this.type + " REQUEST START ...");
            logContext.info(JsonUtil.formatJsonStr(requestJsonObject.toString()));
            request.setAttribute("requestProtocol", this.protocol);
            request.setAttribute("requestUserId", this.userId);
        } catch (Exception e) {
            logContext.error(e, "转化请求报文失败");
            throw e;
        }
    }

    protected abstract void setOtherField(JSONObject requestJsonObject);

    protected abstract String requestLoggerName();

    protected JSONObject getRequestJsonObject(JSONObject requestJsonObject) {
        if (!JsonUtil.jsonKeyExist(requestJsonObject, JSON_NAME_REQUEST)) {
            return null;
        }
        return requestJsonObject.getJSONObject(JSON_NAME_REQUEST);
    }

    protected String getJsonString(JSONObject object, String key) {
        if (JsonUtil.jsonKeyExist(object, key)) {
            return object.getString(key);
        }
        LogContext.instance().warn(key, "not exist");
        return "";
    }

    protected JSONObject getJsonObject(JSONObject object, String key) {
        if (JsonUtil.jsonKeyExist(object, key)) {
            return object.getJSONObject(key);
        }
        LogContext.instance().warn(key, "not exist");
        return new JSONObject();
    }

    protected int getJsonInt(JSONObject object, String key) {
        if (JsonUtil.jsonKeyExist(object, key)) {
            return object.getInt(key);
        }
        LogContext.instance().warn(key, "not exist");
        return 0;
    }

    protected long getJsonLong(JSONObject object, String key) {
        if (JsonUtil.jsonKeyExist(object, key)) {
            return object.getLong(key);
        }
        LogContext.instance().warn(key, "not exist");
        return 0;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public RoleVO getRole() {
        return role;
    }

    public void setRole(RoleVO role) {
        this.role = role;
    }

    public MountsVO getMounts() {
        return mounts;
    }

    public void setMounts(MountsVO mounts) {
        this.mounts = mounts;
    }

    public PetVO getPet() {
        return pet;
    }

    public void setPet(PetVO pet) {
        this.pet = pet;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    private void setBaseField(JSONObject requestJsonObject) {
        JSONObject requestContent = getRequestJsonObject(requestJsonObject);
        if (requestContent == null)
            return;
        this.protocol = getJsonString(requestContent, JSON_NAME_PROTOCOL);
        this.type = RequestType.getTypeByIndex(getJsonString(requestContent, JSON_NAME_REQUEST_TYPE));
        this.userId = getJsonString(requestContent, JSON_NAME_UID_ID);
        this.mobilePhone = getJsonString(requestContent, JSON_NAME_MOBILE_PHONE);
        this.nickName = getJsonString(requestContent, JSON_NAME_NICK_NAME);
        this.imei = getJsonString(requestContent, JSON_NAME_IMEI);
        JSONObject roleJsonObject = getJsonObject(requestContent, JSON_NAME_ROLE);
        this.role = new RoleVO(getJsonString(roleJsonObject, JSON_NAME_NAME),
                getJsonString(roleJsonObject, JSON_NAME_RANK));
        JSONObject mountsJsonObject = getJsonObject(requestContent, JSON_NAME_MOUNTS);
        this.mounts = new MountsVO(getJsonString(mountsJsonObject, JSON_NAME_NAME),
                getJsonString(mountsJsonObject, JSON_NAME_RANK));
        JSONObject petJsonObject = getJsonObject(requestContent, JSON_NAME_PET);
        this.pet = new PetVO(getJsonString(petJsonObject, JSON_NAME_NAME));
    }

    private String getRequestUUID() {
        StringBuilder uuidBuilder = new StringBuilder();
        uuidBuilder.append(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSSSSS));
        uuidBuilder.append("_");
        uuidBuilder.append(NumberUtil.getRandomNum(100000, 999999));
        return uuidBuilder.toString();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
