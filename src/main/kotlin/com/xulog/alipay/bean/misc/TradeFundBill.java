package com.xulog.alipay.bean.misc;

import lombok.Data;

@Data
public class TradeFundBill {
    private String fund_channel; //交易使用的资金渠道
    private String amount; //该支付工具类型所使用的金额
    private String real_amount; //渠道实际付款金额
}