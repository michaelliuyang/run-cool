package com.rc.app.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 父VO
 * Created by michael on 14-9-22.
 */
public abstract class BaseVO<T> {

    public BaseVO(T model) {
        parseFrom(model);
    }

    public BaseVO(){}

    protected abstract void parseFrom(T model);

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

}
