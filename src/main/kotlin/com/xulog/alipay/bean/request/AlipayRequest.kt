package com.xulog.alipay.bean.request

import com.xulog.alipay.AliConfig
import com.xulog.alipay.annotation.RequestConfig
import com.xulog.alipay.bean.misc.SignType
import com.xulog.alipay.bean.response.AliBizResp
import com.xulog.alipay.util.PojoUtils
import com.xulog.alipay.util.Util
import java.text.SimpleDateFormat
import java.util.*

class AlipayRequest<T : AliBizResp>(val app_id: String, val notify_url: String) {

    /*common start*/
    lateinit var method: String

    val format = "json"

    val charset = "UTF-8"

    //sign 和 sign_type¬
    lateinit var sign_type: SignType
    lateinit var sign: String


    val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()) //发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"

    val version = "1.0"

    var biz_content: AliBizContent<T>? = null


    constructor(config: AliConfig, biz: AliBizContent<T>) : this(config.appId, config.notifyUrl) {
        this.biz_content = biz
        this.method = biz.javaClass.getAnnotation(RequestConfig::class.java).value
    }


    fun sign(privateKey: String, type: SignType) {
        this.sign_type = type
        val sortQuery = PojoUtils.transToSortedQueryStr(this, "sign")
        val sign = Util.sign(type, sortQuery, privateKey)
        this.sign = sign
    }
}
