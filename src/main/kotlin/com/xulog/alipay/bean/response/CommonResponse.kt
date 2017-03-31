package com.xulog.alipay.bean.response

/**
 * Created by guofan on 2017/3/30.
 */
open class CommonResponse {
    lateinit var code: String // 网关返回码
    lateinit var msg: String // 网关返回码描述
    var sub_code: String? = null // 业务返回码
    var sub_msg: String? = null // 业务返回码描述
}