package com.xulog.alipay.bean.request

import com.xulog.alipay.bean.request.AlipayBizContent
import com.xulog.alipay.bean.request.AlipayMethod
import com.xulog.alipay.bean.response.RefundResponse

/**
 * Created by guofan on 2017/3/30.
 */
class Refund(val out_trade_no: String? = null,
             val trade_no: String? = null,
             val refund_amount: String) : AlipayBizContent<RefundResponse>(AlipayMethod.REFUND) {
    var refund_reason: String? = null
    var out_request_no: String? = null
    var operator_id: String? = null
    var store_id: String? = null
    var terminal_id: String? = null


    constructor(out_trade_no: String, refund_amount: String)
            : this(out_trade_no, null, refund_amount) {
        this.out_request_no = out_request_no
    }
}