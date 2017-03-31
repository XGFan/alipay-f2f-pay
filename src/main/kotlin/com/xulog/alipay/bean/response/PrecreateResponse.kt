package com.xulog.alipay.bean.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by guofan on 2017/3/30.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class PrecreateResponse : CommonResponse() {
    var out_trade_no: String? = null
    var qr_code: String? = null
}