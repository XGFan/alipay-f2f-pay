package com.xulog.alipay.bean.request.biz

import com.xulog.alipay.annotation.RequestConfig
import com.xulog.alipay.bean.request.AliBizContent
import com.xulog.alipay.bean.response.biz.QueryResp

/**
 * 统一收单线下交易查询
 * https://docs.open.alipay.com/api_1/alipay.trade.query
 */
@RequestConfig("alipay.trade.cancel")
data class Query(var out_trade_no: String? = null,
                 var trade_no: String? = null)
    : AliBizContent<QueryResp>() {


    companion object {
        fun ofOutTradeNo(id: String): Query {
            val query = Query(id)
            return query
        }

        fun ofTradeNo(id: String): Query {
            val query = Query()
            query.trade_no = id
            return query
        }
    }
}