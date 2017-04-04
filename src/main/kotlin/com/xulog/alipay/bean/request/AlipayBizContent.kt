package com.xulog.alipay.bean.request

import com.xulog.alipay.bean.response.CommonResponse

abstract class AlipayBizContent<T : CommonResponse>(val method: AlipayMethod) {

    override fun toString(): String {
        return this::class.java.declaredFields.map {
            it.isAccessible = true
            val value = it.get(this)
            if (value == null) {
                null
            } else {
                it.name to value
            }
        }.filterNotNull()
                .map { """"${it.first}":"${it.second}"""" }
                .sortedBy { it }
                .joinToString(separator = ",", prefix = "{", postfix = "}")
    }
}