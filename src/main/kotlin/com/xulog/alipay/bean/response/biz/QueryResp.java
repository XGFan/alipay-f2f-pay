package com.xulog.alipay.bean.response.biz;

import com.smzdm.upay.sdk.common.annotation.RespKey;
import com.xulog.alipay.bean.misc.TradeFundBill;
import com.xulog.alipay.bean.misc.TradeStatus;
import com.xulog.alipay.bean.response.AliBizResp;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@RespKey("alipay_trade_query_response")
public class QueryResp extends AliBizResp {
    private String trade_no;
    private String out_trade_no;
    private String buyer_logon_id; //买家支付宝账号
    private TradeStatus trade_status; //交易状态
    private String total_amount; //交易的订单金额
    private String receipt_amount; //实收金额
    private String buyer_pay_amount; //买家实付金额
    private String point_amount; //积分支付的金额
    private String invoice_amount; //交易中用户支付的可开具发票的金额
    private String send_pay_date; //本次交易打款给卖家的时间(yyyy-MM-dd HH:mm:ss)
    private String alipay_store_id; //支付宝店铺编号
    private String store_id; //商户门店编号
    private String terminal_id; //商户机具终端编号
    private List<TradeFundBill> fund_bill_list = Collections.emptyList();//交易支付使用的资金渠道

    private String store_name; //请求交易支付中的商户店铺的名称
    private String buyer_user_id; //买家在支付宝的用户id
    private String discount_goods_detail; //本次交易支付所使用的单品券优惠的商品优惠信息	json
    private String industry_sepc_detail; //行业特殊信息（例如在医保卡支付业务中，向用户返回医疗信息）。
    private List<String> voucher_detail_list = Collections.emptyList();//本交易支付时使用的所有优惠券信息
}