package com.xulog.alipay.bean.response

/**
 * Created by guofan on 2017/3/30.
 */
data class PrecreateResponse(val alipayTradePrecreateResponse: AlipayTradePrecreateResponse, val sign: String) {

    data class AlipayTradePrecreateResponse(val code: String, val msg: String, val outTradeNo: String, val qrCode: String)
}