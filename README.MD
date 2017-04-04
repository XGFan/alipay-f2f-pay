## 支付宝当面付API

添加依赖
```xml
<dependency>
    <groupId>com.xulog.lib</groupId>
    <artifactId>alipay-f2f-pay</artifactId>
    <version>0.0.2</version>
</dependency>
```
可能需要添加repository
```xml
<repository>
    <id>jcenter</id>
    <url>https://jcenter.bintray.com/</url>
</repository
```



简单使用

```java
AlipayF2fPay alipayF2fPay = new AlipayF2fPay();//沙盒环境
//AlipayF2fPay alipayF2fPay = new AlipayF2fPay(appId,privateKey,alipayPublicKey,apiGateway);//正式环境

AlipayResponse<PreCreateResponse> res = alipayF2fPay.execute(new PreCreate("NO00029", "29", "29"));//创建二维码
AlipayResponse<RefundResponse> res2 = alipayF2fPay.execute(new Refund("NO00002", "0.5"));//退款
AlipayResponse<TradeQueryResponse> res3 = alipayF2fPay.execute(new TradeQuery("NO00002"));//查询订单情况

alipayF2fPay.verifyCallbackWithRSA2(map);//验证回调签名
```



TODO

- [x] 接口回调签名验证
- [x] 响应序列化
- [ ] 支持SHA1
- [ ] 支持GBK