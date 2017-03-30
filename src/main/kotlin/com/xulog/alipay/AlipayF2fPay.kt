package com.xulog.alipay

import com.xulog.alipay.bean.AlipayBizContent
import com.xulog.alipay.bean.SignType
import com.xulog.alipay.bean.request.PreCreate
import net.dongliu.requests.Requests
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by root on 2017/3/29.
 */
class AlipayF2fPay(val appId: String = SandBox.APPID,
                   val privateKey: String = SandBox.PRIVATEKEY,
                   val alipayPublicKey: String = SandBox.ALIPAYPUBLICKEY,
                   val apiGateway: String = SandBox.APIGATEWAY) {

    var signType: SignType = SignType.RSA2

    var format: String = "json"

    var charset: String = "UTF-8"


    inner class Request(val biz_content: AlipayBizContent) {
        /*common start*/
        var app_id: String = appId //支付宝分配给开发者的应用ID

        var notify_url: String? = null

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

    fun execute(biz_content: AlipayBizContent): String {
        val request = Request(biz_content)
        val sign = signWithRSA2(request.toSortQuery())
        val req = Requests.post(apiGateway).forms(request.toFormMap() + mapOf("sign" to sign))
        val res = req.send()
        return res.readToText(charset("UTF-8"))
    }

    private fun getPrivateKeyFromPKCS8(algorithm: String, ins: ByteArray): PrivateKey {
        val keyFactory = KeyFactory.getInstance(algorithm)
        val decode = Base64.getDecoder().decode(ins)
        return keyFactory.generatePrivate(PKCS8EncodedKeySpec(decode))
    }

    private fun signWithRSA2(content: String): String {
        val priKey = getPrivateKeyFromPKCS8("RSA", privateKey.toByteArray())
        val signature = Signature.getInstance("SHA256WithRSA")
        signature.initSign(priKey)
        signature.update(content.toByteArray(charset(charset)))
        val signed = signature.sign()
        return Base64.getEncoder().encode(signed).toString(charset(charset))
    }

    companion object {
        const val ProductApiGateway: String = "https://openapi.alipay.com/gateway.do"

        @JvmStatic
        fun main(args: Array<String>) {
            val f2fPay = AlipayF2fPay()
            f2fPay.execute(PreCreate("id001", "100", "测试"))
//            f2fPay.createQrCode("12345678", "88.88", "Iphone6 16G")
//            f2fPay.execute(Query("id001", null))
//            f2fPay.execute(Refund("mcsa002_139024424412886175039745116474005648749", "2017032821001004390226554245", "0.1", "a"))
        }
    }

}

