package com.xulog.alipay.util

import com.xulog.alipay.bean.misc.SignType
import java.math.BigDecimal
import java.math.RoundingMode
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.xml.bind.DatatypeConverter


/**
 * Created by guofan on 2017/4/4.
 */
object Util {
    const val Digest_RSA: String = "RSA"
    const val Digest_MD5: String = "MD5"
    const val Digest_SHA1: String = "SHA-1"
    const val Digest_SHA256: String = "SHA-256"

    const val Algorithm_RSA1: String = "SHA1WithRSA"
    const val Algorithm_RSA2: String = "SHA256WithRSA"


    /**
     * 摘要算法
     */
    fun digest(digestAlgorithm: String, content: String): String {
        val digest = MessageDigest.getInstance(digestAlgorithm)
        digest.update(content.toByteArray(StandardCharsets.UTF_8))
        val byteHash: ByteArray = digest.digest()
        digest.reset()
        return DatatypeConverter.printHexBinary(byteHash)
    }

    fun sign(algorithm: String, key: String, content: String): String {
        val signature = Signature.getInstance(algorithm)
        signature.initSign(getRsaPrivateKeyFromPKCS8(key.toByteArray(StandardCharsets.UTF_8)))
        signature.update(content.toByteArray(StandardCharsets.UTF_8))
        val signed = signature.sign()
        return Base64.getEncoder().encode(signed).toString(StandardCharsets.UTF_8)
    }

    fun verify(algorithm: String, key: String, content: String, sign: String): Boolean {
        val signature = Signature.getInstance(algorithm)
        signature.initVerify(getRsaPublicKeyFromPKCS8(key.toByteArray(StandardCharsets.UTF_8)))
        signature.update(content.toByteArray(StandardCharsets.UTF_8))
        return signature.verify(Base64.getDecoder().decode(sign))
    }

    /**
     * todo
     * 验签
     */
    fun verify(content: String, keyString: String, sign: String, signType: SignType): Boolean {
        return when (signType) {
            SignType.RSA -> {
                verify(Algorithm_RSA1, keyString, content, sign)
            }
            SignType.RSA2 -> {
                verify(Algorithm_RSA2, keyString, content, sign)
            }
        }
    }

    /**
     * 签名
     * 返回的是经过Base64 encode之后的数据
     */
    fun sign(signType: SignType, content: String, keyString: String): String {
        return when (signType) {
            SignType.RSA -> {
                sign(Algorithm_RSA1, keyString, content)
            }
            SignType.RSA2 -> {
                sign(Algorithm_RSA2, keyString, content)
            }
        }
    }


    fun getRsaPrivateKeyFromPKCS8(ins: ByteArray): PrivateKey {
        val keyFactory = KeyFactory.getInstance(Digest_RSA)
        val decode = Base64.getDecoder().decode(ins)
        return keyFactory.generatePrivate(PKCS8EncodedKeySpec(decode))
    }

    fun getRsaPublicKeyFromPKCS8(ins: ByteArray): PublicKey {
        val keyFactory = KeyFactory.getInstance(Digest_RSA)
        val decode = Base64.getDecoder().decode(ins)
        return keyFactory.generatePublic(X509EncodedKeySpec(decode))
    }


    fun <K, V> Map<K, V>.toRequestUrl(gateWay: String): String {
        val url = if (!gateWay.endsWith("?")) {
            gateWay + "?"
        } else {
            gateWay
        }
        return this.map {
            "${URLEncoder.encode(it.key.toString(), "UTF-8")}=${URLEncoder.encode(it.value.toString(), "UTF-8")}"
        }.joinToString("&", url, "")
    }


    val Hundred: BigDecimal = BigDecimal.valueOf(100L)

    fun Int.toYuan(): String {
        val bd = BigDecimal.valueOf(this.toLong())
        return bd.divide(Hundred, 2, RoundingMode.CEILING).toPlainString()
    }

    fun randomString(len: Int): String {
        val random = Random()
        val stringBuilder = StringBuilder()
        (0..len - 1).forEach {
            val i = random.nextInt(62)
            stringBuilder.append(if (i < 10) {
                (i + 48).toChar()
            } else if (i in 10..35) {
                (i + 55).toChar()
            } else {
                (i + 61).toChar()
            })
        }
        return stringBuilder.toString()
    }

}