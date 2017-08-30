package com.xulog.alipay.bean.request.biz

import com.xulog.alipay.MethodName
import com.xulog.alipay.bean.request.BizReq
import com.xulog.alipay.bean.response.biz.PayResp
import com.xulog.alipay.util.MsicUtil.toYuan
import java.util.concurrent.TimeUnit


/**
 * 统一收单交易支付接口
 * https://docs.open.alipay.com/api_1/alipay.trade.pay
 */
@MethodName("alipay.trade.pay")
data class Pay(val out_trade_no: String,
               val total_amount: String,
               val auth_code: String,
               val subject: String) : BizReq<PayResp>() {
    val scene: String = "bar_code"
    var body: String? = null
    var timeout_express: String? = null

    constructor(out_trade_no: String, total_amount: Int, auth_code: String, subject: String)
            : this(out_trade_no, total_amount.toYuan(), auth_code, subject)

    fun body(body: String): Pay {
        this.body = body
        return this
    }

    fun timeOutInHour(i: Int, unit: TimeUnit): Pay {
        this.timeout_express = "${i}h"
        return this
    }

    fun timeOutInDay(i: Int, unit: TimeUnit): Pay {
        this.timeout_express = "${i}d"
        return this
    }

    fun timeOutToday(i: Int, unit: TimeUnit): Pay {
        this.timeout_express = "1c"
        return this
    }
}
