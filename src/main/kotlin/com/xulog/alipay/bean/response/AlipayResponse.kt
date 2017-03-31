package com.xulog.alipay.bean.response

/**
 * Created by guofan on 2017/3/30.
 */
class AlipayResponse {
    lateinit var code: String // 网关返回码
    lateinit var msg: String // 网关返回码描述
    var subCode: String? = null // 业务返回码
    var subMsg: String? = null // 业务返回码描述
    lateinit var sign: String // 签名
}