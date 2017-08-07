package com.xulog.alipay.bean.request.biz

import com.xulog.alipay.annotation.RequestConfig
import com.xulog.alipay.bean.request.AliBizContent
import com.xulog.alipay.bean.response.biz.PayResp

/**
 * 统一收单交易支付接口
 * https://docs.open.alipay.com/api_1/alipay.trade.pay
 */
@RequestConfig("alipay.trade.pay")
data class Pay(val out_trade_no: String, val auth_code: String,
          val subject: String, val total_amount: String) : AliBizContent<PayResp>() {
    var scene: String = "bar_code"
    var body: String? = null
    var timeout_express: String? = null
}
