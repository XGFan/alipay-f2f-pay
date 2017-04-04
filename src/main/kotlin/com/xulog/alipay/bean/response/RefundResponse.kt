package com.xulog.alipay.bean.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

/**
 * Created by guofan on 2017/3/30.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class RefundResponse : CommonResponse() {
    var trade_no: String? = null
    var out_trade_no: String? = null
    var buyer_logon_id: String? = null
    var fund_change: String? = null
    var refund_fee: String? = null
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var gmt_refund_pay: Date? = null
    var refund_detail_item_list: List<TradeFundBill> = emptyList()
    var store_name: String? = null
    var buyer_user_id: String? = null
}