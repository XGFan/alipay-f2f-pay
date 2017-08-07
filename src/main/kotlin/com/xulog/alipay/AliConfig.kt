package com.xulog.alipay

import com.xulog.alipay.bean.misc.SignType

open class AliConfig(val appId: String, val signType: SignType,
                     val privateKey: String, val publicKey: String,
                     val gateWay: String = "https://openapi.alipay.com/gateway.do", var notifyUrl: String)

