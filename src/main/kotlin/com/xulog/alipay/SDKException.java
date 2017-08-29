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
        SIGN_EXCEPTION,
        SERIALIZATION_EXCEPTION,
        NETWORK_EXCEPTION,
        DESERIALIZATION_EXCEPTION,
        UNKNOWN
    }
}
