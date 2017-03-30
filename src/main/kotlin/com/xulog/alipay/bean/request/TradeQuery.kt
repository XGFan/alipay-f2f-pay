package com.xulog.alipay.bean.request

import com.xulog.alipay.bean.AlipayBizContent
import com.xulog.alipay.bean.AlipayMethod

class TradeQuery(val out_trade_no: String?, val trade_no: String?)
    : AlipayBizContent(AlipayMethod.TRADE_QUERY) {
    constructor(out_trade_no: String) : this(out_trade_no, null)
}