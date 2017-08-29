package com.xulog.alipay.bean.request.biz

import com.xulog.alipay.MethodName
import com.xulog.alipay.bean.request.AliBizContent
import com.xulog.alipay.bean.response.biz.QueryResp

/**
 * 统一收单线下交易查询
 * https://docs.open.alipay.com/api_1/alipay.trade.query
 */
@MethodName("alipay.trade.query")
data class Query(var out_trade_no: String? = null,
                 var trade_no: String? = null)
    : AliBizContent<QueryResp>() {


    companion object {
        fun ofOutTradeNo(id: String): Query {
            return Query(out_trade_no = id)
        }

        fun ofTradeNo(id: String): Query {
            return Query(trade_no = id)
        }
    }
}