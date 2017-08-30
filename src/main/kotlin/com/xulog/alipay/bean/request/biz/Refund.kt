package com.xulog.alipay.bean.request.biz

import com.xulog.alipay.MethodName
import com.xulog.alipay.bean.request.BizReq
import com.xulog.alipay.bean.response.biz.RefundResp
import com.xulog.alipay.util.MsicUtil.toYuan

/**
 * 统一收单交易退款接口
 * https://docs.open.alipay.com/api_1/alipay.trade.refund
 * Created by guofan on 2017/3/30.
 */
@MethodName("alipay.trade.refund")
data class Refund(var out_trade_no: String? = null,
                  var trade_no: String? = null,
                  val refund_amount: String) : BizReq<RefundResp>() {
    var refund_reason: String? = null
    var out_request_no: String? = null

    fun reason(why: String): Refund {
        this.refund_reason = why
        return this
    }


    companion object {
        /**
         * 单笔全退
         */
        fun ofOutTradeNo(id: String, refund_amount: Int): Refund {
            return Refund(out_trade_no = id, refund_amount = refund_amount.toYuan())
        }

        /**
         * 部分退款
         */
        fun ofOutTradeNo(id: String, out_request_no: String, refund_amount: Int): Refund {
            val refund = ofOutTradeNo(id, refund_amount)
            refund.out_request_no = out_request_no
            return refund
        }

        /**
         * 单笔全退
         */
        fun ofTradeNo(id: String, refund_amount: Int): Refund {
            return Refund(trade_no = id, refund_amount = refund_amount.toYuan())
        }

        /**
         * 部分退款
         */
        fun ofTradeNo(id: String, out_request_no: String, refund_amount: Int): Refund {
            val refund = ofTradeNo(id, refund_amount)
            refund.out_request_no = out_request_no
            return refund
        }
    }
}