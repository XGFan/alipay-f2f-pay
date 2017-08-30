package com.xulog.alipay.bean.misc;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.HashMap;
import java.util.Map;

public class Extra {

    @JsonUnwrapped
    final private Map<String, Object> extraMap = new HashMap<>();

    public Object getExtraArg(String key) {
        return this.extraMap.get(key);
    }


    @JsonAnySetter
    public void setExtraArg(String name, Object value) {
        this.extraMap.put(name, value);
    }

}
