package com.xulog.alipay.bean

enum class AlipayMethod(val str: String) {
    TRADE_QUERY("alipay.trade.query"),
    REFUND("alipay.trade.refund"),
    PAY("alipay.trade.pay"),
    PRE_CREATE("alipay.trade.precreate"),
    CANCEL("alipay.trade.cancel"),
    REFUND_QUERY("alipay.trade.fastpay.refund.query"),
    SETTLE("alipay.trade.order.settle");

    override fun toString(): String {
        return this.str
    }
}