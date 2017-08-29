package com.xulog.alipay.bean.response;

import com.xulog.alipay.bean.misc.Extra;

import java.security.InvalidParameterException;

public class AliBizResp extends Extra {
    /**
     * 网关返回码
     */
    private String code;
    /**
     * 网关返回码描述
     */
    private String msg;
    /**
     * 业务返回码
     */
    private String sub_code;
    /**
     * 业务返回码描述
     */
    private String sub_msg;

    /**
     * 单纯的是否请求成功
     */
    public boolean success() {
        return this.code.equals("10000") && this.msg.equals("Success");
    }

    /**
     * 检查不过就抛错误
     */
    public void checkStatusEasily() {
        if (!success()) {
            throw new InvalidParameterException(this.code + "|" + this.msg + "|" + this.sub_code + "|" + this.sub_msg);
        }
    }
}
