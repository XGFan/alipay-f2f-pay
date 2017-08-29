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
AlipayF2fPay alipayF2fPay = new AlipayF2fPay();//沙盒环境
//AlipayF2fPay alipayF2fPay = new AlipayF2fPay(appId,privateKey,alipayPublicKey);//正式环境

AlipayResponse<PreCreateResponse> res = alipayF2fPay.execute(new PreCreate("NO00029", "29", "29"));//创建二维码
AlipayResponse<RefundResponse> res2 = alipayF2fPay.execute(new Refund("NO00002", "0.5"));//退款
AlipayResponse<TradeQueryResponse> res3 = alipayF2fPay.execute(new TradeQuery("NO00002"));//查询订单情况

AsyncNotify notify = alipayF2fPay.verifyCallbackWithRSA2(map);//验证回调签名
```
