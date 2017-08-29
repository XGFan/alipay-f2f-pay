package com.xulog.alipay.bean.request.biz

import com.xulog.alipay.MethodName
import com.xulog.alipay.bean.request.AliBizContent
import com.xulog.alipay.bean.response.biz.CancelResp

/**
 * 统一收单交易撤销接口
 * https://docs.open.alipay.com/api_1/alipay.trade.cancel
 */
@MethodName("alipay.trade.cancel")
data class Cancel(var out_trade_no: String? = null, var trade_no: String? = null) : AliBizContent<CancelResp>() {

    companion object {
        fun ofOutTradeNo(id: String): Cancel {
            return Cancel(id)
        }

        fun ofTradeNo(id: String): Cancel {
            val cancel = Cancel()
            cancel.trade_no = id
            return cancel
        }
    }
}