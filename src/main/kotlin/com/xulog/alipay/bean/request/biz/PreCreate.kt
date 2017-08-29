package com.xulog.alipay.bean.request.biz

import com.xulog.alipay.MethodName
import com.xulog.alipay.bean.request.AliBizContent
import com.xulog.alipay.bean.response.biz.PreCreateResp

/**
 * 统一收单线下交易预创建
 * https://docs.open.alipay.com/api_1/alipay.trade.precreate
 */
@MethodName("alipay.trade.precreate")
data class PreCreate(val out_trade_no: String,
                     val total_amount: String,
                     val subject: String) :
        AliBizContent<PreCreateResp>() {
    var timeout_express: String? = null
}