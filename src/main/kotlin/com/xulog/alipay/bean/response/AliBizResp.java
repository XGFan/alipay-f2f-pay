package com.xulog.alipay.bean.response;

import com.xulog.alipay.bean.misc.Extra;

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
}
