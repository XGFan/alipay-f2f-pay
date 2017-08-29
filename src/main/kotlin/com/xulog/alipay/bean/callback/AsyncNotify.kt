package com.xulog.alipay.bean.callback

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class AsyncNotify {
    //通知时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    lateinit var notify_time: Date
    //通知类型
    lateinit var notify_type: String
    //通知校验ID
    lateinit var notify_id: String
    //签名类型
    lateinit var sign_type: String
    //sign
    lateinit var sign: String
    //支付宝交易号
    lateinit var trade_no: String
    //app_id
    lateinit var app_id: String
    //商户订单号
    lateinit var out_trade_no: String


    var buyer_id: String? = null
    var buyer_logon_id: String? = null

    var seller_id: String? = null
    var seller_email: String? = null

    var trade_status: String? = null

    var total_amount: String? = null
    var receipt_amount: String? = null
    var invoice_amount: String? = null
    var buyer_pay_amount: String? = null
    var point_amount: String? = null

    var subject: String? = null
    var body: String? = null

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var gmt_create: Date? = null
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var gmt_payment: Date? = null

    var raw:Map<String,String> = emptyMap()

    @JsonIgnore
    var verify:Boolean = false
}