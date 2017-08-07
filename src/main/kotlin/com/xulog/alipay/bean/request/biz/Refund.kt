package com.xulog.alipay.bean.request.biz

import com.xulog.alipay.annotation.RequestConfig
import com.xulog.alipay.bean.request.AliBizContent
import com.xulog.alipay.bean.response.biz.RefundResp

/**
 * 统一收单交易退款接口
 * https://docs.open.alipay.com/api_1/alipay.trade.refund
 * Created by guofan on 2017/3/30.
 */
@RequestConfig("alipay.trade.refund")
data class Refund(var out_trade_no: String? = null,
                  var trade_no: String? = null,
                  val refund_amount: String) : AliBizContent<RefundResp>() {
    var refund_reason: String? = null
    var out_request_no: String? = null


    companion object {
        fun ofOutTradeNo(id: String, refund_amount: String): Refund {
            return Refund(out_trade_no = id, refund_amount = refund_amount)
        }

        fun ofOutTradeNo(id: String, out_request_no: String, refund_amount: String): Refund {
            val refund = ofOutTradeNo(id, refund_amount)
            refund.out_request_no = out_request_no
            return refund
        }

        fun ofTradeNo(id: String, refund_amount: String): Refund {
            return Refund(trade_no = id, refund_amount = refund_amount)
        }

        fun ofTradeNo(id: String, out_request_no: String, refund_amount: String): Refund {
            val refund = ofTradeNo(id, refund_amount)
            refund.out_request_no = out_request_no
            return refund
        }
    }
}