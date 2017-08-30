package com.xulog.alipay.bean.request

import com.fasterxml.jackson.annotation.JsonValue
import com.xulog.alipay.bean.misc.Extra
import com.xulog.alipay.bean.response.BizRes
import com.xulog.alipay.util.PojoUtils
import java.util.stream.Collectors

/**
 * 本质上没有什么卵用，主要是为了一个泛型来表示返回值
 */
abstract class BizReq<T : BizRes> : Extra() {

    /**
     * 本质上就是转成json
     * 用已经固定的参数加上额外可能后期添加的参数
     */
    @JsonValue
    final override fun toString(): String {
        return PojoUtils.transToKeyValueList(this)
                .filter { it.second != null && it.second.toString().isNotBlank() }
                .map { """"${it.first}":"${it.second}"""" }
                .sorted(compareBy { it })
                .collect(Collectors.joining(",", "{", "}"))
    }
}