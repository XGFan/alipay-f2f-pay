package com.xulog.alipay

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.xulog.alipay.bean.callback.AlipayNotify
import com.xulog.alipay.bean.misc.SignType
import com.xulog.alipay.bean.request.AlipayRequest
import com.xulog.alipay.bean.request.BizReq
import com.xulog.alipay.bean.response.AlipayResponse
import com.xulog.alipay.bean.response.BizRes
import com.xulog.alipay.bean.response.biz.*
import com.xulog.alipay.util.MsicUtil
import com.xulog.alipay.util.PojoUtils
import net.dongliu.requests.RawResponse
import net.dongliu.requests.Requests
import java.io.IOException
import java.util.*


/**
 * Created by guofan on 2017/3/29.
 */
@Suppress("ConvertSecondaryConstructorToPrimary")
class AlipayF2fPay {

    val config: AliConfig
    var objectMapper: ObjectMapper

    /**
     * 用于单个支付宝渠道
     */
    constructor(config: AliConfig) {
        objectMapper = ObjectMapper()
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"))
        this.objectMapper = objectMapper
        this.config = config
    }

    constructor(appId: String, signType: SignType, privateKey: String, publicKey: String, notifyUrl: String) :
            this(AliConfig(appId, signType, privateKey, publicKey, notifyUrl))

    constructor(notifyUrl: String) : this(SandBox.initNotifyUrl(notifyUrl))

    /**
     * 发起请求
     */
    @Throws(SDKException::class)
    fun <T : BizRes> execute(bizContent: BizReq<T>): AlipayResponse<T> {
        //组装
        val request = AlipayRequest(config, bizContent)
        //签名
        try {
            request.sign(config.privateKey, config.signType)
        } catch (e: Exception) {
            throw SDKException(SDKException.REASON.SIGN_EXCEPTION, e)
        }
        val form: Map<String, String>
        try {
            form = objectMapper.convertValue<Map<String, String>>(request, object : TypeReference<Map<String, String>>() {})
        } catch (e: Exception) {
            throw SDKException(SDKException.REASON.SERIALIZATION_EXCEPTION, e)
        }
        val raw: RawResponse
        try {
            val req = Requests.post(config.gateWay)
                    .body()
                    .body(form)
            raw = req.send()
        } catch (e: Exception) {
            throw SDKException(SDKException.REASON.NETWORK_EXCEPTION, e)
        }
        val res = raw.readToText()
        //解析
        try {
            return objectMapper.readValue<AlipayResponse<T>>(res, object : TypeReference<AlipayResponse<T>>() {})
        } catch (e: IOException) {
            throw SDKException(SDKException.REASON.DESERIALIZATION_EXCEPTION, res, e)
        }

    }

    /**
     * 回调
     */
    fun callBack(map: Map<String, Any>): AlipayNotify {
        val alipayNotify: AlipayNotify
        try {
            alipayNotify = objectMapper.convertValue<AlipayNotify>(map, AlipayNotify::class.java)
        } catch (e: IOException) {
            throw SDKException(SDKException.REASON.DESERIALIZATION_EXCEPTION, e)
        }

        val verify: Boolean
        try {
            val sortQuery = PojoUtils.transToSortedQueryStr(alipayNotify, "sign", "sign_type")
            verify = MsicUtil.verify(sortQuery, config.publicKey, alipayNotify.sign, alipayNotify.sign_type)
        } catch (e: Exception) {
            throw SDKException(SDKException.REASON.SIGN_EXCEPTION, e)
        }

        alipayNotify.isVerify = verify
        return alipayNotify
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

        val RESPCLASSES: Map<String, Class<*>> = mapOf(
                "alipay_trade_cancel_response" to CancelResp::class.java,
                "alipay_trade_pay_response" to PayResp::class.java,
                "alipay_trade_precreate_response" to PreCreateResp::class.java,
                "alipay_trade_query_response" to QueryResp::class.java,
                "alipay_trade_refund_response" to RefundResp::class.java
        )
        var objectMapper: ObjectMapper

        init {
            objectMapper = ObjectMapper()
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
            objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"))
        }

    }
}

