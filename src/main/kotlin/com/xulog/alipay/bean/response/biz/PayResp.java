package com.xulog.alipay.bean.response.biz;

import com.xulog.alipay.bean.response.BizRes;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PayResp extends BizRes {
    private String trade_no;
    private String out_trade_no;
    private String buyer_logon_id;
    private String total_amount;
    private String receipt_amount;
    private String buyer_pay_amount;
    private String gmt_payment;
    private List discount_goods_detail;
}
