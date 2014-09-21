package com.rc.app.request;

import com.rc.app.constants.LoggerNameConstants;
import org.json.JSONObject;

/**
 * 获取排行榜信息请求
 * Created by michael on 14-9-20.
 */
public class GetRankingListRequest extends BaseRequest {

    @Override
    protected void setOtherField(JSONObject requestJsonObject) {
        //do nothing
    }

    @Override
    protected String requestLoggerName() {
        return LoggerNameConstants.GET_RANKING_LIST_LOGGER;
    }
}
