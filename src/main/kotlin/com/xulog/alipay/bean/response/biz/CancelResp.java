package com.xulog.alipay.bean.response.biz;

import com.xulog.alipay.bean.response.AliBizResp;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CancelResp extends AliBizResp {
    private String trade_no;
    private String out_trade_no;
    private String retry_flag;
    private String action;
}
