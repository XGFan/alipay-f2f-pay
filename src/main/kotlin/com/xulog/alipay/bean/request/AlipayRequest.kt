package com.xulog.alipay.bean.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.xulog.alipay.AliConfig
import com.xulog.alipay.MethodName
import com.xulog.alipay.bean.misc.SignType
import com.xulog.alipay.bean.response.BizRes
import com.xulog.alipay.util.PojoUtils
import com.xulog.alipay.util.MsicUtil
import java.util.*

class AlipayRequest<T : BizRes>(val app_id: String, val notify_url: String) {

    /*common start*/
    lateinit var method: String

    val format = "json"

    val charset = "UTF-8"

    //sign 和 sign_type¬
    lateinit var sign_type: SignType
    lateinit var sign: String


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val timestamp: Date = Date() //发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"

    val version = "1.0"

    var biz_content: BizReq<T>? = null


    constructor(config: AliConfig, biz: BizReq<T>) : this(config.appId, config.notifyUrl) {
        this.biz_content = biz
        this.method = biz.javaClass.getAnnotation(MethodName::class.java).value
    }


    fun sign(privateKey: String, type: SignType) {
        this.sign_type = type
        val sortQuery = PojoUtils.transToSortedQueryStr(this, "sign")
        val sign = MsicUtil.sign(type, sortQuery, privateKey)
        this.sign = sign
    }
}
