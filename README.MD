## 支付宝当面付API

[![Build Status](https://travis-ci.org/XGFan/alipay-f2f-pay.svg?branch=master)](https://travis-ci.org/XGFan/alipay-f2f-pay)
![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.xulog.lib/alipay-f2f-pay/badge.svg)

添加依赖
```xml
<dependency>
    <groupId>com.xulog.lib</groupId>
    <artifactId>alipay-f2f-pay</artifactId>
    <version>${version}</version>
</dependency>
```

简单使用

```java
AlipayF2fPay alipayF2fPay = new AlipayF2fPay(yourNotifyUrl);//沙盒环境

AlipayF2fPay alipayF2fPay2 = new AlipayF2fPay(appId,signType,privateKey,alipayPublicKey,yourNotifyUrl);//正式环境

AlipayResponse<PreCreateResp> res = alipayF2fPay.execute(new PreCreate("NO00001", 50, "5毛钱的辣条"));//创建二维码
AlipayResponse<RefundResp> res2 = alipayF2fPay.execute(Refund.ofOutTradeNo("NO00001", 5));//退款
AlipayResponse<TradeQueryResp> res3 = alipayF2fPay.execute(new TradeQuery("NO00002"));//查询订单情况
AlipayResponse<CancelResp> res4 = alipayF2fPay.execute(Cancel.ofOutTradeNo("NO00001", 5));//关闭交易
AlipayResponse<PayResp> res5 = alipayF2fPay.execute(new Pay("NO00001", 10,"xxxxx","收钱"));//扫码支付

AlipayNotify notify = alipayF2fPay.callBack(map);
notify.isVerify()//验证回调签名
```


[测试二维码](https://pay.test4x.com/donate.png)

或者[点我去付款](https://pay.test4x.com/pay/10)