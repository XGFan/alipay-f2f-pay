package com.xulog.alipay

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.xulog.alipay.Util.signWithRSA2
import com.xulog.alipay.bean.SignType
import com.xulog.alipay.bean.callback.AsyncNotify
import com.xulog.alipay.bean.request.AlipayBizContent
import com.xulog.alipay.bean.response.AlipayResponse
import com.xulog.alipay.bean.response.CommonResponse
import net.dongliu.requests.Requests
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by guofan on 2017/3/29.
 */
class AlipayF2fPay(val appId: String = SandBox.APPID,
                   val privateKey: String = SandBox.PRIVATEKEY,
                   val alipayPublicKey: String = SandBox.ALIPAYPUBLICKEY,
                   val apiGateway: String = SandBox.APIGATEWAY) {


    constructor(appId: String,privateKey: String,alipayPublicKey: String):this(appId, privateKey, alipayPublicKey, ProductApiGateway)

    val signType: SignType = SignType.RSA2

    var format: String = "json"

    var charset: String = "UTF-8"

    var notify_url: String? = null


    inner class Request<T : CommonResponse>(val biz_content: AlipayBizContent<T>) {
        /*common start*/
        var app_id: String = appId //支付宝分配给开发者的应用ID

        var notify_url: String? = this@AlipayF2fPay.notify_url

        val method = biz_content.method

        val format = this@AlipayF2fPay.format
        val charset = this@AlipayF2fPay.charset
        var sign_type: SignType = this@AlipayF2fPay.signType

        var timestamp: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()) //发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"

        val version = "1.0"
        /*common end*/

        // biz_content: String //请求参数的集合，最大长度不限，
        // 除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档

        fun toSortQuery(): String {
            return this::class.java.declaredFields.map {
                it.isAccessible = true
                val value = it.get(this)
                if (value == null || it.isSynthetic) {
                    null
                } else {
                    it.name to value
                }
            }.filterNotNull().map { "${it.first}=${it.second}" }.sortedBy { it }.joinToString("&")
        }


        fun toFormMap(): Map<String, String> {
            return this::class.java.declaredFields.map {
                it.isAccessible = true
                val value = it.get(this)
                if (value == null || it.isSynthetic) {
                    null
                } else {
                    it.name to value.toString()
                }
            }.filterNotNull().toMap()
        }

    }

    fun <T : CommonResponse> execute(biz_content: AlipayBizContent<T>): AlipayResponse<T> {
        val request = Request(biz_content)
        val sign = signWithRSA2(request.toSortQuery(), charset(charset), privateKey.toByteArray())
        val req = Requests.post(apiGateway).forms(request.toFormMap() + mapOf("sign" to sign))
        val res = req.send().readToText(charset(charset))
        return ObjectMapper().registerKotlinModule().readValue(res, object : TypeReference<AlipayResponse<T>>() {})
    }


    fun verifyCallbackWithRSA2(map: Map<String, String>): AsyncNotify {
        val sortQuery = map.filterKeys { it != "sign" && it != "sign_type" }
                .map { "${it.key}=${it.value}" }
                .sortedBy { it }.joinToString("&")
        val cs = charset(charset)
        val status = Util.verifyWithRSA2(sortQuery, cs, alipayPublicKey.toByteArray(cs), map["sign"]!!)
        try {
            val notify = ObjectMapper().findAndRegisterModules().convertValue<AsyncNotify>(map, AsyncNotify::class.java)
            notify.raw = map
            notify.verify = status
            return notify
        }catch (e:Exception){
            throw  RuntimeException()
        }
    }

    companion object {
        const val ProductApiGateway: String = "https://openapi.alipay.com/gateway.do"
    }

}

