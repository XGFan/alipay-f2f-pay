package com.xulog.alipay

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.smzdm.upay.sdk.common.annotation.RespKey
import com.xulog.alipay.annotation.RequestConfig
import com.xulog.alipay.bean.callback.AlipayNotify
import com.xulog.alipay.bean.request.AliBizContent
import com.xulog.alipay.bean.request.AlipayRequest
import com.xulog.alipay.bean.response.AliBizResp
import com.xulog.alipay.bean.response.AlipayResponse
import com.xulog.alipay.util.PojoUtils
import net.dongliu.requests.Requests
import net.dongliu.requests.body.RequestBody
import org.reflections.Reflections
import java.io.IOException
import java.util.*


/**
 * Created by guofan on 2017/3/29.
 */
class AlipayF2fPay {

    val config: AliConfig
    var objectMapper = ObjectMapper()

    /**
     * 用于单个支付宝渠道
     */
    constructor(config: AliConfig) {
        objectMapper = ObjectMapper()
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"))
        this.objectMapper = objectMapper
        this.config = config
    }

    /**
     * 用于多个支付宝渠道存在
     */
    constructor(objectMapper: ObjectMapper, config: AliConfig) : this(config) {
        this.objectMapper = objectMapper
    }


    /**
     * 发起请求
     */
    fun <T : AliBizResp> execute(bizContent: AliBizContent<T>): AlipayResponse<T>? {
        //组装
        val request = AlipayRequest(config, bizContent)
        //获取请求配置
        val requestConfig = bizContent.javaClass.getAnnotation(RequestConfig::class.java)
        //签名
        request.sign(config.privateKey, config.signType)
        val requestMap = PojoUtils.toForm(request)

        val req = Requests.post(config.gateWay)
                .body(RequestBody.form(requestMap.entries))
        val raw = req.send()
        val res = raw.readToText()
        //解析
        try {
            return objectMapper.readValue<AlipayResponse<T>?>(res, object : TypeReference<AlipayResponse<T>>() {})
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

    }

    /**
     * 回调
     */
    fun callBack(map: Map<String, Any>): AlipayNotify {
        return objectMapper.convertValue<AlipayNotify>(map, AlipayNotify::class.java)
    }

    /**
     * 验证来源
     */
    fun verifySource(notifyId: String): Boolean {
        val queryPara = HashMap<String, String>()
        queryPara.put("service", "notify_verify")
        queryPara.put("partner", config.appId)
        queryPara.put("notify_id", notifyId)
        val s = Requests.get("https://mapi.alipay.com/gateway.do").params(queryPara).send().readToText()
        return "true" == s
    }

    companion object {

        val RESPCLASSES: Map<String, Class<*>>
        var objectMapper: ObjectMapper

        init {
            objectMapper = ObjectMapper()
            objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"))

            val reflections = Reflections("com.xulog.alipay.bean.response.biz")
            val typesAnnotatedWith = reflections.getTypesAnnotatedWith(RespKey::class.java)
            RESPCLASSES = typesAnnotatedWith.map {
                it.getAnnotation(RespKey::class.java).value to it
            }.toMap()
        }

    }
}

