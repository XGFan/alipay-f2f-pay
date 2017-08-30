package com.xulog.alipay.bean.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xulog.alipay.AlipayF2fPay;
import lombok.Data;

import java.io.IOException;


/**
 * Created by guofan on 2017/3/31.
 */
@Data
public class AlipayResponse<T extends BizRes> {
    private String sign = "";
    private T biz_content;

    @SuppressWarnings("unchecked")
    @JsonAnySetter
    public void setDynamicProperty(String name, Object value) {
        String writeValueAsString;
        try {
            ObjectMapper objectMapper = AlipayF2fPay.Companion.getObjectMapper();
            writeValueAsString = objectMapper.writeValueAsString(value);
            Class clazz = AlipayF2fPay.Companion.getRESPCLASSES().getOrDefault(name, BizRes.class);
            this.biz_content = objectMapper.readValue(writeValueAsString, (Class<T>) clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}