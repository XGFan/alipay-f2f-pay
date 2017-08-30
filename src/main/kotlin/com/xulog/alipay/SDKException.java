package com.xulog.alipay;

import lombok.Getter;
import lombok.Setter;

public class SDKException extends Exception {
    @Getter
    @Setter
    private REASON reason;

    public SDKException(REASON reason) {
        super("[" + reason + "]");
        this.reason = reason;
    }

    public SDKException(REASON reason, String message) {
        super("[" + reason + "]" + message);
        this.reason = reason;
    }

    public SDKException(REASON reason, Throwable cause) {
        super("[" + reason + "]", cause);
        this.reason = reason;
    }

    public SDKException(REASON reason, String message, Throwable cause) {
        super("[" + reason + "]" + message, cause);
        this.reason = reason;
    }


    public static enum REASON {
        /**
         * 签名或者验签错误
         */
        SIGN_EXCEPTION,
        /**
         * 序列化错误
         */
        SERIALIZATION_EXCEPTION,
        /**
         * 网络错误
         */
        NETWORK_EXCEPTION,
        /**
         * 反序列化错误
         */
        DESERIALIZATION_EXCEPTION,
        /**
         * 未知错误
         */
        UNKNOWN
    }
}
