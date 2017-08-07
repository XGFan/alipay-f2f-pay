package com.xulog.alipay.bean.response.biz;

import com.smzdm.upay.sdk.common.annotation.RespKey;
import com.xulog.alipay.bean.response.AliBizResp;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@RespKey("alipay_trade_pay_response")
public class PayResp extends AliBizResp {
    private String trade_no;
    private String out_trade_no;
    private String buyer_logon_id;
    private String total_amount;
    private String receipt_amount;
    private String buyer_pay_amount;
    private String gmt_payment;
    private List discount_goods_detail;
}
