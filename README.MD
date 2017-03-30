## 支付宝当面付API

简单使用

```java
AlipayF2fPay alipayF2fPay = new AlipayF2fPay();//沙盒环境
//AlipayF2fPay alipayF2fPay = new AlipayF2fPay(appId,privateKey,alipayPublicKey,apiGateway);//正式环境
String res = alipayF2fPay.execute(new PreCreate("NO00001", "0.5", "五毛打赏"));
//自行将json转成map或者bean
```
返回值如下
```json
{"alipay_trade_precreate_response":{"code":"10000","msg":"Success","out_trade_no":"NO00001","qr_code":"https:\/\/qr.alipay.com\/bax08572oh2dfggyk00k0044"},"sign":"hYFIPqc6IPXC6hQsU5VeYdNL/hCZ8ArOZ9w3mYMDsxpLsT5ENg3YHNBtLJVkQ+E/9PI5XyHCg/82ku0ABiNeRCdrwyD34TBYik7MEiJcBHxVj1BYOM1OLnyABgeGh1yiw3soZOJrz2T0Ae/fEf4WwBhQPbpTwgJVkhXBQBnbrpTyJmVPLEpCE2H8fnojcRJftTort4CH9R9IWG2vJXUig+MZiWGJfAtFWNMNY9aumv/uVm96zy849CHrXDP0anULQiVtyeskB72l6s1u2TfQqgOXmyUOTeli4cMW0jUO0qczRyyjmuw+LYcJ67feqDpVoKPeOOJtZgH9/MIldBhBmw=="}
```


支持方法

```java
alipayF2fPay.execute(new PreCreate("NO00001", "0.5", "五毛打赏")); //创建二维码
alipayF2fPay.execute(new Refund("NO00001","0.5"));//退款
alipayF2fPay.execute(new TradeQuery("NO00001"));//查询订单情况
```



TODO

- [ ] 接口回调签名验证
- [ ] 响应序列化