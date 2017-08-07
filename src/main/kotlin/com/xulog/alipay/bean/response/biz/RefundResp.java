package com.xulog.alipay.bean.response.biz;

import com.smzdm.upay.sdk.common.annotation.RespKey;
import com.xulog.alipay.bean.misc.TradeFundBill;
import com.xulog.alipay.bean.response.AliBizResp;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@RespKey("alipay_trade_refund_response")
public class RefundResp extends AliBizResp {
    private String trade_no;
    private String out_trade_no;
    private String buyer_logon_id;
    private String fund_change;
    private String refund_fee;
    private String gmt_refund_pay;
    private List<TradeFundBill> refund_detail_item_list;
    private String buyer_user_id;
}
