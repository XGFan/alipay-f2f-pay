package com.xulog.alipay.bean.callback;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xulog.alipay.bean.misc.Extra;
import com.xulog.alipay.bean.misc.SignType;
import com.xulog.alipay.bean.misc.TradeStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayNotify extends Extra {
    /**
     * 通知时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date notify_time;
    /**
     * 通知的类型
     */
    private String notify_type;
    /**
     * 通知校验ID
     */
    private String notify_id;
    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    private SignType sign_type;
    /**
     * 签名
     */
    private String sign;
    /**
     * 支付宝交易号
     */
    private String trade_no;
    /**
     * 支付宝分配给开发者的应用Id
     */
    private String app_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 商户业务ID，主要是退款通知中返回退款申请的流水号
     */
    private String out_biz_no;

    /**
     * 买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字
     */
    private String buyer_id;
    /**
     * 买家支付宝账号
     */
    private String buyer_logon_id;

    /**
     * 卖家支付宝用户号
     */
    private String seller_id;
    /**
     * 卖家支付宝账号
     */
    private String seller_email;
    /**
     * 交易目前所处的状态
     */
    private TradeStatus trade_status;

    /**
     * 本次交易支付的订单金额，单位为人民币（元）
     */
    private String total_amount;

    private String subject;
    private String body;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmt_create;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmt_payment;

    @JsonIgnore
    private boolean verify = false;


    public SignType getSign_type() {
        return sign_type;
    }

    public String getSign() {
        return sign;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }
}