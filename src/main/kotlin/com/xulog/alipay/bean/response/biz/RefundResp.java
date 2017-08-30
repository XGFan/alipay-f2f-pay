package com.xulog.alipay.bean.response.biz;

import com.xulog.alipay.bean.misc.TradeFundBill;
import com.xulog.alipay.bean.response.BizRes;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class RefundResp extends BizRes {
    private String trade_no;
    private String out_trade_no;
    private String buyer_logon_id;
    private String fund_change;
    private String refund_fee;
    private String gmt_refund_pay;
    private List<TradeFundBill> refund_detail_item_list;
    private String buyer_user_id;
}
