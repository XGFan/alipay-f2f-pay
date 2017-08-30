package com.xulog.alipay.bean.response.biz;

import com.xulog.alipay.bean.response.BizRes;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PreCreateResp extends BizRes {
    private String out_trade_no;
    private String qr_code;
}
