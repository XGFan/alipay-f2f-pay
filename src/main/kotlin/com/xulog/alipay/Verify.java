package com.xulog.alipay;

import com.xulog.alipay.bean.misc.SignType;
import com.xulog.alipay.util.PojoUtils;
import com.xulog.alipay.util.Util;

/**
 * Created by Guofan on 2017/7/25.
 */
public interface Verify {

    SignType getSign_type();

    String getSign();

    String getNotify_id();

    default boolean verify(String publicKey) {
        String sortQuery = PojoUtils.transToSortedQueryStr(this, "sign", "sign_type");
        return Util.INSTANCE.verify(sortQuery, publicKey, this.getSign(), this.getSign_type());
    }

}
