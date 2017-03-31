package com.xulog.alipay.bean.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by guofan on 2017/3/31.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class TradeQueryResponse : CommonResponse() {
    var trade_no: String? = null
    var out_trade_no: String? = null
    var buyer_logon_id: String? = null //买家支付宝账号
    var trade_status: TradeStatus? = null //交易状态
    var total_amount: String? = null //交易的订单金额
    var receipt_amount: String? = null //实收金额
    var buyer_pay_amount: String? = null //买家实付金额
    var point_amount: String? = null //积分支付的金额
    var invoice_amount: String? = null //交易中用户支付的可开具发票的金额
    var send_pay_date: String? = null //本次交易打款给卖家的时间(yyyy-MM-dd HH:mm:ss)
    var alipay_store_id: String? = null //支付宝店铺编号
    var store_id: String? = null //商户门店编号
    var terminal_id: String? = null //商户机具终端编号
    var fund_bill_list: List<TradeFundBill> = emptyList() //交易支付使用的资金渠道
    var store_name: String? = null //请求交易支付中的商户店铺的名称
    var buyer_user_id: String? = null //买家在支付宝的用户id
    var discount_goods_detail: String? = null //本次交易支付所使用的单品券优惠的商品优惠信息	json
    var industry_sepc_detail: String? = null //行业特殊信息（例如在医保卡支付业务中，向用户返回医疗信息）。
    var voucher_detail_list: List<String> = emptyList() //本交易支付时使用的所有优惠券信息


    enum class TradeStatus {
        WAIT_BUYER_PAY,
        TRADE_CLOSED,
        TRADE_SUCCESS,
        TRADE_FINISHED
    }
}

