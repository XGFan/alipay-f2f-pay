package com.xulog.alipay.bean.misc

enum class SignType {
    RSA,
    RSA2;

    override fun toString(): String {
        return this.name
    }
}