package com.xulog.alipay.bean.response.biz;

import com.smzdm.upay.sdk.common.annotation.RespKey;
import com.xulog.alipay.bean.response.AliBizResp;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@RespKey("alipay.trade.precreate")
public class PreCreateResp extends AliBizResp {
    private String out_trade_no;
    private String qr_code;
}
