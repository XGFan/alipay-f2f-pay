package com.xulog.alipay.bean.response

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

/**
 * Created by guofan on 2017/3/31.
 */
class AlipayResponse<T : CommonResponse>() {
    lateinit var sign: String
    lateinit var bizContent: T

    @Suppress("UNCHECKED_CAST")
    @JsonAnySetter
    fun setDynamicProperty(name: String, value: Any) {
        val objectMapper = ObjectMapper().registerKotlinModule()
        val writeValueAsString = objectMapper.writeValueAsString(value)
        bizContent = when (name) {
            "alipay_trade_precreate_response" -> objectMapper.readValue(writeValueAsString, PreCreateResponse::class.java) as T
            "alipay_trade_query_response" -> objectMapper.readValue(writeValueAsString, TradeQueryResponse::class.java) as T
            "alipay_trade_refund_response" -> objectMapper.readValue(writeValueAsString, RefundResponse::class.java) as T
            else -> {
                CommonResponse() as T
            }
        }
    }
}