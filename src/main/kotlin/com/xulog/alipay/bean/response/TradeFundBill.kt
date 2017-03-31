package com.xulog.alipay.bean.response

class TradeFundBill {
    lateinit var fund_channel: String //交易使用的资金渠道
    var amount: String? = null //该支付工具类型所使用的金额
    var real_amount: String? = null //渠道实际付款金额
}