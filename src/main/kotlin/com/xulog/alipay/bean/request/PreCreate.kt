package com.xulog.alipay.bean.request

import com.xulog.alipay.bean.AlipayBizContent
import com.xulog.alipay.bean.AlipayMethod

class PreCreate(val out_trade_no: String, val total_amount: String, val subject: String) :
        AlipayBizContent(AlipayMethod.PRE_CREATE) {
    var seller_id: String? = null   //卖家支付宝用户ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
    //订单总金额，单位为元，精确到小数点后两位
    var discountable_amount: String? = null //订单总金额，单位为元，精确到小数点后两位
    var undiscountable_amount: String? = null   //订单总金额，单位为元，精确到小数点后两位
    var buyer_logon_id: String? = null // 买家支付宝账号
    var operator_id: String? = null //商户操作员编号
    var store_id: String? = null    //商户门店编号
    var terminal_id: String? = null //商户机具终端编号
    var timeout_express: String? = null
    var alipay_store_id: String? = null
}