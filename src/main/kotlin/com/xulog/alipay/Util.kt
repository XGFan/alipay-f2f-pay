package com.xulog.alipay

import java.nio.charset.Charset
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*

/**
 * Created by guofan on 2017/4/4.
 */
object Util {



    fun verifyWithRSA2(content: String, cs: Charset, ins: ByteArray, sign: String): Boolean {
        val key = publicKeyFromPKCS8("RSA", ins)
        val signature = Signature.getInstance("SHA256WithRSA")
        signature.initVerify(key)
        signature.update(content.toByteArray(cs))
        return signature.verify(Base64.getDecoder().decode(sign))
    }

    fun signWithRSA2(content: String, cs: Charset, ins: ByteArray): String {
        val key = privateKeyFromPKCS8("RSA", ins)
        val signature = Signature.getInstance("SHA256WithRSA")
        signature.initSign(key)
        signature.update(content.toByteArray(cs))
        val signed = signature.sign()
        return Base64.getEncoder().encode(signed).toString(cs)
    }

    fun privateKeyFromPKCS8(algorithm: String, ins: ByteArray): PrivateKey {
        val keyFactory = KeyFactory.getInstance(algorithm)
        val decode = Base64.getDecoder().decode(ins)
        return keyFactory.generatePrivate(PKCS8EncodedKeySpec(decode))
    }

    fun publicKeyFromPKCS8(algorithm: String, ins: ByteArray): PublicKey {
        val keyFactory = KeyFactory.getInstance(algorithm)
        val decode = Base64.getDecoder().decode(ins)
        return keyFactory.generatePublic(X509EncodedKeySpec(decode))
    }
}