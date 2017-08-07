package com.xulog.alipay

import com.xulog.alipay.bean.misc.SignType

/**
 * 签名接口
 */
interface Sign {

    fun setSign_type(signType: SignType)

    fun setSign(sign: String)


}
