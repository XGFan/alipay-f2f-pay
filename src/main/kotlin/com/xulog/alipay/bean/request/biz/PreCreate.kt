package com.xulog.alipay.bean.request.biz

import com.xulog.alipay.MethodName
import com.xulog.alipay.bean.request.BizReq
import com.xulog.alipay.bean.response.biz.PreCreateResp
import com.xulog.alipay.util.MsicUtil.toYuan
import java.util.concurrent.TimeUnit

/**
 * 统一收单线下交易预创建
 * https://docs.open.alipay.com/api_1/alipay.trade.precreate
 */
@MethodName("alipay.trade.precreate")
data class PreCreate(val out_trade_no: String,
                     val total_amount: String,
                     val subject: String) :
        BizReq<PreCreateResp>() {
    var timeout_express: String? = null

    constructor(out_trade_no: String,
                total_amount: Int,
                subject: String) : this(out_trade_no, total_amount.toYuan(), subject)

    fun timeOutInHour(i: Int, unit: TimeUnit): PreCreate {
        this.timeout_express = "${i}h"
        return this
    }

    fun timeOutInDay(i: Int, unit: TimeUnit): PreCreate {
        this.timeout_express = "${i}d"
        return this
    }

    fun timeOutToday(i: Int, unit: TimeUnit): PreCreate {
        this.timeout_express = "1c"
        return this
    }

}

